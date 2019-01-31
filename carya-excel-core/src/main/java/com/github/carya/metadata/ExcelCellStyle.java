/**
 * Copyright (c) YunTrex Corporation. All rights reserved.
 * <p>
 * This software is the proprietary information of YunTrex Corporation.
 * Use is subject to license terms.
 */
package com.github.carya.metadata;

import com.github.carya.annotation.CellStyleFormat;
import org.apache.poi.ss.usermodel.*;

import java.util.function.Consumer;

/**
 * 单元格样式
 *
 * @author stevie.wong
 * @version 3.0, 2019-01-30 11:20
 */
public class ExcelCellStyle {
    /**
     * 水平居中方式
     */
    private HorizontalAlignment align;

    /**
     * 垂直居中方式
     */
    private VerticalAlignment valign;

    /**
     * 边框样式
     */
    private BorderStyle borderStyle;

    /**
     * 边框颜色
     */
    private IndexedColors borderColor;

    /**
     * 背景颜色
     */
    private IndexedColors bgColor;

    /**
     * 单元格字体
     */
    private ExcelCellFont font;

    /**
     * 是否换行
     */
    private boolean wrapText;

    /**
     * 创建单元格样式
     * @param buildingFunction 组装函数
     * @return see {@link ExcelCellStyle}
     */
    public static ExcelCellStyle create(Consumer<Builder> buildingFunction) {
        return new ExcelCellStyle(buildingFunction);
    }

    /**
     * 创建默认单元格样式
     * @return see {@link ExcelCellStyle}
     */
    public static ExcelCellStyle create() {
        return create(null);
    }

    /**
     * 创建单元格样式，format为空则创建默认单元格样式
     * @param format see {@link CellStyleFormat}
     * @return see {@link ExcelCellStyle}
     */
    public static ExcelCellStyle of(CellStyleFormat format) {
        if (null == format) {
            return create();
        }

        return create(builder ->
            builder.withAlign(format.align())
                .withValign(format.valign())
                .withBorderStyle(format.borderStyle())
                .withBorderColor(format.borderColor())
                .withBgColor(format.bgColor())
                .withFont(ExcelCellFont.of(format.font()))
                .withWrapText(format.wrapText())
        );
    }

    private ExcelCellStyle(Consumer<Builder> buildingFunction) {
        this.align = HorizontalAlignment.LEFT;
        this.valign = VerticalAlignment.CENTER;
        this.borderStyle = BorderStyle.NONE;
        this.borderColor = IndexedColors.WHITE;
        this.bgColor = IndexedColors.WHITE;
        this.font = ExcelCellFont.create();
        this.wrapText = false;

        if (null == buildingFunction) {
            return;
        }

        buildingFunction.accept(new Builder() {
            @Override
            public Builder withAlign(HorizontalAlignment align) {
                ExcelCellStyle.this.align = align;
                return this;
            }

            @Override
            public Builder withValign(VerticalAlignment valign) {
                ExcelCellStyle.this.valign = valign;
                return this;
            }

            @Override
            public Builder withBorderStyle(BorderStyle borderStyle) {
                ExcelCellStyle.this.borderStyle = borderStyle;
                return this;
            }

            @Override
            public Builder withBorderColor(IndexedColors borderColor) {
                ExcelCellStyle.this.borderColor = borderColor;
                return this;
            }

            @Override
            public Builder withBgColor(IndexedColors bgColor) {
                ExcelCellStyle.this.bgColor = bgColor;
                return this;
            }

            @Override
            public Builder withFont(ExcelCellFont font) {
                ExcelCellStyle.this.font = font;
                return this;
            }

            @Override
            public Builder withWrapText(boolean wrapText) {
                ExcelCellStyle.this.wrapText = wrapText;
                return this;
            }
        });
    }

    public interface Builder {
        /**
         * 设置水平居中方式
         * @param align 水平居中方式
         * @return
         */
        Builder withAlign(HorizontalAlignment align);

        /**
         * 设置垂直居中方式
         * @param valign 垂直居中方式
         * @return
         */
        Builder withValign(VerticalAlignment valign);

        /**
         * 设置边框样式
         * @param borderStyle 边框样式
         * @return
         */
        Builder withBorderStyle(BorderStyle borderStyle);

        /**
         * 设置边框颜色
         * @param borderColor 边框颜色
         * @return
         */
        Builder withBorderColor(IndexedColors borderColor);

        /**
         * 设置背景色
         * @param bgColor 背景色
         * @return
         */
        Builder withBgColor(IndexedColors bgColor);

        /**
         * 设置字体
         * @param font 字体
         * @return
         */
        Builder withFont(ExcelCellFont font);

        /**
         * 设置是否换行
         * @param wrapText 是否换行
         * @return
         */
        Builder withWrapText(boolean wrapText);
    }

    /**
     * 装饰样式
     * @param cellStyle POI单元格样式
     * @param font POI字体
     * @return see {@link CellStyle}
     */
    public CellStyle decorate(CellStyle cellStyle, Font font) {
        // 居中方式
        cellStyle.setAlignment(getAlign());
        cellStyle.setVerticalAlignment(getValign());

        // 设置边框
        cellStyle.setBorderBottom(getBorderStyle());
        cellStyle.setBorderLeft(getBorderStyle());
        cellStyle.setBorderRight(getBorderStyle());
        cellStyle.setBorderTop(getBorderStyle());
        cellStyle.setBottomBorderColor(getBorderColor().getIndex());
        cellStyle.setLeftBorderColor(getBorderColor().getIndex());
        cellStyle.setRightBorderColor(getBorderColor().getIndex());
        cellStyle.setTopBorderColor(getBorderColor().getIndex());

        // 设置字体
        cellStyle.setFont(getFont().decorate(font));

        // 设置单元格是否换行
        cellStyle.setWrapText(isWrapText());

        // 背景颜色
        cellStyle.setFillBackgroundColor(getBgColor().getIndex());

        return cellStyle;
    }

    /**
     * 返回水平居中方式
     * @return 水平居中方式
     */
    public HorizontalAlignment getAlign() {
        return align;
    }

    /**
     * 返回垂直居中方式
     * @return 垂直居中方式
     */
    public VerticalAlignment getValign() {
        return valign;
    }

    /**
     * 返回边框样式
     * @return 边框样式
     */
    public BorderStyle getBorderStyle() {
        return borderStyle;
    }

    /**
     * 返回边框颜色
     * @return 边框颜色
     */
    public IndexedColors getBorderColor() {
        return borderColor;
    }

    /**
     * 返回背景颜色
     * @return 背景颜色
     */
    public IndexedColors getBgColor() {
        return bgColor;
    }

    /**
     * 返回字体
     * @return 字体
     */
    public ExcelCellFont getFont() {
        return font;
    }

    /**
     * 是否换行
     * @return 是否换行
     */
    public boolean isWrapText() {
        return wrapText;
    }
}
