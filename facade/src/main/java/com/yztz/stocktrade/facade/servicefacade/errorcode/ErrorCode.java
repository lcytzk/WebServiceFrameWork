/**
 * Yztz.com Inc.
 * Copyright (c) 2013-2015 All Rights Reserved.
 *
 */

package com.yztz.stocktrade.facade.servicefacade.errorcode;

/**
 * @author Liang Chenye
 * @version $Id: ErrorCode, v 0.1 2015/7/3 9:32
 */

public enum ErrorCode {
    SUCCESS(0),
    NO_SUCH_ITEM(-1, "无此记录"),
    SYSTEM_ERROR(-1000, "系统错误");

    private int code;
    private String msg;

    ErrorCode(int code) {
        this.code = code;
        msg = "";
    }

    ErrorCode(int code, String msg) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}
