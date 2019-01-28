/**
 * Copyright (c) YunTrex Corporation. All rights reserved.
 * <p>
 * This software is the proprietary information of YunTrex Corporation.
 * Use is subject to license terms.
 */
package com.github.carya.writer;

import com.github.carya.metadata.ExcelHeader;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;

/**
 * Excel数据写入模型
 *
 * @author stevie.wong
 * @version 3.0, 2019-01-25 16:45
 */
public class ExcelWriterBucket<E> {
    /**
     * sheet名称
     */
    private final String name;

    /**
     * 表头
     */
    private final ExcelHeader header;

    /**
     * 数据
     */
    private final Collection<E> entries;


    /**
     * 组装{@link ExcelWriterBucket}
     * @param name sheet名称
     * @param entries 数据
     * @return see {@link ExcelWriterBucket}
     */
    public static <E> ExcelWriterBucket of(String name, Collection<E> entries) {
        return new ExcelWriterBucket(name, entries);
    }

    @SuppressWarnings("unchecked")
    private ExcelWriterBucket(String name, Collection<E> entries) {
        this.name = name;
        this.entries = entries;

        Class<E> clazz = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        header = ExcelHeader.of(clazz);
    }

    /**
     * 返回sheet名称
     * @return sheet名称
     */
    public String getName() {
        return name;
    }

    /**
     * 返回表头
     * @return 表头
     */
    public ExcelHeader getHeader() {
        return header;
    }

    /**
     * 返回数据
     * @return 数据
     */
    public Collection<E> getEntries() {
        return entries;
    }
}
