/**
 * Copyright (c) YunTrex Corporation. All rights reserved.
 * <p>
 * This software is the proprietary information of YunTrex Corporation.
 * Use is subject to license terms.
 */
package com.github.carya.metadata;

import com.github.carya.annotation.ExcelField;

import java.lang.reflect.Field;

/**
 * Excel标题列对象
 *
 * @author stevie.wong
 * @version 3.0, 2019-01-23 15:31
 */
public class ExcelHeaderField implements Comparable<ExcelHeaderField> {

    /**
     * 字段
     */
    private Field field;

    /**
     * 标题
     */
    private String title;

    /**
     * 国际化标签
     */
    private String label;

    /**
     * 列位置
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
     * 根据{@link Field}标注生成{@link ExcelHeaderField}对象
     * @param field 对象字段
     * @return see {@link ExcelHeaderField}
     */
    public static ExcelHeaderField of(Field field) {
        ExcelHeaderField excelHeaderField = null;
        if (field != null && field.isAnnotationPresent(ExcelField.class)) {
            ExcelField excelField = field.getAnnotation(ExcelField.class);
            excelHeaderField = new ExcelHeaderField(field, excelField.title(), excelField.label(),
                    excelField.index(), excelField.width(), excelField.format());
        }

        return excelHeaderField;
    }

    @Override
    public int compareTo(ExcelHeaderField o) {
        int x = this.index;
        int y = o.getIndex();
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }

    private ExcelHeaderField(Field field, String title, String label, int index, short width, String format) {
        this.field = field;
        this.title = title;
        this.label = label;
        this.index = index;
        this.width = width;
        this.format = format;
    }

    public Field getField() {
        return field;
    }

    public String getTitle() {
        return title;
    }

    public String getLabel() {
        return label;
    }

    public int getIndex() {
        return index;
    }

    public short getWidth() {
        return width;
    }

    public String getFormat() {
        return format;
    }
}
