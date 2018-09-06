package com.ndsc.validator.core.logic;


import com.ndsc.validator.ValidatorResult;
import com.ndsc.validator.core.IValidator;
import com.ndsc.validator.util.MyUtil;

import java.security.InvalidParameterException;
import java.security.Timestamp;
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

    @Override
    public ValidatorResult validator(Object data, Map<String,Object> param) throws  Exception {

        if (data == null) return ValidatorResult.success();

        //获取参数
        Object endDate = param.get("p_data");
        if (MyUtil.IsEmpty(endDate)) {
            return ValidatorResult.exception("结束时间不能为空!");
        }
        if (endDate instanceof Date||
                endDate instanceof java.sql.Date)
        {
            Date eDate=(Date)endDate;
            ValidatorResult result = compareEndDate(data, eDate);
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
            ValidatorResult result = compareEndDate(data, eDate);
            return result;

        }
        else if(endDate instanceof Timestamp)
        {
            Timestamp timeEndDate=(Timestamp)endDate;
            Date eDate=timeEndDate.getTimestamp();
            ValidatorResult result = compareEndDate(data, eDate);
            return  result;
        }
        else
        {
            return ValidatorResult.exception("结束时间参数类型错误!");
        }

    }

    //通过比较起止日期进行比较，起日期小于止日期就返回错误数据
    private ValidatorResult compareEndDate(Object beginData,Date endDate)
    {
        if(beginData instanceof  Date
            ||beginData instanceof java.sql.Date)
        {
            Date beginDate=(Date)beginData;
            if(beginDate.compareTo(endDate)>0)
            {
                return ValidatorResult.fail(errorMsg);
            }
        }
        else if(beginData instanceof  String)
        {
            try {
                Date beginDate = MyUtil.convertDate(beginData.toString());
                if(beginDate.compareTo(endDate)>0)
                {
                    return ValidatorResult.fail(errorMsg);
                }
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
            if(beginDate.compareTo(endDate)>0)
            {
                return ValidatorResult.fail(errorMsg);
            }
        }
        else
        {
            return ValidatorResult.exception("结束时间参数类型错误!");
        }
        return  ValidatorResult.success();
    }
}
