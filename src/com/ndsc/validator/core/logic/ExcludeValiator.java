package com.ndsc.validator.core.logic;


import com.ndsc.validator.ValidatorResult;
import com.ndsc.validator.core.IValidator;
import com.ndsc.validator.util.MyUtil;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.Map;

/**
 * 逻辑性校验:排除性验证，输入值不能在规定的范围内（文本、日期）
 * @author Michael
 * @since 2018-5-25
 */
public class ExcludeValiator implements IValidator {

    private String errorMsg;
    public ExcludeValiator(String errorMsg)
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
            throw new InvalidParameterException("开始值不能为空或key值不是【begin】!");
        }
        if (MyUtil.IsEmpty(end)) {
            throw new InvalidParameterException("结束值不能为空或key值不是【end】!");
        }

        //直接判断是不是数字，不是数字就判断是不是时间
        Double double_begin=MyUtil.getDouble(begin);
        if(double_begin!=null)
        {
            Double double_end=MyUtil.getDouble(end);
            if(double_end==null)
            {
                throw  new Exception("参数开始值是数字，结束值却非数字，必须一致！");
            }
            else
            {
                if(double_begin>double_end)
                {
                    throw  new Exception("参数开始值不能大于结束值！");
                }
                else
                {
                    Double double_data=MyUtil.getDouble(data);
                    if(double_data!=null)
                    {
                        if (double_data >= double_begin
                                && double_data <= double_end)
                        {
                            //属于范围内
                            return ValidatorResult.fail(errorMsg);
                        }
                    }
                }
            }
        }
        else
        {
            //判断是不是时间格式
           Date date_begin=MyUtil.getDate(begin);
           if(date_begin!=null)
           {
                Date date_end=MyUtil.getDate(end);
                if(date_end==null)
                {
                    throw  new Exception("参数开始值是日期格式，结束值却非日期格式，必须一致！");
                }
                else
                {
                    if(date_begin.compareTo(date_end)>0)
                    {
                        throw  new Exception("参数开始值不能大于结束值！");
                    }
                    else
                    {
                        Date date_data=MyUtil.getDate(data);
                        if(date_data!=null)
                        {
                            if(date_data.compareTo(date_begin)>0&&
                                    date_data.compareTo(date_end)<0)
                            {
                                return ValidatorResult.fail(errorMsg);
                            }
                        }
                    }
                }
           }
           else
           {
               throw new Exception("暂时只支持时间和数字范围的排除性校验！");
           }
        }
        return ValidatorResult.success();
    }


}
