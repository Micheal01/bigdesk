package com.ndsc.validator;

/**
 * 返回结果对象
 * @author Michael
 * @since 2018-6-4
 */
public class ValidatorResult {
    private ResultCode code;
    private String message;

    public ValidatorResult(ResultCode code, String message) {
        this.code = code;
        this.message = message;
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
        return new ValidatorResult(ResultCode.成功,"");
    }

    public static ValidatorResult fail(String data)
    {
        return new ValidatorResult(ResultCode.失败,data);
    }

    @Override
    public String toString() {
        return "ValidatorResult{" +
                "code=" + code.name() +
                ", message='" + message + '\'' +
                '}';
    }
}
