package com.ndsc.validator.core.logic;

import com.ndsc.validator.ValidatorResult;
import com.ndsc.validator.core.IValidator;
import com.ndsc.validator.util.MyUtil;
import com.ndsc.validator.util.SimpleDateFormatUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 不能大于当前时间
 * @author Michael
 * @since 2018-11-20
 */
public class NotOverNowDateValidator implements IValidator {

    private String errorMsg;
    public NotOverNowDateValidator(String errorMsg)
    {
        this.errorMsg=errorMsg;
    }

    @Override
    public ValidatorResult validator(Object data, Map<String,Object> param) throws  Exception {
        if(data==null)return ValidatorResult.success();

        //获取参数
        if (MyUtil.IsEmpty(data)) {
            return ValidatorResult.exception("参数不能为空!");
        }


        Date p_date=MyUtil.getDate(data);
        if(p_date==null)
        {
            return ValidatorResult.exception("参数格式不对!");
        }

        String systemDate= SimpleDateFormatUtil.dateToString(new Date());

        String pDate= SimpleDateFormatUtil.dateToString(p_date);

        Date systemDate_new=MyUtil.getDate(systemDate);
        Date pDate_new=MyUtil.getDate(pDate);

        if(pDate_new.getTime()>systemDate_new.getTime())
        {
            return ValidatorResult.fail(errorMsg);
        }

        return ValidatorResult.success();
    }
}
