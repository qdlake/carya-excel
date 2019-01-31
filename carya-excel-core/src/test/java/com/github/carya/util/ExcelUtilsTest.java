/**
 * Copyright (c) YunTrex Corporation. All rights reserved.
 * <p>
 * This software is the proprietary information of YunTrex Corporation.
 * Use is subject to license terms.
 */
package com.github.carya.util;

import com.github.carya.metadata.ExcelFileType;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static junit.framework.TestCase.assertTrue;

/**
 * ExcelUtils测试类
 *
 * @author stevie.wong
 * @version 3.0, 2019-01-29 10:10
 */
public class ExcelUtilsTest {
    private InputStream in(String fileName) {
        try {
            return new FileInputStream("src/test/resources/" + fileName);
        } catch (FileNotFoundException e) {
            return ExcelUtilsTest.class.getResourceAsStream("/" + fileName);
        }
    }

    @Test
    public void testBuildWorkbook() {
        assertTrue("是xls格式", ExcelUtils.buildWorkbook(ExcelFileType.XLS, in("test1.xls")) instanceof HSSFWorkbook);
        assertTrue("是xlsx格式", ExcelUtils.buildWorkbook(ExcelFileType.XLSX, in("test1.xlsx")) instanceof SXSSFWorkbook);
    }

    @Test
    public void testBuildWorkbookNoInputStream() {
        assertTrue("是xls格式", ExcelUtils.buildWorkbook(ExcelFileType.XLS) instanceof HSSFWorkbook);
        assertTrue("是xlsx格式", ExcelUtils.buildWorkbook(ExcelFileType.XLSX) instanceof SXSSFWorkbook);
    }

}
