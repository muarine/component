package com.rtmap.wx.sdk.pay.core;

import com.rtmap.utils.security.SignUtil;
import com.rtmap.wx.sdk.exp.RtmapConnectException;
import com.rtmap.wx.sdk.exp.RtmapInvalidException;
import com.rtmap.wx.sdk.exp.RtmapPayException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Map;

/**
 * PayHandler   支付API,封装HTTP,抛异常
 *
 * @author Muarine<maoyun@rtmap.com>
 * @date 2016 10/21/16 17:38
 * @since 2.0.0
 */
public class PayHandler extends RtmapObject{

    private final static String CHARSET = "UTF-8";

    private enum RequestMethod{
        GET , POST , DELETE , PUT
    }

    /**
     * HTTPS 双向证书请求
     *
     * @param url           请求地址
     * @param requestParam  请求MAP
     * @param key           商户密钥
     * @param mchId         mchId
     * @param in            cert inputstream
     * @param clazz         字节码文件
     * @param <T>           泛型响应对象
     * @return
     */
    protected static <T> T request(String url, Map<String, Object> requestParam, String key , Class<T> clazz, String mchId , InputStream in) throws RtmapPayException, RtmapConnectException, RtmapInvalidException {
        return request(url , requestParam , key , clazz, true , true , mchId , in);
    }

    /**
     * 响应成功后不检查Map签名
     * @param url           请求URL
     * @param requestParam  请求参数
     * @param key           支付密钥
     * @param clazz         请求对象字节码
     * @param <T>           返回对象
     * @param in            证书输入流
     * @return
     * @throws RtmapPayException
     * @throws RtmapConnectException
     * @throws RtmapInvalidException
     */
    protected static <T> T requestNoCheckSign(String url, Map<String, Object> requestParam, String key ,Class<T> clazz, String mchId , InputStream in) throws RtmapPayException, RtmapConnectException, RtmapInvalidException {
        return request(url , requestParam , key , clazz, false , true , mchId , in);
    }

    /**
     * HTTPS
     * 默认:
     * 不携带证书请求
     * 校验响应XML的签名
     *
     * @param url           请求URL
     * @param requestParam  请求参数
     * @param key           支付密钥
     * @param clazz         请求对象字节码
     * @param <T>           返回对象
     * @return
     * @throws RtmapConnectException
     * @throws RtmapInvalidException
     * @throws RtmapPayException
     */
    protected static <T> T request(String url, Map<String, Object> requestParam,
                                   String key , Class<T> clazz)
            throws RtmapConnectException, RtmapInvalidException, RtmapPayException {
        return request(url , requestParam , key , clazz , true , false , null , null);
    }

    /**
     * HTTPS 不携带证书请求
     *
     * @param url           请求URL
     * @param requestParam  请求参数
     * @param key           支付密钥
     * @param clazz         请求对象字节码
     * @param <T>           返回对象
     * @return
     * @throws RtmapConnectException
     * @throws RtmapInvalidException
     * @throws RtmapPayException
     */
    private static <T> T request(String url, Map<String, Object> requestParam,
                                   String key , Class<T> clazz , boolean checkSign ,
                                   boolean useCert , String mchId , InputStream in) throws RtmapConnectException, RtmapInvalidException, RtmapPayException {
        // 签名
        requestParam.put("sign" , SignUtil.getMapSign(requestParam , key));
        // 组装请求XML报文
        String body = SignUtil.mapToXML(requestParam);
        // 发送请求
        RtmapResponse response = makeURLConnectionRequest(RequestMethod.POST , url , body , useCert , mchId , in);
        // 检测响应报文
        if(response.getErrcode() != 200){
            throw new RtmapConnectException(String.format("响应码:%s , 网络请求失败" , response.getErrcode()));
        }
        // 组装Model
        String result = response.getResult();
        Map<String, Object> responseMap = SignUtil.xmlToMap(result);

        handlerErrMsg(result , responseMap , key , checkSign);

        return mapToInstance(responseMap , clazz);
    }




