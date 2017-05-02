package test.com.rmtap.pay;

import com.rtmap.core.exp.RtmapConnectException;
import com.rtmap.core.exp.RtmapInvalidException;
import com.rtmap.core.exp.RtmapPayException;
import com.rtmap.utils.string.OrderUtils;
import com.rtmap.wx.sdk.pay.model.RedPack;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * TestRedPack
 *
 * @author Muarine<maoyun@rtmap.com>
 * @date 2016 10/22/16 17:37
 * @since 2.0.0
 */
public class RedPackTest {

    /**
     * 普通模式发放现金红包
     */
    @Test
    public void testSendRedPack(){
        Map<String,Object> requestParam = new HashMap<String, Object>();
        requestParam.put("nonce_str", OrderUtils.randomString(32));
        requestParam.put("mch_billno", OrderUtils.randomInt(28));
        requestParam.put("mch_id", "xxxx");
        requestParam.put("wxappid", "xxxx");
        requestParam.put("send_name", "xxxx");
        requestParam.put("re_openid", "xxxx");
        requestParam.put("total_amount", 100);	//付款金额：分
        requestParam.put("total_num", 1);
        requestParam.put("wishing", "提前预祝您圣诞节快乐！");		// 字符限制：128
        requestParam.put("client_ip", "127.0.0.1");
        requestParam.put("act_name", "现金红包活动");
        requestParam.put("remark", "全民参与红包");

        String key = "xxxx";
        String certPath = "/Users/Muarine/cert/apiclient_cert_test.p12";

        try {
            InputStream in = new FileInputStream(certPath);
            RedPack refund = RedPack.create(requestParam , key , in);
            System.out.println(refund.toString());
        } catch (RtmapPayException | RtmapConnectException | RtmapInvalidException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 受理模式发放现金红包
     */
    @Test
    public void testSendRedPack2(){
        Map<String,Object> requestParam = new HashMap<String, Object>();
        requestParam.put("nonce_str", OrderUtils.randomString(32));
        requestParam.put("mch_billno", OrderUtils.randomInt(28));
        requestParam.put("mch_id", "xxxx");
        requestParam.put("sub_mch_id", "xxxx");
        requestParam.put("wxappid", "xxxx");
        requestParam.put("msgappid", "xxxx");
        requestParam.put("send_name", "北京");
        requestParam.put("re_openid", "xxxx");
        requestParam.put("total_amount", 100);	//付款金额：分
        requestParam.put("total_num", 1);
        requestParam.put("wishing", "提前预祝您圣诞节快乐！");		// 字符限制：128
        requestParam.put("client_ip", "127.0.0.1");
        requestParam.put("act_name", "现金红包活动");
        requestParam.put("remark", "全民参与红包");
//        requestParam.put("consume_mch_id", "1262120901");

        String key = "xxxx";
        String certPath = "/Users/Muarine/cert/apiclient_cert.p12";

        try {
            InputStream in = new FileInputStream(certPath);
            RedPack refund = RedPack.create(requestParam , key , in);
            System.out.println(refund.toString());
        } catch (RtmapPayException | RtmapConnectException | RtmapInvalidException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
