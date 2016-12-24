package com.pansijing.common.network;

import java.io.Serializable;

/**
 * @desc 网络请求解析类
 * Created by guomaojian on 16/10/12.
 */

public class LocalResponse<O extends Object> implements Serializable {

    private int resultcode;
    private String reason;
    private int error_code;
    private O result;

    public int getResultcode() {
        return resultcode;
    }

    public void setResultcode(int resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public O getResult() {
        return result;
    }

    public void setResult(O result) {
        this.result = result;
    }
}
