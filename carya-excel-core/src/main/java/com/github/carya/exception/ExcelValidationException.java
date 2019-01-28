/**
 * Copyright (c) YunTrex Corporation. All rights reserved.
 * <p>
 * This software is the proprietary information of YunTrex Corporation.
 * Use is subject to license terms.
 */
package com.github.carya.exception;

/**
 * Excel验证异常
 *
 * @author stevie.wong
 * @version 3.0, 2019-01-28 16:46
 */
public class ExcelValidationException extends RuntimeException {
    public ExcelValidationException(String message) {
        super(message);
    }

    public ExcelValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcelValidationException(Throwable cause) {
        super(cause);
    }
}
