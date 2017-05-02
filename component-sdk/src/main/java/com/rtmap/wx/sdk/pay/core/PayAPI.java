package com.rtmap.wx.sdk.pay.core;

/**
 * PayAPI
 *
 * @author Muarine<maoyun@rtmap.com>
 * @date 2016 10/21/16 17:49
 * @since 2.0.0
 */
public class PayAPI {

    private static final String UNIFIED_ORDER   = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    private static final String ORDER_QUERY     = "https://api.mch.weixin.qq.com/pay/orderquery";
    private static final String CLOSE_ORDER     = "https://api.mch.weixin.qq.com/pay/closeorder";
    private static final String REFUND          = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    private static final String SEND_REDPACK    = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
    private static final String REDPACK_INFO    = "https://api.mch.weixin.qq.com/mmpaymkttransfers/gethbinfo";
    private static final String GROUP_REDPACK   = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendgroupredpack";
    private static final String MICRO_PAY       = "https://api.mch.weixin.qq.com/pay/micropay";

    private static final String PROMOTION_TRANSFERS   = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";

    /**
     * 微信支付统一下单
     * @return
     */
    public static String getUnifiedOrder() {
        return UNIFIED_ORDER;
    }

    /**
     * 微信支付查询订单
     * @return
     */
    public static String getOrderQuery() {
        return ORDER_QUERY;
    }

    /**
     * 微信支付关闭订单
     * @return
     */
    public static String getCloseOrder() {
        return CLOSE_ORDER;
    }

    /**
     * 微信支付退款
     * @return
     */
    public static String getRefund() {
        return REFUND;
    }

    /**
     * 现金红包
     * @return
     */
    public static String getSendRedPack() {
        return SEND_REDPACK;
    }

    /**
     * 查询红包
     * @return
     */
    public static String getHbInfo() {
        return REDPACK_INFO;
    }

    /**
     * 裂变红包
     * @return
     */
    public static String getSendGroupRedPack() {
        return GROUP_REDPACK;
    }

    /**
     * 企业付款
     * @return
     */
    public static String getPromotionTransfers() {
        return PROMOTION_TRANSFERS;
    }

    /**
     * 刷卡支付
     * @return
     */
    public static String getMicroPay() {
        return MICRO_PAY;
    }

}
