/**
 * Copyright (c) YunTrex Corporation. All rights reserved.
 * <p>
 * This software is the proprietary information of YunTrex Corporation.
 * Use is subject to license terms.
 */
package com.github.carya.writer;

import com.github.carya.metadata.ExcelFileType;
import com.github.carya.metadata.ExcelHeader;
import com.github.carya.util.ExcelUtils;
import org.apache.poi.ss.usermodel.*;

import java.util.Collection;
import java.util.HashMap;

/**
 * 接口{@link ExcelWriter}的标准实现
 *
 * @author stevie.wong
 * @version 3.0, 2019-01-28 11:32
 */
public class StandardExcelWriter implements ExcelWriter {
    protected static HashMap<String, CellStyle> styleMap = new HashMap<>();

    @Override
    public Workbook writeExcel(String fileName, ExcelFileType fileType, String strategyName, Collection<ExcelWriterBucket<?>> buckets) {
        // 创建workbook
        Workbook workbook = ExcelUtils.buildWorkbook(fileType);
        applyStyle(workbook);

        // 生成excel
        buckets.forEach(bucket -> {
            Sheet sheet = workbook.createSheet(bucket.getName());

            ExcelHeader header = bucket.getHeader();

            // 是否生成标题
            if (header.isNeedHeader()) {
                Row row = sheet.createRow(0);
            }

        });

        return null;
    }

    protected void applyStyle(Workbook workbook) {

    }
}
