/**
 * Copyright (c) YunTrex Corporation. All rights reserved.
 * <p>
 * This software is the proprietary information of YunTrex Corporation.
 * Use is subject to license terms.
 */
package com.github.carya.metadata;

/**
 * 列数据类型
 *
 * @author stevie.wong
 * @version 3.0, 2019-01-23 14:37
 */
public enum FieldDataType {
    /**
     * 空
     */
    BLANK,

    /**
     * 文本
     */
    TEXT,

    /**
     * 整数
     */
    INTEGER,

    /**
     * 小数
     */
    NUMERIC,

    /**
     * 日期
     */
    DATE,

    /**
     * 日期时间
     */
    DATETIME,

    /**
     * 布尔
     */
    BOOLEAN
}
