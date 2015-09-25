/**
 * Yztz.com Inc.
 * Copyright (c) 2013-2015 All Rights Reserved.
 */

package com.yztz.stocktrade.facade.release.dto;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * @author Liang Chenye
 * @version $Id: Result, v 0.1 2015/7/3 9:19
 */
@XmlRootElement
@XmlSeeAlso({SchemeInfo.class, PageList.class})
public class Result<T> {

    /**
     * Error code. 0 for success, anything else for fail.
     */
    private int errorCode = 0;

    /**
     * Error message if there is any error occurs.
     */
    private String errorMsg = "";

    /**
     * Result.
     */
    private T result;

    public Result() {
    }

    public Result(T result) {
        this.result = result;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Result result1 = (Result) o;

        if (errorCode != result1.errorCode)
            return false;
        if (errorMsg != null ? !errorMsg.equals(result1.errorMsg) : result1.errorMsg != null)
            return false;
        return !(result != null ? !result.equals(result1.result) : result1.result != null);

    }

    @Override
    public int hashCode() {
        int result1 = errorCode;
        result1 = 31 * result1 + (errorMsg != null ? errorMsg.hashCode() : 0);
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        return result1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Result, {");
        sb.append("errorCode=");
        sb.append(errorCode);
        sb.append(", result=");
        sb.append(result);
        sb.append('}');
        return sb.toString();
    }
}
