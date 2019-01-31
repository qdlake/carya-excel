/**
 * Copyright (c) YunTrex Corporation. All rights reserved.
 * <p>
 * This software is the proprietary information of YunTrex Corporation.
 * Use is subject to license terms.
 */
package com.github.carya.util;

import com.github.carya.exception.ExcelValidationException;
import com.github.carya.metadata.ExcelFileType;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

/**
 * Excel工具类
 *
 * @author stevie.wong
 * @version 3.0, 2019-01-25 16:54
 */
public class ExcelUtils {
    /**
     * 根据excel文件类型，创建相应的Workbook
     * @param fileType excel文件类型
     * @param in 输入流
     * @return see {@link Workbook}
     * @throws ExcelValidationException
     */
    public static Workbook buildWorkbook(ExcelFileType fileType, InputStream in) throws ExcelValidationException {
        Workbook workbook = null;
        try {
            switch (fileType) {
                case XLS:
                    workbook = null != in ? new HSSFWorkbook(new POIFSFileSystem(in)) : new HSSFWorkbook();
                    break;
                case XLSX:
                    workbook = null != in ? new SXSSFWorkbook(new XSSFWorkbook(in)) : new SXSSFWorkbook(500);
                    break;
            }
        } catch (IOException e) {
            throw new ExcelValidationException("创建Excel工作薄错误", e);
        }
        return workbook;
    }

    /**
     * 根据excel文件类型，创建相应的创建Workbook
     * @param fileType excel文件类型
     * @return see {@link Workbook}
     * @throws ExcelValidationException
     */
    public static Workbook buildWorkbook(ExcelFileType fileType) throws ExcelValidationException {
        return buildWorkbook(fileType, null);
    }
}
