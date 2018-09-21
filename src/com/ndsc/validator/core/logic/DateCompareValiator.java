package com.ndsc.validator.core.logic;


import com.ndsc.validator.ValidatorResult;
import com.ndsc.validator.core.IValidator;
import com.ndsc.validator.util.MyUtil;
import com.ndsc.validator.util.SimpleDateFormatUtil;

import java.security.InvalidParameterException;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 逻辑性校验:开始时间不能大于结束时间
 * @author Michael
 * @since 2018-5-25
 */
public class DateCompareValiator implements IValidator {

    private String errorMsg;
    public DateCompareValiator(String errorMsg)
    {
        this.errorMsg=errorMsg;
    }

    /**
     * 校验
     * @param data 字段值
     * @param param  需要传入的其他参数 {"p_data":"","role":">,<,>=,<=,="}
     */
    @Override
    public ValidatorResult validator(Object data, Map<String,Object> param) throws  Exception {

        if (data == null) return ValidatorResult.success();

        //获取参数
        Object endDate = param.get("p_data");
        if (MyUtil.IsEmpty(endDate)) {
            return ValidatorResult.exception("结束时间不能为空!");
        }
        Object p_role = param.get("role");
        if (MyUtil.IsEmpty(p_role)) {
            return ValidatorResult.exception("比较关系不能为空!");
        }

        if (endDate instanceof Date||
                endDate instanceof java.sql.Date)
        {
            Date eDate=(Date)endDate;
            ValidatorResult result = compareEndDate(data, eDate,p_role);
            return  result;
        }
        else if(endDate instanceof  String)
        {
            String strEndDate=endDate.toString();
            Date eDate=null;
            try {
                 eDate = MyUtil.convertDate(strEndDate);

            }catch (Exception e)
            {
                return ValidatorResult.exception("参数异常，结束时间不是日期格式，传入的值为【"+endDate.toString()+"】");
            }
            ValidatorResult result = compareEndDate(data, eDate,p_role);
            return result;

        }
        else if(endDate instanceof Timestamp)
        {
            Timestamp timeEndDate=(Timestamp)endDate;
            Date eDate=timeEndDate.getTimestamp();
            ValidatorResult result = compareEndDate(data, eDate,p_role);
            return  result;
        }
        else
        {
            return ValidatorResult.exception("结束时间参数类型错误!");
        }

    }

    //通过比较起止日期进行比较，起日期小于止日期就返回错误数据
    private ValidatorResult compareEndDate(Object beginData,Date endDate,Object role)
    {
        if(beginData instanceof  Date
            ||beginData instanceof java.sql.Date)
        {
            Date beginDate=(Date)beginData;

            return compareData(beginDate.compareTo(endDate),role, SimpleDateFormatUtil.dateToString(endDate));

        }
        else if(beginData instanceof  String)
        {
            try {
                Date beginDate = MyUtil.convertDate(beginData.toString());
                return compareData(beginDate.compareTo(endDate),role, SimpleDateFormatUtil.dateToString(endDate));
            }
            catch (Exception e)
            {
                return ValidatorResult.exception("参数异常，开始时间不是日期格式，传入的值为【"+beginData.toString()+"】");
            }
        }
        else if(beginData instanceof  Timestamp)
        {
            Timestamp beginTimeStamp=(Timestamp)beginData;
            Date beginDate=beginTimeStamp.getTimestamp();

            return compareData(beginDate.compareTo(endDate),role, SimpleDateFormatUtil.dateToString(endDate));
        }
        else
        {
            return ValidatorResult.exception("结束时间参数类型错误!");
        }
    }

    private ValidatorResult compareData(int result,Object p_role,String  endDate)
    {
        //规则
        String role=p_role.toString();
        switch (role)
        {
            case ">":
                if(result<=0)
                {
                    return ValidatorResult.fail(String.format(errorMsg, "大于",endDate));
                }
                break;
            case ">=":
                if(result<0)
                {
                    return ValidatorResult.fail(String.format(errorMsg, "大于等于",endDate));
                }
                break;
            case "<":
                if(result>=0)
                {
                    return ValidatorResult.fail(String.format(errorMsg, "小于",endDate));
                }
                break;
            case "<=":
                if(result>0)
                {
                    return ValidatorResult.fail(String.format(errorMsg, "小于等于",endDate));
                }
                break;
            case "=":
                if(result!=0)
                {
                    return ValidatorResult.fail(String.format(errorMsg, "等于",endDate));
                }
                break;
            default:
                return ValidatorResult.exception("参数值规则【"+role+"】类型不对，必须是>,<,>=,<=,=");
        }
        return ValidatorResult.success();
    }
}
