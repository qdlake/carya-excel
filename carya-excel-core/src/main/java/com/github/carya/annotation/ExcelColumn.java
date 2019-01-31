/**
 * Copyright (c) YunTrex Corporation. All rights reserved.
 * <p>
 * This software is the proprietary information of YunTrex Corporation.
 * Use is subject to license terms.
 */
package com.github.carya.annotation;

import java.lang.annotation.*;

/**
 * 标注为Excel列
 *
 * @author stevie.wong
 * @version 3.0, 2019-01-23 14:01
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ExcelColumn {
    /**(label为空时使用title作为标题)
     * @return 列标题
     */
    String title();

    /**
     * 列号(从0开始)
     * @return 列号
     */
    int index() default 99999;

    /**
     * 列宽度(当开启自适应列宽，则width失效)
     * @see ExcelProperty
     * @return 列宽度
     */
    short width() default 15;

    /**
     * 格式化
     * @return 格式化
     */
    String format() default "";
}
