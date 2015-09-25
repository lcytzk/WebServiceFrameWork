/**
 * Yztz.cn Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package org.yolk.common.constants;

/**
 * 一些系统级别的参数常量
 *
 * @author elliott
 * @version $Id: SystemConstants.java, v 0.1 2013-12-31 下午3:14:27 elliott Exp $
 */
public interface SystemConstants {

    /**
     * 缓存单元信息
     */
    String STOCK_UNIT_CACHE_PREFIX = "HOMS_STOCK_UNIT#";

    /**
     * 缓存单元信息快照
     */
    String STOCK_UNIT_CACHE_SNAPSHOOT_PREFIX = "HOMS_STOCK_UNIT_SNAPSHOOT#";

    /**
     * 国家规定的法定节假日
     */
    String HOLIDAYS_AND_FESTIVALS_NAME = "HOLIDAYS_AND_FESTIVALS";

    /**
     * Stock trade data server base url.
     * "$baseUrl/serviceName"
     */
    String STOCK_TRADE_DATA_SERVER_BASE_URL = "STOCK_TRADE_DATA_SERVER_BASE_URL";

    /**
     * Stock trade data server port.
     */
    String STOCK_TRADE_DATA_SERVER_PORT = "STOCK_TRADE_DATA_SERVER_PORT";

    /**
     * 交易广播数据缓存
     */
    String TRADE_BROADCAST_DATA_CACHE = "TRADE_BROADCAST_DATA_CACHE";

    /**
     * 达人数据缓存
     */
    String FASHION_ROLL_DATA_CACHE = "FASHION_ROLL_DATA_CACHE";

    /**
     * 按日配资数据缓存
     */
    String EVERWIN_ROLL_DATA_CACHE = "EVERWIN_ROLL_DATA_CACHE";

    /**
     * 按周配资数据缓存
     */
    String WEEKWIN_ROLL_DATA_CACHE = "WEEKWIN_ROLL_DATA_CACHE";

    /**
     * 按月配资数据缓存
     */
    String MONTHWIN_ROLL_DATA_CACHE = "MONTHWIN_ROLL_DATA_CACHE";

    /**
     * 极惠配资数据缓存
     */
    String FAVOUR_ROLL_DATA_CACHE = "FAVOUR_ROLL_DATA_CACHE";

    /**
     * 免费体验数据缓存
     */
    String EXPERIENCE_ROLL_DATA_CACHE = "EXPERIENCE_ROLL_DATA_CACHE";

    /**
     * 实盘大赛数据缓存
     */
    String MATCH_ROLL_DATA_CACHE = "MATCH_ROLL_DATA_CACHE";

    /**
     * 赢币配资数据缓存
     */
    String YINIG_ROLL_DATA_CACHE = "YING_ROLL_DATA_CACHE";
}
