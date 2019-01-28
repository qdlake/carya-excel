/**
 * Copyright (c) YunTrex Corporation. All rights reserved.
 * <p>
 * This software is the proprietary information of YunTrex Corporation.
 * Use is subject to license terms.
 */
package com.github.carya.annotation;

import java.lang.annotation.*;

/**
 * Excel读取/写入字段策略
 * <p>
 * 当对象未设置ExcelStrategy标注时，所有标注为ExcelField的字段，都需要导入/导出；
 * 当对象加上了ExcelStrategy标注，将根据标注中列出的字段来导入/导出。
 * 对象里未标注为ExcelField的字段，即使出现在ExcelStrategy中，也不会被导入/导出。
 * </p>
 * @author stevie.wong
 * @version 3.0, 2019-01-28 12:58
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelStrategy {
    /**
     * 策略名称
     * @return 策略名称
     */
    String name();

    /**
     * 字段列表
     * @return 字段列表
     */
    String[] value();
}
