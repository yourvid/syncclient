package com.cmf.exception;

import com.alibaba.fastjson.JSON;
import com.cmf.base.ResponseModel;
import com.cmf.constants.SystemConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 全局异常处理.
 */
@ControllerAdvice(basePackages = "com.cmf")
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(XcHandlerException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void XcHandlerExceptionResponse(XcHandlerException exception, HttpServletResponse response, HttpServletRequest request) {
        logger.error("全局异常:{}", exception.getMessage(), exception);
        responseJson(request, response, new ResponseModel(exception.getErrorCode(), exception.getMessage()));

    }


    /**
     * 响应json格式字符串
     *
     * @param request  {HttpServletRequest}
     * @param response {HttpServletResponse}
     * @param model    {ResponseModel}
     */
    private void responseJson(HttpServletRequest request, HttpServletResponse response, ResponseModel model) {
        response.setCharacterEncoding(SystemConstants.SYSTEM_CHARSET);
        response.setContentType("application/json; charset=utf-8");
        try (PrintWriter out = response.getWriter()) {
            out.append(JSON.toJSONString(model));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }


}
