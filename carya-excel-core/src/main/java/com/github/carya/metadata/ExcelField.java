/**
 * Copyright (c) YunTrex Corporation. All rights reserved.
 * <p>
 * This software is the proprietary information of YunTrex Corporation.
 * Use is subject to license terms.
 */
package com.github.carya.metadata;

import com.github.carya.annotation.CellStyleFormat;
import com.github.carya.annotation.ExcelColumn;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.util.StringUtil;

import java.lang.reflect.Field;
import java.util.function.Consumer;

/**
 * Excel标题列对象
 *
 * @author stevie.wong
 * @version 3.0, 2019-01-23 15:31
 */
public class ExcelField implements Comparable<ExcelField> {
    /**
     * 标题
     */
    private String title;

    /**
     * 列号
     */
    private int index;

    /**
     * 列宽度
     */
    private short width;

    /**
     * 格式化
     */
    private String format;

    /**
     * 单元格样式
     */
    private ExcelCellStyle style;

    /**
     * 根据{@link Field}标注生成{@link ExcelField}对象
     * @param field 对象字段
     * @return see {@link ExcelField}
     */
    public static ExcelField of(Field field) {
        ExcelField excelField = null;
        if (null != field) {
            if (field.isAnnotationPresent(ExcelColumn.class)) {
                ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);

                excelField = create(builder ->
                    builder.withTitle(excelColumn.title())
                        .withIndex(excelColumn.index())
                        .withWidth(excelColumn.width())
                        .withFormat(excelColumn.format())
                        .withStyle(ExcelCellStyle.of(field.getAnnotation(CellStyleFormat.class)))
                );
            }
        }

        return excelField;
    }

    /**
     * 创建Excel标题列对象
     * @param buildingFunction 组装函数
     * @return see {@link ExcelField}
     */
    public static ExcelField create(Consumer<Builder> buildingFunction) {
        return new ExcelField(buildingFunction);
    }

    public interface Builder {
        /**
         * 设置标题
         * @param title 标题
         * @return
         */
        Builder withTitle(String title);

        /**
         * 设置列号
         * @param index 列号
         * @return
         */
        Builder withIndex(int index);

        /**
         * 设置列宽
         * @param width 列宽
         * @return
         */
        Builder withWidth(short width);

        /**
         * 设置格式化
         * @param format 格式化
         * @return
         */
        Builder withFormat(String format);

        /**
         * 设置单元格样式
         * @param style 单元格样式
         * @return
         */
        Builder withStyle(ExcelCellStyle style);
    }

    /**
     * implement Comparable interface
     * @param o see {@link ExcelField}
     * @return
     */
    @Override
    public int compareTo(ExcelField o) {
        int x = this.index;
        int y = o.getIndex();
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }

    private ExcelField(Consumer<Builder> buildingFunction) {
        if (null == buildingFunction) {
            return;
        }

        buildingFunction.accept(new Builder() {
            @Override
            public Builder withTitle(String title) {
                ExcelField.this.title = title;
                return this;
            }

            @Override
            public Builder withIndex(int index) {
                ExcelField.this.index = index;
                return this;
            }

            @Override
            public Builder withWidth(short width) {
                ExcelField.this.width = width;
                return this;
            }

            @Override
            public Builder withFormat(String format) {
                ExcelField.this.format = format;
                return this;
            }

            @Override
            public Builder withStyle(ExcelCellStyle style) {
                ExcelField.this.style = style;
                return this;
            }
        });
    }

    public CellStyle decorate(CellStyle cellStyle, DataFormat dataFormat) {
        if (StringUtils.isNotBlank(getFormat())) {
            cellStyle.setDataFormat(dataFormat.getFormat(getFormat()));
        }
        return cellStyle;
    }

    /**
     * 返回标题
     * @return 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 返回列号(从0开始)
     * @return 列号
     */
    public int getIndex() {
        return index;
    }

    /**
     * 返回列宽
     * @return 列宽
     */
    public short getWidth() {
        return width;
    }

    /**
     * 返回格式化
     * @return 格式化
     */
    public String getFormat() {
        return format;
    }

    /**
     * 返回单元格样式
     * @return 单元格样式
     */
    public ExcelCellStyle getStyle() {
        return style;
    }
}
