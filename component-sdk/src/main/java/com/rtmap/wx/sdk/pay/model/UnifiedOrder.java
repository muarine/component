package com.rtmap.wx.sdk.pay.model;


import com.rtmap.core.exp.RtmapConnectException;
import com.rtmap.core.exp.RtmapInvalidException;
import com.rtmap.core.exp.RtmapPayException;
import com.rtmap.wx.sdk.pay.core.PayAPI;
import com.rtmap.wx.sdk.pay.core.PayHandler;

import java.util.Map;

/**
 * UnifiedOrder
 *
 * @author Muarine<maoyun@rtmap.com>
 * @date 2016 10/21/16 17:52
 * @since 2.0.0
 */
public class UnifiedOrder extends PayHandler {


    private String tradeType;
    private String prepayId;
    private String codeUrl;

    /**
     * 发起预下单请求
     *
     * @param requestParam
     * @return
     */
    public static UnifiedOrder create(Map<String ,Object> requestParam , String key) throws RtmapPayException, RtmapConnectException, RtmapInvalidException {

        return request(PayAPI.getUnifiedOrder() , requestParam ,key , UnifiedOrder.class);
    }


    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }
}
