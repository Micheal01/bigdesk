package com.ndsc.validator.core.valid;

import com.ndsc.validator.ValidatorResult;
import com.ndsc.validator.core.IValidator;

import java.util.Map;

/**
 * 合法性校验：数字格式校验
 * @author Michael
 * @since 2018-5-29
 */
public class DigitValiator implements IValidator {

    private String errorMsg;//错误信息提示
    public DigitValiator(String errorMsg)
    {
        this.errorMsg=errorMsg;
    }


    @Override
    public ValidatorResult validator(Object data, Map<String,Object> param) throws  Exception {

        if(data==null)return  ValidatorResult.success();

        try {
            Double.parseDouble(data.toString());
        }
        catch (NumberFormatException e)
        {
            //格式错误就说明格式不对
            return ValidatorResult.fail(errorMsg);
        }
        return ValidatorResult.success();
    }
}
