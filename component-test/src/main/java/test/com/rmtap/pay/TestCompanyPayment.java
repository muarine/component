package test.com.rmtap.pay;

import com.rtmap.utils.string.OrderUtils;
import com.rtmap.wx.sdk.exp.RtmapConnectException;
import com.rtmap.wx.sdk.exp.RtmapInvalidException;
import com.rtmap.wx.sdk.exp.RtmapPayException;
import com.rtmap.wx.sdk.pay.model.CompanyPayment;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * TestCompanyPayment
 *
 * @author Muarine<maoyun0903@163.com>
 * @date 2016 11/16/16 20:10
 * @since 2.0.0
 */
public class TestCompanyPayment {

    @Test
    public void testPayment(){
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("mch_appid" , "xxxx");
        requestMap.put("mchid" , "xxxx");
//        requestMap.put("device_info" , "");
        requestMap.put("nonce_str" , OrderUtils.randomString(32));
        requestMap.put("partner_trade_no" , OrderUtils.randomString(32));
        requestMap.put("openid" , "xxxx");
        requestMap.put("check_name" , "NO_CHECK");  // NO_CHECK FORCE_CHECK OPTION_CHECK
//        requestMap.put("re_user_name" , "");
        requestMap.put("amount" , 100);
        requestMap.put("desc" , "转账付款的记录");
        requestMap.put("spbill_create_ip" , "127.0.0.1");

        String key = "xxxx";
        String caPath = "/Users/Muarine/cert/apiclient_cert_test.p12";
        try {
            InputStream in = new FileInputStream(caPath);
            CompanyPayment companyPayment = CompanyPayment.create(requestMap, key, in);
            System.out.println(companyPayment.toString());
        } catch (RtmapPayException | RtmapInvalidException | RtmapConnectException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
