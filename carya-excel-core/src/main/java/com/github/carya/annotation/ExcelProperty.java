/**
 * Copyright (c) YunTrex Corporation. All rights reserved.
 * <p>
 * This software is the proprietary information of YunTrex Corporation.
 * Use is subject to license terms.
 */
package com.github.carya.annotation;

import java.lang.annotation.*;

/**
 * Excel相关属性
 *
 * @author stevie.wong
 * @version 3.0, 2019-01-29 17:55
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelProperty {

    /**
     * 是否需要标题，默认为是
     * @return 是否需要标题
     */
    boolean needHeader() default true;

    /**
     * 标题是否支持国际化，默认为否
     * @return 标题是否支持国际化
     */
    boolean i18nHeader() default false;

    /**
     * 是否支持自适应列宽，默认为否
     * @return 是否支持自适应列宽
     */
    boolean autoSizeColumn() default false;
}
