package com.cmf.exception;

import com.cmf.exception.enums.ExceptionEnums;

/**
 * 系统异常基类
 */
public class XcException extends RuntimeException{
    private static final long serialVersionUID = 9008026268539823228L;
    private ExceptionEnums exceptionEnums;
    private static int errorCode = 500;

    /**
     * @param exceptionEnums 异常
     */
    public XcException(final ExceptionEnums exceptionEnums) {
        super(exceptionEnums.getMessage());
        this.exceptionEnums = exceptionEnums;
        errorCode = exceptionEnums.getCode();
    }

    /**
     * @param exceptionEnums 异常
     * @param cause          异常
     */
    public XcException(final ExceptionEnums exceptionEnums, final Throwable cause) {
        super(exceptionEnums.getMessage(), cause);
        this.exceptionEnums = exceptionEnums;
        errorCode = exceptionEnums.getCode();
    }

    public XcException(String message) {
        super(message);
    }

    public XcException(String message, Throwable cause) {
        super(message, cause);
    }

    public XcException(String message, int errorCode) {
        super(message);
        XcException.errorCode = errorCode;
    }

    public XcException(String message, int errorCode, Throwable cause) {
        super(message, cause);
        XcException.errorCode = errorCode;
    }

    public ExceptionEnums getExceptionEnums() {
        return exceptionEnums;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        XcException.errorCode = errorCode;
    }
}
