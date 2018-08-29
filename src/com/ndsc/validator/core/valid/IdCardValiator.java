package com.ndsc.validator.core.valid;

import com.ndsc.validator.ValidatorResult;
import com.ndsc.validator.core.IValidator;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 合法性校验：身份证号码校验
 * @author Michael
 * @since 2018-5-29
 */
public class IdCardValiator implements IValidator {

    private String errorMsg;//错误信息提示
    public IdCardValiator(String errorMsg)
    {
        this.errorMsg=errorMsg;
    }


    @Override
    public ValidatorResult validator(Object data, Map<String,Object> param) throws  Exception {

        if(data==null)return  ValidatorResult.success();

        //校验规则
        String pattern = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$)";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        Matcher matcher = r.matcher(data.toString());
        boolean isMatches = matcher.matches();
        if(!isMatches)
        {
            //不匹配
            return ValidatorResult.fail(errorMsg);
        }
        return ValidatorResult.success();
    }
}
