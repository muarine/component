package test.com.rmtap.pay;

import com.rtmap.core.exp.RtmapConnectException;
import com.rtmap.core.exp.RtmapInvalidException;
import com.rtmap.core.exp.RtmapPayException;
import com.rtmap.wx.sdk.pay.model.UnifiedOrder;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * TestOrder
 *
 * @author Muarine<maoyun@rtmap.com>
 * @date 2016 10/22/16 09:29
 * @since 2.0.0
 */
public class PrePayTest {

    /**
     * 受理模式预下单
     */
    @Test
    public void testOrder(){

        Map<String, Object> orderMap = new HashMap<String, Object>();
        orderMap.put("appid", "wxf3a057928b881466");
        orderMap.put("mch_id", "1244220402");
        orderMap.put("sub_mch_id", "1262120901");
        orderMap.put("nonce_str", "qhk39pevst31ycrxlc7f1zac9v3gwnc8");
        orderMap.put("body", "当红不让1028");
        orderMap.put("notify_url", "http://xxx.com/");
        orderMap.put("out_trade_no", "T0142016122108405329683298720601");
        orderMap.put("total_fee", 100);
        orderMap.put("trade_type", "JSAPI");
        orderMap.put("spbill_create_ip", "127.0.0.1");
        orderMap.put("openid", "xxxx");
        orderMap.put("receipt", "Y");

        String key = "xxxx";
        try {
            UnifiedOrder unifiedOrder = UnifiedOrder.create(orderMap , key);

            System.out.println(unifiedOrder.toString());

        } catch (RtmapPayException | RtmapConnectException | RtmapInvalidException e) {
            e.printStackTrace();
        }
    }

    /**
     * 受理模式预下单
     */
    @Test
    public void testOrder1(){

        Map<String, Object> orderMap = new HashMap<String, Object>();
        orderMap.put("appid", "wx75e99b3610c1a31e");
        orderMap.put("mch_id", "1398656302");
        orderMap.put("sub_mch_id", "1406427402");
        orderMap.put("nonce_str", "JAUWEQEQEW87667687HH");
        orderMap.put("body", "测试预下单");
        orderMap.put("notify_url", "http://nwxpay.i-lz.cn/wxpay/default/noticeRtmap/mchid/1400039902/orderid/201610227531620434");
        orderMap.put("out_trade_no", "T0022016102209410557291520870128");
        orderMap.put("total_fee", 100);
        orderMap.put("trade_type", "JSAPI");
        orderMap.put("spbill_create_ip", "127.0.0.1");
        orderMap.put("openid", "oRE0wv5kPhNe10wwwmkgVwSZ1eiw");

        String key = "jingluojingluo999fuwushang000000";
        try {
            UnifiedOrder unifiedOrder = UnifiedOrder.create(orderMap , key);

            System.out.println(unifiedOrder.toString());

        } catch (RtmapPayException | RtmapConnectException | RtmapInvalidException e) {
            e.printStackTrace();
        }
    }

    /**
     * 普通商户JSAPI 预下单
     */
    @Test
    public void testGernalOrder(){

        Map<String, Object> orderMap = new HashMap<String, Object>();
        // 西单停车
        orderMap.put("appid", "wxfbea9e80126c504f");
        orderMap.put("mch_id", "1250048401");
        orderMap.put("nonce_str", "qhk39pevst31ycrxlc7f1zac9v3gwnc8");
        orderMap.put("body", "当红不让1028");
        orderMap.put("notify_url", "http://www.rtmap.com/");
        orderMap.put("out_trade_no", "T0142016122108405329683298721601");
        orderMap.put("total_fee", 100);
        orderMap.put("trade_type", "JSAPI");
        orderMap.put("spbill_create_ip", "127.0.0.1");
        orderMap.put("openid", "oq1Gkt92Bolno6FnFOTWADfrqMZw");
        orderMap.put("receipt", "Y");

        String key = "2whatisyournamemynameisShiYuKun2";
        try {
            UnifiedOrder unifiedOrder = UnifiedOrder.create(orderMap , key);

            System.out.println(unifiedOrder.toString());

        } catch (RtmapPayException | RtmapConnectException | RtmapInvalidException e) {
            e.printStackTrace();
        }
    }


    /**
     * 普通商户APP 预下单
     */
    @Test
    public void testAppPrePay(){
        /**
         * {"nonce_str":"igkiznfgaonxrcnn6uaq77b4e93vyx9e",
         * "out_trade_no":"T0122016122217414867035007421096",
         * "appid":"wxf3a057928b881466",
         * "total_fee":1,
         * "trade_type":"APP",
         * "attach":"112233",
         * "mch_id":"1234667902",
         * "notify_url":"http://pay.rtmap.com/pay-api/callback/wxpay",
         * "body":"支付测试",
         * "spbill_create_ip":"127.0.0.1"}
         */
        Map<String, Object> orderMap = new HashMap<String, Object>();
        // 西单停车
        orderMap.put("appid", "wxfef6f93adaf82206");
        orderMap.put("mch_id", "10035148");
        orderMap.put("nonce_str", "qhk39pevst31ycrxlc7f1zac9v3gwnc8");
        orderMap.put("out_trade_no", "T0122016122217414867035007421096");
        orderMap.put("body", "支付测试");
        orderMap.put("notify_url", "http://pay.rtmap.com/pay-api/callback/wxpay");
        orderMap.put("total_fee", 100);
        orderMap.put("trade_type", "APP");
        orderMap.put("spbill_create_ip", "127.0.0.1");

        String key = "2whatisyournamemynameisShiYuKun2";
        try {
            UnifiedOrder unifiedOrder = UnifiedOrder.create(orderMap , key);

            System.out.println(unifiedOrder.toString());

        } catch (RtmapPayException | RtmapConnectException | RtmapInvalidException e) {
            e.printStackTrace();
        }
    }

}
