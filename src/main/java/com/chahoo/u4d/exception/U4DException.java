package com.chahoo.u4d.exception;

/**
 * Created by jjryu on 2017-03-03.
 */
public class U4DException extends  RuntimeException{
    String exceptionMsg = null;
    String code=null;

    public U4DException(String message){
        super(message);
        this.exceptionMsg=message;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }
    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
