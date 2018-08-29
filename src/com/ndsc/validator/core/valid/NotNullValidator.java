package com.ndsc.validator.core.valid;

import com.ndsc.validator.ValidatorResult;
import com.ndsc.validator.core.IValidator;
import com.ndsc.validator.util.MyUtil;

import java.util.Map;

/**
 * 非空校验
 * @author Michael
 * @since 2018-5-28
 */
public class NotNullValidator implements IValidator {

    private String errorMsg;//错误信息提示
    public NotNullValidator(String errorMsg)
    {
        this.errorMsg=errorMsg;
    }

    @Override
    public ValidatorResult validator(Object data, Map<String,Object> param) throws Exception {

       if(MyUtil.IsEmpty(data))
       {
           return ValidatorResult.fail(errorMsg);
       }
        return ValidatorResult.success();
    }
}
