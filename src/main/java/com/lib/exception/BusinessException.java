package com.lib.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BusinessException extends RuntimeException{

    private String errorCode;
    private static Logger log = LoggerFactory.getLogger(BusinessException.class);

    /**
     * 构造一个异常类
     * @param errorCode 错误码
     * @param errorMsg 错误信息
     * @param isRecord 是否使用默认构造器记录错误日志
     */
    public BusinessException(String errorCode, String errorMsg, boolean isRecord){
        super(errorMsg);
        if(isRecord) {
            log.error("业务出错，错误码是" + errorCode, this);
        }
        this.errorCode = errorCode;
    }

    /**
     * 构建一个简单的异常类，自动记录异常
     * @param errorMsg
     */
    public BusinessException(String errorMsg){
        super(errorMsg);
        log.error(errorMsg);
        this.errorCode = "500";
    }

    /**
     * 构建一个简单的异常类，记录异常
     * @param errorMsg 异常信息
     * @param e 异常栈信息
     */
    public BusinessException(String errorMsg, Exception e){

        super(errorMsg,e);
        log.error(errorMsg,e);
        this.errorCode = "500";
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    public BusinessException(Exception e,String format,Object... args ){
        super(e);
        String errorMsg = String.format(format,args);
        log.error(errorMsg,e);
        this.errorCode = "500";
    }
    public BusinessException(String format,Object... args ){
        super(String.format(format,args));
        String errorMsg = String.format(format,args);
        log.error(errorMsg);
        this.errorCode = "500";
    }
}
