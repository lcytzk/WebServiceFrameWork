/**
 * Yztz.com Inc.
 * Copyright (c) 2013-2015 All Rights Reserved.
 */
package com.yztz.stocktrade.facade.domain;

/**
 * @author hao
 * @version $Id: TradeActionEnum.java, v 0.1 Mar 19, 2015 9:47:33 AM hao Exp $
 */
public enum TradeActionEnum {
    /**
     * 买入
     */
    BUY("BUY", "买入"),
    /**
     * 卖出
     */
    SELL("SELL", "卖出"),
    /**
     * 斩获
     */
    PROFIT("PROFIT", "盈利"),
    /**
     * 申请
     */
    APPLY("APPLY", "申请");

    private String value;

    private String message;

    private TradeActionEnum(String value, String message) {
        this.value = value;
        this.message = message;

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static TradeActionEnum getByValue(String value) {
        if (value != null) {
            for (TradeActionEnum enu : values()) {
                if (enu.value.equals(value)) {
                    return enu;
                }
            }
        }
        return null;
    }

    public static TradeActionEnum getByMessage(String msg) {
        if (msg != null) {
            for (TradeActionEnum enu : values()) {
                if (enu.message.equals(msg)) {
                    return enu;
                }
            }
        }
        return null;
    }

    /**
     * Getter method for property <tt>message</tt>.
     *
     * @return property value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter method for property <tt>message</tt>.
     *
     * @param message value to be assigned to property message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return value;
    }

}
