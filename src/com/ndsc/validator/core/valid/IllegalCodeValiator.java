package com.ndsc.validator.core.valid;

import com.ndsc.validator.ValidatorResult;
import com.ndsc.validator.core.IValidator;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 合法性校验：非法字符校验
 * @author Michael
 * @since 2018-5-29
 */
public class IllegalCodeValiator implements IValidator {

    private String errorMsg;//错误信息提示
    public IllegalCodeValiator(String errorMsg)
    {
        this.errorMsg=errorMsg;
    }


    @Override
    public ValidatorResult validator(Object data, Map<String,Object> param) throws  Exception {

        if(data==null)return  ValidatorResult.success();

        //校验规则
        //只允许数字字母空格还有（逗号，句号，感叹号，分号,斜杠,冒号,方括号，小括号，大括号），其他都是非法的
        String pattern = "[`~#$%^&*+=|{}':'\\\\[\\\\].<>?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        Matcher matcher = r.matcher(data.toString());
        boolean isMatches = matcher.matches();
        if(!isMatches)
        {
            //匹配上说明有非法字符
            return ValidatorResult.fail(errorMsg);
        }
        return  ValidatorResult.success();
    }
}
