/**
 * Copyright (c) YunTrex Corporation. All rights reserved.
 * <p>
 * This software is the proprietary information of YunTrex Corporation.
 * Use is subject to license terms.
 */
package com.github.carya.annotation;

import java.lang.annotation.*;

/**
 * Excel读取/写入字段策略组
 * <p>
 * 允许一个对象配置多种读取/写入字段策略。
 * </p>
 * @author stevie.wong
 * @version 3.0, 2019-01-28 12:57
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelStrategies {
    /**
     * 策略组
     * @return 策略组
     */
    ExcelStrategy[] value();
}
