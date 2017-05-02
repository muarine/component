package com.rtmap.wx.sdk.pay.core;

import java.util.List;
import java.util.Map;

/**
 * RtmapResponse
 *
 * @author Muarine<maoyun@rtmap.com>
 * @date 2016 10/21/16 18:42
 * @since 2.0.0
 */
public class RtmapResponse {

    private int errcode;
    private String result;
    private Map<String , List<String>> headers;

    public RtmapResponse(int errcode, String result, Map<String, List<String>> headers) {
        this.errcode = errcode;
        this.result = result;
        this.headers = headers;
    }

    public RtmapResponse(int errcode, String result) {
        this.errcode = errcode;
        this.result = result;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }
}
