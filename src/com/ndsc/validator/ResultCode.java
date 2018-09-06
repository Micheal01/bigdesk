package com.ndsc.validator;

/**
 * 结果状态码
 * @author Michael
 * @since 2018-6-4
 */
public enum  ResultCode {
    成功(200),

    失败(0),

    异常(500);

    public final  int flag;
    ResultCode(int flag)
    {
        this.flag=flag;
    }
}
