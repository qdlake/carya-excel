/**
 * Copyright (c) YunTrex Corporation. All rights reserved.
 * <p>
 * This software is the proprietary information of YunTrex Corporation.
 * Use is subject to license terms.
 */
package com.github.carya.metadata;

import com.github.carya.annotation.ExcelProperty;
import com.github.carya.annotation.ExcelStrategies;
import com.github.carya.annotation.ExcelStrategy;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;
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
     * 读取/写入策略，see {@link com.github.carya.annotation.ExcelStrategy} & {@link com.github.carya.annotation.ExcelStrategies}
     */
    private Map<String, List<ExcelField>> strategyMap;

    /**
     * 是否存在标题行
     */
    private boolean needHeader;

    /**
     * 标题是否支持国际化
     */
    private boolean i18nHeader;

    /**
     * 是否支持自适应列宽(当开启自适应列宽，则ExcelColumn中的width失效)
     */
    private boolean autoSizeColumn;

    /**
     * 组装{@link ExcelHeader}
     * @param headerClazz Class
     * @return see {@link ExcelHeader}
     */
    public static ExcelHeader of(Class<?> headerClazz) {
        ExcelHeader excelHeader = null;



        // 获取ExcelField标注
        List<Field> fields = new ArrayList<>();
        for (Class<?> clazz = headerClazz; clazz != Object.class; clazz = clazz.getSuperclass()) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        }

        if (!fields.isEmpty()) {
            Map<String, List<ExcelField>> strategyMap = new HashMap<>(1);
            strategyMap.put(DEFAULT_STRATEGY, processExcelFields(fields, null));

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

            if (!strategies.isEmpty()) {
                strategies.forEach((k, v) -> strategyMap.put(k, processExcelFields(fields, v)));
            }

            // 获取ExcelProperty
            boolean existsExcelProperty = headerClazz.isAnnotationPresent(ExcelProperty.class);
            ExcelProperty excelProperty = existsExcelProperty ? headerClazz.getAnnotation(ExcelProperty.class) : null;
            excelHeader = create(builder ->
                builder.withStrategyMap(strategyMap)
                    .withNeedHeader(existsExcelProperty ? excelProperty.needHeader() : true)
                    .withI18nHeader(existsExcelProperty ? excelProperty.i18nHeader() : false)
                    .withAutoSizeColumn(existsExcelProperty ? excelProperty.autoSizeColumn() : false)
            );
        }

        return excelHeader;
    }

    /**
     * 创建Excel标题栏对象
     * @param buildingFunction 组织函数
     * @return see {@link ExcelHeader}
     */
    public static ExcelHeader create(Consumer<Builder> buildingFunction) {
        return new ExcelHeader(buildingFunction);
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
     * @return 表头字段列表
     */
    private static List<ExcelField> processExcelFields(List<Field> fields, List<String> includes) {
        List<ExcelField> headerFields = fields.stream().map(field -> {
            if (includes == null || includes.contains(field.getName())) {
                return ExcelField.of(field);
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());

        // 排序
        if (!headerFields.isEmpty()) {
            Collections.sort(headerFields);
        }

        return headerFields;
    }

    public interface Builder {
        /**
         * 设置读取/写入策略
         * @param strategyMap 读取/写入策略
         * @return
         */
        Builder withStrategyMap(Map<String, List<ExcelField>> strategyMap);

        /**
         * 设置默认策略字段
         * @param fields 表头字段数组
         * @return
         */
        Builder withExcelFields(ExcelField... fields);

        /**
         * 设置是否需要标题行
         * @param needHeader 是否需要标题行
         * @return
         */
        Builder withNeedHeader(boolean needHeader);

        /**
         * 设置是否支持国际化
         * @param i18nHeader 是否支持国际化
         * @return
         */
        Builder withI18nHeader(boolean i18nHeader);

        /**
         * 设置是否支持自适应列宽
         * @param autoSizeColumn 是否支持自适应列宽
         * @return
         */
        Builder withAutoSizeColumn(boolean autoSizeColumn);
    }

    private ExcelHeader(Consumer<Builder> buildingFunction) {
        if (null == buildingFunction) {
            return;
        }

        strategyMap = new HashMap<>();
        buildingFunction.accept(new Builder() {
            @Override
            public Builder withStrategyMap(Map<String, List<ExcelField>> strategyMap) {
                ExcelHeader.this.strategyMap.putAll(strategyMap);
                return this;
            }

            @Override
            public Builder withExcelFields(ExcelField... fields) {
                ExcelHeader.this.strategyMap.put(DEFAULT_STRATEGY, Arrays.asList(fields));
                return this;
            }

            @Override
            public Builder withNeedHeader(boolean needHeader) {
                ExcelHeader.this.needHeader = needHeader;
                return this;
            }

            @Override
            public Builder withI18nHeader(boolean i18nHeader) {
                ExcelHeader.this.i18nHeader = i18nHeader;
                return this;
            }

            @Override
            public Builder withAutoSizeColumn(boolean autoSizeColumn) {
                ExcelHeader.this.autoSizeColumn = autoSizeColumn;
                return this;
            }
        });
    }

    /**
     * 根据策略返回字段列表
     * @param strategyName 策略名称
     * @return 字段列表
     */
    public List<ExcelField> getExcelFields(String strategyName) {
        return strategyMap.getOrDefault(strategyName, Collections.emptyList());
    }

    /**
     * 是否有标题行
     * @return 是否有标题行
     */
    public boolean isNeedHeader() {
        return needHeader;
    }

    /**
     * 标题是否支持国际化
     * @return 标题是否支持国际化
     */
    public boolean isI18nHeader() {
        return i18nHeader;
    }

    /**
     * 是否支持自适应列宽
     * @return 是否支持自适应列宽
     */
    public boolean isAutoSizeColumn() {
        return autoSizeColumn;
    }
}
