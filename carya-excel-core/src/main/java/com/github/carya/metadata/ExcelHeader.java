/**
 * Copyright (c) YunTrex Corporation. All rights reserved.
 * <p>
 * This software is the proprietary information of YunTrex Corporation.
 * Use is subject to license terms.
 */
package com.github.carya.metadata;

import com.github.carya.annotation.ExcelNoHeader;
import com.github.carya.annotation.ExcelStrategies;
import com.github.carya.annotation.ExcelStrategy;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Excel标题栏对象
 *
 * @author stevie.wong
 * @version 3.0, 2019-01-23 17:04
 */
public class ExcelHeader {
    /**
     * 默认读取/写入策略名称
     */
    public static final String DEFAULT_STRATEGY = "##default";

    /**
     * 对象class
     */
    private Class<?> headerClazz;

    /**
     * 读取/写入策略，see {@link com.github.carya.annotation.ExcelStrategy} & {@link com.github.carya.annotation.ExcelStrategies}
     */
    private Map<String, List<ExcelHeaderField>> strategyMap;

    /**
     * 是否存在标题行
     */
    private boolean existsHeader;

    /**
     * 组装{@link ExcelHeader}
     * @param headerClazz Class
     * @return see {@link ExcelHeader}
     */
    public static ExcelHeader of(Class<?> headerClazz) {
        ExcelHeader excelHeader = null;

        // 获取ExcelStrategy or ExcelStrategies标注
        Map<String, List<String>> strategies = Collections.emptyMap();
        if (headerClazz.isAnnotationPresent(ExcelStrategy.class)) {
            processExcelStrategyAnnotation(strategies, headerClazz);
        } else if (headerClazz.isAnnotationPresent(ExcelStrategies.class)) {
            ExcelStrategies excelStrategies = headerClazz.getAnnotation(ExcelStrategies.class);
            if (excelStrategies.value() != null && excelStrategies.value().length > 0) {
                for (ExcelStrategy excelStrategy : excelStrategies.value()) {
                    processExcelStrategyAnnotation(strategies, headerClazz);
                }
            }
        }

        // 获取ExcelNoHeader标注
        boolean existsHeader = true;
        if (headerClazz.isAnnotationPresent(ExcelNoHeader.class)) {
            existsHeader = false;
        }

        // 获取ExcelField标注
        List<Field> fields = new ArrayList<>();
        for (Class<?> clazz = headerClazz; clazz != Object.class; clazz = clazz.getSuperclass()) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        }

        if (!fields.isEmpty()) {
            Map<String, List<ExcelHeaderField>> strategyMap = new HashMap<>(1);
            boolean existsStrategy = !strategies.isEmpty();
            if (existsStrategy) {
                strategies.forEach((k, v) -> strategyMap.put(k, transform(fields, v)));
            } else {
                strategyMap.put(DEFAULT_STRATEGY, transform(fields, null));
            }

            excelHeader = new ExcelHeader(headerClazz, strategyMap, existsHeader);
        }

        return excelHeader;
    }

    /**
     * 处理ExcelStrategy标注
     * @param strategyMap 读取/写入策略Map
     * @param headerClazz 对象class
     */
    private static void processExcelStrategyAnnotation(Map<String, List<String>> strategyMap, Class<?> headerClazz) {
        ExcelStrategy excelStrategy = headerClazz.getAnnotation(ExcelStrategy.class);
        strategyMap.put(excelStrategy.name(), Arrays.asList(excelStrategy.value()));
    }

    /**
     * 转换表头字段
     * @param fields 字段列表
     * @param includes 包含此字段列表
     * @return
     */
    private static List<ExcelHeaderField> transform(List<Field> fields, List<String> includes) {
        List<ExcelHeaderField> headerFields = fields.stream().map(field -> {
            if (includes == null || includes.contains(field.getName())) {
                return ExcelHeaderField.of(field);
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());

        // 排序
        if (!headerFields.isEmpty()) {
            Collections.sort(headerFields);
        }

        return headerFields;
    }

    private ExcelHeader(Class<?> headerClazz, Map<String, List<ExcelHeaderField>> strategyMap, boolean existsHeader) {
        this.headerClazz = headerClazz;
        this.strategyMap = strategyMap;
        this.existsHeader = existsHeader;
    }

    /**
     * 是否有标题行
     * @return 是否有标题行
     */
    public boolean isExistsHeader() {
        return existsHeader;
    }
}
