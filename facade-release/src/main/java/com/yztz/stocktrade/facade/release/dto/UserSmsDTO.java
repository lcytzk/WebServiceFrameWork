/**
 * Yztz.com Inc.
 * Copyright (c) 2013-2015 All Rights Reserved.
 */

package com.yztz.stocktrade.facade.release.dto;

import java.io.Serializable;

/**
 * @author Liang Chenye
 * @version $Id: UserSmsDTO, v 0.1 2015/6/23 12:54
 */
public class UserSmsDTO implements Serializable {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 发送原因
     */
    private String cause;

    /**
     * 短信内容
     */
    private String msg;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UserSmsDTO, {");
        sb.append("userId=");
        sb.append(userId);
        sb.append(", cause=");
        sb.append(cause);
        sb.append(", msg=");
        sb.append(msg);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        UserSmsDTO that = (UserSmsDTO) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null)
            return false;
        if (cause != null ? !cause.equals(that.cause) : that.cause != null)
            return false;
        return !(msg != null ? !msg.equals(that.msg) : that.msg != null);

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (cause != null ? cause.hashCode() : 0);
        result = 31 * result + (msg != null ? msg.hashCode() : 0);
        return result;
    }
}
