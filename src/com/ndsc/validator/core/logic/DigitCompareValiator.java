package com.ndsc.validator.core.logic;


import com.ndsc.validator.ValidatorResult;
import com.ndsc.validator.core.IValidator;
import com.ndsc.validator.util.MyUtil;

import java.security.InvalidParameterException;
import java.util.Map;

/**
 * 逻辑性校验:数值比较（数字类型比较，此值不能大于或者小于，输入的前一值
 * @author Michael
 * @since 2018-5-25
 */
public class DigitCompareValiator implements IValidator {

    private String errorMsg;
    public DigitCompareValiator(String errorMsg)
    {
        this.errorMsg=errorMsg;
    }

    /**
     * 校验
     * @param data 字段值
     * @param param  参数格式 {"p_data":"","role":">,<,>=,<=,="}
     * @return 错误提示信息
     * @throws Exception
     */
    @Override
    public ValidatorResult validator(Object data, Map<String,Object> param) throws  Exception {

        if (data == null) return ValidatorResult.success();

        //获取参数
        Object p_data = param.get("p_data");
        if (MyUtil.IsEmpty(p_data)) {
            return ValidatorResult.exception("比较的值不能为空!");
        }

        Object p_role = param.get("role");
        if (MyUtil.IsEmpty(p_role)) {
            return ValidatorResult.exception("比较关系不能为空!");
        }

        Double double_p_data=MyUtil.getDouble(p_data);
        if(double_p_data==null)
        {
            return ValidatorResult.exception("比较的值【"+p_data+"】类型不对，必须是数字类型!");
        }

        Double double_data=MyUtil.getDouble(data);
        if(double_data==null)
        {
            return ValidatorResult.exception("参数值【"+data+"】类型不对，必须是数字类型!");
        }
        //规则
        String role=p_role.toString();
        switch (role)
        {
            case ">":
                if(double_data>double_p_data)
                {
                    return ValidatorResult.fail(String.format(errorMsg, "大于",p_data));
                }
                break;
            case ">=":
                if(double_data>=double_p_data)
                {
                    return ValidatorResult.fail(String.format(errorMsg, "大于等于",p_data));
                }
                break;
            case "<":
                if(double_data<double_p_data)
                {
                    return ValidatorResult.fail(String.format(errorMsg, "小于",p_data));
                }
                break;
            case "<=":
                if(double_data<=double_p_data)
                {
                    return ValidatorResult.fail(String.format(errorMsg, "小于等于",p_data));
                }
                break;
            case "=":
                if(double_data.equals(double_p_data))
                {
                    return ValidatorResult.fail(String.format(errorMsg, "等于",p_data));
                }
                break;
            default:
                return ValidatorResult.exception("参数值规则【"+role+"】类型不对，必须是>,<,>=,<=,=");
        }
        return ValidatorResult.success();

    }


}
