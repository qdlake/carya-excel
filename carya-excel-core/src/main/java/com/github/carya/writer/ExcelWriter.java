/**
 * Copyright (c) YunTrex Corporation. All rights reserved.
 * <p>
 * This software is the proprietary information of YunTrex Corporation.
 * Use is subject to license terms.
 */
package com.github.carya.writer;

import com.github.carya.metadata.ExcelFileType;
import com.github.carya.metadata.ExcelHeader;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Arrays;
import java.util.Collection;

/**
 * Excel写入接口
 *
 * @author stevie.wong
 * @version 3.0, 2019-01-25 15:32
 */
public interface ExcelWriter {

    /**
     * 生成Excel(一个bucket生成一个sheet)
     * @param fileName excel文件名
     * @param fileType excel文件类型
     * @param strategyName excel读取/写入策略名称
     * @param buckets 多sheet数据
     * @return see {@link Workbook}
     */
    Workbook writeExcel(String fileName, ExcelFileType fileType, String strategyName, Collection<ExcelWriterBucket<?>> buckets);

    /**
     * 按默认策略生成Excel(一个bucket生成一个sheet)
     * @param fileName excel文件名
     * @param fileType excel文件类型
     * @param buckets 多sheet数据
     * @return see {@link Workbook}
     */
    default Workbook writeExcel(String fileName, ExcelFileType fileType, Collection<ExcelWriterBucket<?>> buckets) {
        return writeExcel(fileName, fileType, ExcelHeader.DEFAULT_STRATEGY, buckets);
    }

    /**
     * 按默认策略生成只有一个Sheet的Excel
     * @param fileName excel文件名
     * @param fileType excel文件类型
     * @param bucket 单sheet数据
     * @return see {@link Workbook}
     */
    default Workbook writeExcel(String fileName, ExcelFileType fileType, ExcelWriterBucket<?> bucket) {
        return writeExcel(fileName, fileType, ExcelHeader.DEFAULT_STRATEGY, Arrays.asList(bucket));
    }
}
