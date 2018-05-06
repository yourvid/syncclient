package com.cmf.exception.enums;


public enum HandlerExceptionEnums implements ExceptionEnums {

    HANDLER_CALL_BACK_302(302, "地址回调302跳转错误"),
    HANDLER_CALL_BACK_400(400, "地址回调400 bad request 错误"),
    HANDLER_CALL_BACK_401(401, "地址回调401 unauthorized错误"),
    HANDLER_CALL_BACK_404(404, "地址回调404 not found 错误"),
    HANDLER_CALL_BACK_403(403, "地址回调403 forbidden 错误");



    public int code;
    public String message;

    private HandlerExceptionEnums(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    private HandlerExceptionEnums(final String message) {
        this.message = message;
    }

    @Override
    public final int getCode() {
        return code;
    }

    @Override
    public final String getMessage() {
        return message;
    }
}
