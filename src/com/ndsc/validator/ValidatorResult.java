package com.ndsc.validator;

/**
 * 返回结果对象
 * @author Michael
 * @since 2018-6-4
 */
public class ValidatorResult {
    private ResultCode code; //状态
    private String message; //返回内容
    private String exceptionMsg; //异常内容

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }


    public ValidatorResult(ResultCode code, String message,String exceptionMsg) {
        this.code = code;
        this.message = message;
        this.exceptionMsg =exceptionMsg;
    }

    public ResultCode getCode() {
        return code;
    }

    public void setCode(ResultCode code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public  static ValidatorResult success()
    {
        return new ValidatorResult(ResultCode.成功,"","");
    }

    public static ValidatorResult fail(String data)
    {
        return new ValidatorResult(ResultCode.失败,data,"");
    }

    public static ValidatorResult exception(String errorMsg)
    {
        return new ValidatorResult(ResultCode.异常,"",errorMsg);
    }

    @Override
    public String toString() {
        return "ValidatorResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", exceptionMsg='" + exceptionMsg + '\'' +
                '}';
    }
}
