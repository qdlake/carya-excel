/**
 * Copyright (c) YunTrex Corporation. All rights reserved.
 * <p>
 * This software is the proprietary information of YunTrex Corporation.
 * Use is subject to license terms.
 */
package com.github.carya.writer;

import com.github.carya.metadata.ExcelFileType;
import com.github.carya.util.ExcelUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Collection;

/**
 * 接口{@link ExcelWriter}的标准实现
 *
 * @author stevie.wong
 * @version 3.0, 2019-01-28 11:32
 */
public class StandardExcelWriter implements ExcelWriter {
    @Override
    public Workbook writeExcel(String fileName, ExcelFileType fileType, String strategyName, Collection<ExcelWriterBucket<?>> buckets) {
        // 创建workbook
        Workbook workbook = ExcelUtils.buildWorkbook(fileType);

        // 生成excel
        buckets.forEach(bucket -> {
            Sheet sheet = workbook.createSheet(bucket.getName());

        });
        return null;
    }
}
