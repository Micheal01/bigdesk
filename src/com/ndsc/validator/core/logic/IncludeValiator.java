package com.ndsc.validator.core.logic;


import com.ndsc.validator.ValidatorResult;
import com.ndsc.validator.core.IValidator;
import com.ndsc.validator.util.MyUtil;

import java.security.InvalidParameterException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 逻辑性校验:必须包括关键字校验，例如处罚文号必须包括 罚（XXXX）号
 * @author Michael
 * @since 2018-5-25
 */
public class IncludeValiator implements IValidator {

    private String errorMsg;
    public IncludeValiator(String errorMsg)
    {
        this.errorMsg=errorMsg;
    }

    /**
     * 校验
     * @param data 字段值
     * @param param  参数格式 {"p_data":" "},这边只接受正则表达式
     * @return 错误提示信息
     * @throws Exception
     */
    @Override
    public ValidatorResult validator(Object data, Map<String,Object> param) throws  Exception {

        if(data==null)return ValidatorResult.success();

        //获取参数
        Object p_data=param.get("p_data");

        if (MyUtil.IsEmpty(p_data)) {
            return ValidatorResult.exception("开始值不能为空!");
        }
        //校验规则
        String pattern =p_data.toString();

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
