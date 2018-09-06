package com.ndsc.validator.core.logic;


import com.ndsc.validator.ValidatorResult;
import com.ndsc.validator.core.IValidator;
import com.ndsc.validator.util.MyUtil;

import java.security.InvalidParameterException;
import java.util.Map;

/**
 * 逻辑性校验:验证值长度只能在规定的范围；
 * @author Michael
 * @since 2018-5-25
 */
public class LengthValiator implements IValidator {

    private String errorMsg;
    public LengthValiator(String errorMsg)
    {
        this.errorMsg=errorMsg;
    }

    /**
     * 校验
     * @param data 字段值
     * @param param  参数格式 {"begin":"","end":""}
     * @return 错误提示信息
     * @throws Exception
     */
    @Override
    public ValidatorResult validator(Object data, Map<String,Object> param) throws  Exception {

        if(data==null)return ValidatorResult.success();

        //获取参数
        Object begin=param.get("begin");
        Object end=param.get("end");

        if (MyUtil.IsEmpty(begin)) {
            return ValidatorResult.exception("开始值不能为空!");
        }
        if (MyUtil.IsEmpty(end)) {
            return ValidatorResult.exception("结束值不能为空!");
        }

        Double double_begin=MyUtil.getDouble(begin);
        if(double_begin==null)
        {
            return ValidatorResult.exception("参数异常，开始值必须是数字，你传入的是【"+begin+"】!");
        }

        Double double_end=MyUtil.getDouble(end);
        if(double_end==null)
        {
            return ValidatorResult.exception("参数异常，结束值必须是数字，你传入的是【"+end+"】!");
        }
        if(data instanceof  String)
        {
            Integer length=data.toString().length();
            if(length<double_begin||length>double_end)
            {
                return ValidatorResult.fail(String.format(errorMsg, begin.toString(),end.toString()));
            }
        }
        else
        {
            return ValidatorResult.exception("参数异常，传入值必须是字符串!");
        }

        return ValidatorResult.success();
    }


}
