/**
 * Copyright (c) YunTrex Corporation. All rights reserved.
 * <p>
 * This software is the proprietary information of YunTrex Corporation.
 * Use is subject to license terms.
 */
package com.github.carya.metadata;

import org.apache.poi.poifs.filesystem.FileMagic;

import java.io.IOException;
import java.io.InputStream;

/**
 * Excel文件类型
 *
 * @author stevie.wong
 * @version 3.0, 2019-01-23 14:54
 */
public enum ExcelFileType {
    /**
     * xls(Microsoft Excel 2007前excel的文件存储格式)
     */
    XLS(".xls"),

    /**
     * xlxs(Microsoft Excel 2007后excel的文件存储格式, 基于openXml和zip技术)
     */
    XLSX(".xlsx");

    private String extension;
    ExcelFileType(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return this.extension;
    }

    /**
     * 返回输入流Excel文件类型
     * @param inputStream 输入流
     * @return Excel文件类型
     */
    public static ExcelFileType valueOf(InputStream inputStream){
        try {
            if (!inputStream.markSupported()) {
                return null;
            }
            FileMagic fileMagic =  FileMagic.valueOf(inputStream);
            if(FileMagic.OLE2.equals(fileMagic)){
                return XLS;
            }
            if(FileMagic.OOXML.equals(fileMagic)){
                return XLSX;
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
