/**
 * Copyright (c) YunTrex Corporation. All rights reserved.
 * <p>
 * This software is the proprietary information of YunTrex Corporation.
 * Use is subject to license terms.
 */
package com.github.carya.metadata;

import com.github.carya.annotation.CellFontFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.util.function.Consumer;

/**
 * 单元格字体
 *
 * @author stevie.wong
 * @version 3.0, 2019-01-30 13:29
 */
public class ExcelCellFont {
    /**
     * 字体名称
     */
    private String name;

    /**
     * 字号
     */
    private short heightInPoints;

    /**
     * 字体颜色
     */
    private IndexedColors color;

    /**
     * 粗体
     */
    private boolean bold;

    /**
     * 斜体
     */
    private boolean italic;

    /**
     * 创建单元格字体
     * @param buildingFunction 组装函数
     * @return see {@link ExcelCellFont}
     */
    public static ExcelCellFont create(Consumer<Builder> buildingFunction) {
        return new ExcelCellFont(buildingFunction);
    }

    /**
     * 创建默认单元格字体
     * @return see {@link ExcelCellFont}
     */
    public static ExcelCellFont create() {
        return create(null);
    }

    /**
     * 创建单元格字体，format为空则创建默认单元格字体
     * @param format see {@link CellFontFormat}
     * @return see {@link ExcelCellFont}
     */
    public static ExcelCellFont of(CellFontFormat format) {
        if (null == format) {
            return create();
        }

        return create(builder ->
           builder.withName(format.value())
               .withHeightInPoints(format.heightInPoints())
               .withColor(format.color())
               .withBold(format.bold())
               .withItalic(format.italic())
        );
    }

    private ExcelCellFont(Consumer<Builder> buildingFunction) {
        this.name = "宋体";
        this.heightInPoints = 11;
        this.color = IndexedColors.BLACK;
        this.bold = false;
        this.italic = false;

        if (null == buildingFunction) {
            return;
        }

        buildingFunction.accept(new Builder() {
            @Override
            public Builder withName(String name) {
                ExcelCellFont.this.name = name;
                return this;
            }

            @Override
            public Builder withHeightInPoints(short heightInPoints) {
                ExcelCellFont.this.heightInPoints = heightInPoints;
                return this;
            }

            @Override
            public Builder withColor(IndexedColors color) {
                ExcelCellFont.this.color = color;
                return this;
            }

            @Override
            public Builder withBold(boolean bold) {
                ExcelCellFont.this.bold = bold;
                return this;
            }

            @Override
            public Builder withItalic(boolean italic) {
                ExcelCellFont.this.italic = italic;
                return this;
            }
        });
    }

    public interface Builder {
        /**
         * 设置字体名称
         * @param name 字体名称
         * @return
         */
        Builder withName(String name);

        /**
         * 设置字号
         * @param heightInPoints 字号
         * @return
         */
        Builder withHeightInPoints(short heightInPoints);

        /**
         * 设置字体颜色
         * @param color 字体颜色
         * @return
         */
        Builder withColor(IndexedColors color);

        /**
         * 设置是否粗体
         * @param bold 是否粗体
         * @return
         */
        Builder withBold(boolean bold);

        /**
         * 设置是否斜体
         * @param italic 是否斜体
         * @return
         */
        Builder withItalic(boolean italic);
    }

    /**
     * 装饰字体
     * @param font POI字体
     * @return see {@link Font}
     */
    public Font decorate(Font font) {
        font.setFontName(getName());
        font.setFontHeightInPoints(getHeightInPoints());
        font.setColor(getColor().getIndex());
        font.setBold(isBold());
        font.setItalic(isItalic());

        return font;
    }

    /**
     * 返回字体名称
     * @return 字体名称
     */
    public String getName() {
        return name;
    }

    /**
     * 返回字号
     * @return 字号
     */
    public short getHeightInPoints() {
        return heightInPoints;
    }

    /**
     * 返回字体颜色
     * @return 字体颜色
     */
    public IndexedColors getColor() {
        return color;
    }

    /**
     * 是否粗体
     * @return 是否粗体
     */
    public boolean isBold() {
        return bold;
    }

    /**
     * 是否斜体
     * @return 是否斜体
     */
    public boolean isItalic() {
        return italic;
    }
}
