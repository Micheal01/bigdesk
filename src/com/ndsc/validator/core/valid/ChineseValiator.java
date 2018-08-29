package com.ndsc.validator.core.valid;

import com.ndsc.validator.ValidatorResult;
import com.ndsc.validator.core.IValidator;
import com.ndsc.validator.util.MyUtil;

import java.util.Map;

/**
 * 合法性校验：只能输入中文
 * @author Michael
 * @since 2018-5-29
 */
public class ChineseValiator implements IValidator {

    private String errorMsg;//错误信息提示
    public ChineseValiator(String errorMsg)
    {
        this.errorMsg=errorMsg;
    }


    @Override
    public ValidatorResult validator(Object data, Map<String,Object> param) throws  Exception {

        if(data==null) return  ValidatorResult.success();

        boolean chinese = MyUtil.isChinese(data.toString());
        if(!chinese)
        {
            return ValidatorResult.fail(errorMsg);
        }
        return ValidatorResult.success();
    }
}
