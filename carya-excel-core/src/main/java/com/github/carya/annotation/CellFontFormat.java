/**
 * Copyright (c) YunTrex Corporation. All rights reserved.
 * <p>
 * This software is the proprietary information of YunTrex Corporation.
 * Use is subject to license terms.
 */
package com.github.carya.annotation;

import org.apache.poi.ss.usermodel.IndexedColors;

import java.lang.annotation.*;

/**
 * 单元格字体样式
 * <p>参考https://github.com/alibaba/easyexcel/issues/205</p>
 *
 * @author stevie.wong
 * @version 3.0, 2019-01-29 16:02
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CellFontFormat {
    /**
     * 字体，默认为宋体
     * @return 字体
     */
    String value() default "宋体";

    /**
     * 字号，默认为11
     * @return 字号
     */
    short heightInPoints() default 11;

    /**
     * 字体颜色，默认为黑色
     * @return 字体颜色
     */
    IndexedColors color() default IndexedColors.BLACK;

    /**
     * 是否粗体，默认为否
     * @return 是否粗体
     */
    boolean bold() default false;

    /**
     * 是否斜体，默认为否
     * @return 是否斜体
     */
    boolean italic() default false;
}