    /**
     * 处理网络请求响应的错误信息
     *
     * @param result            响应结果
     * @param responseResult    响应结果
     * @param key               支付密钥
     * @throws RtmapPayException
     */
    private static void handlerErrMsg(String result , Map<String, Object> responseResult , String key , boolean checkSign) throws RtmapPayException {


        // 3. 判定结果  先判断协议字段返回，再判断业务返回，最后判断交易状态
        String return_code = responseResult.get("return_code").toString();
        if (!return_code.equals("SUCCESS")) {
            throw new RtmapPayException(return_code , objToStr(responseResult.get("return_msg")) , result);
        }
        String result_code = responseResult.get("result_code").toString();
        if(!result_code.equals("SUCCESS")){
            throw new RtmapPayException(return_code , objToStr(responseResult.get("return_msg")) ,
                    objToStr(responseResult.get("result_code")),objToStr(responseResult.get("err_code")),
                    objToStr(responseResult.get("err_code_des")) ,result);
        }
        // 签名校验
        if (checkSign && !SignUtil.checkMapSign(responseResult, key)){
            throw new RtmapPayException(String.format("请求失败. 签名校验失败 content:\n%s" , result));
        }

    }

    private static String objToStr(Object obj){
        if(obj == null){
            return "";
        }
        return obj.toString();
    }

    /**
     * 构建网络请求,返回响应结果
     *
     * @param method
     * @param url
     * @param body
     * @return
     * @throws RtmapConnectException
     */
    private static RtmapResponse makeURLConnectionRequest(
            PayHandler.RequestMethod method, String url, String body , boolean useCert , String mchId , InputStream in) throws RtmapConnectException {
        HttpURLConnection conn = null;
        try {
            switch (method) {
//                case GET:
//                    conn = createGetConnection(url, body);
//                    break;
                case POST:
                    conn = createPostConnection(url, body , useCert , mchId , in);
                    break;
//                case DELETE:
//                    conn = createDeleteConnection(url, body);
//                    break;
//                case PUT:
//                    conn = createPutConnection(url, body);
//                    break;
                default:
                    throw new RtmapConnectException(String.format("Unrecognized HTTP method %s. ", method));
            }
            // trigger the request
            int errcode = conn.getResponseCode();
            String result;

            if (errcode >= 200 && errcode < 300) {
                result = SignUtil.inputStreamToString(conn.getInputStream());
            } else {
                result = SignUtil.inputStreamToString(conn.getErrorStream());
            }
            return new RtmapResponse(errcode, result);

        } catch (IOException e) {
            throw new RtmapConnectException(String.format("发送请求失败,URL:%s 错误内容:%s.", url, e.getMessage()), e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    /**
     * 创建POST连接
     *
     * @param url       请求地址
     * @param body      请求报文
     * @return
     * @throws IOException
     */
    private static HttpURLConnection createPostConnection(String url , String body , boolean useCert , String mchId , InputStream in) throws IOException, RtmapConnectException {
        URL connURL = new URL(url);
        HttpsURLConnection conn = (HttpsURLConnection) connURL.openConnection();

        conn.setConnectTimeout(30 * 1000);
        conn.setReadTimeout(80 * 1000);
        conn.setUseCaches(false);

        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", String.format("application/xml;charset=%s", CHARSET));
        if(useCert){
            try {
                // Load the key store using the CA
                KeyStore keyStore = KeyStore.getInstance("PKCS12");
                keyStore.load(in, mchId.toCharArray());

                KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                kmf.init(keyStore, mchId.toCharArray());
                // init SSLContext
                SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
                sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());

                conn.setSSLSocketFactory(sslContext.getSocketFactory());

            } catch (CertificateException | NoSuchAlgorithmException | KeyStoreException |
                    KeyManagementException | UnrecoverableKeyException | NoSuchProviderException e) {
                throw new RtmapConnectException(e.getMessage() , e);
            }

        }
        OutputStream output = null;
        try {
            output = conn.getOutputStream();
            output.write(body.getBytes(CHARSET));
        } finally {
            if (output != null) {
                output.close();
            }
        }
        return conn;
    }

}
