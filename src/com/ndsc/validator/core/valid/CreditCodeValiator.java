package com.ndsc.validator.core.valid;

import com.ndsc.validator.ValidatorResult;
import com.ndsc.validator.core.IValidator;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 合法性校验：统一社会信用代码格式校验
 * @author Michael
 * @since 2018-5-29
 */
public class CreditCodeValiator implements IValidator {

    private String errorMsg;//错误信息提示
    public CreditCodeValiator(String errorMsg)
    {
        this.errorMsg=errorMsg;
    }


    @Override
    public ValidatorResult validator(Object data,Map<String,Object> param) throws  Exception {

        if(data==null)return  ValidatorResult.success();

        //校验规则
        String pattern = "[1-9A-GY]{1}[1239]{1}[1-5]{1}[0-9]{5}[0-9A-Z]{10}";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        Matcher matcher = r.matcher(data.toString());
        boolean isMatches = matcher.matches();
        if(!isMatches)
        {
            //不匹配
            return  ValidatorResult.fail(errorMsg);
        }
        return ValidatorResult.success();
    }
}
