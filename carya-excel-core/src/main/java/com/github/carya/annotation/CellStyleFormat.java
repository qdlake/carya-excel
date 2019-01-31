/**
 * Copyright (c) YunTrex Corporation. All rights reserved.
 * <p>
 * This software is the proprietary information of YunTrex Corporation.
 * Use is subject to license terms.
 */
package com.github.carya.annotation;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.lang.annotation.*;

/**
 * 单元格样式
 *
 * <p>>参考https://github.com/alibaba/easyexcel/issues/205</p>
 *
 * @author stevie.wong
 * @version 3.0, 2019-01-29 15:43
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CellStyleFormat {
    /**
     * 水平居中方式，默认居左
     * @return see {@link HorizontalAlignment}
     */
    HorizontalAlignment align() default HorizontalAlignment.LEFT;

    /**
     * 垂直居中方式，默认居中
     * @return see {@link VerticalAlignment}
     */
    VerticalAlignment valign() default VerticalAlignment.CENTER;

    /**
     * 边框样式，默认无样式
     * @return see {@link BorderStyle}
     */
    BorderStyle borderStyle() default BorderStyle.NONE;

    /**
     * 边框颜色，默认为白色
     * @return see {@link IndexedColors}
     */
    IndexedColors borderColor() default IndexedColors.WHITE;

    /**
     * 背景颜色，默认为白色
     * @return see {@link IndexedColors}
     */
    IndexedColors bgColor() default IndexedColors.WHITE;

    /**
     * 标题字体，默认为粗体
     * @return see {@link CellFontFormat}
     */
    CellFontFormat headerFont() default @CellFontFormat(bold = true);

    /**
     * 单元格字体
     * @return see {@link CellFontFormat}
     */
    CellFontFormat dataFont() default @CellFontFormat();

    /**
     * 单元格是否换行，默认为否
     * @return 单元格是否换行
     */
    boolean wrapText() default false;
}
