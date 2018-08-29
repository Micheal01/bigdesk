package com.ndsc.validator.core.logic;


import com.ndsc.validator.ValidatorResult;
import com.ndsc.validator.core.IValidator;
import com.ndsc.validator.util.MyUtil;

import java.util.Map;

/**
 * 逻辑性校验:包含校验
 * @author Michael
 * @since 2018-5-25
 */
public class ContainValiator implements IValidator {

    private String errorMsg;
    public ContainValiator(String errorMsg)
    {
        this.errorMsg=errorMsg;
    }

    @Override
    public ValidatorResult validator(Object data, Map<String,Object> param) throws  Exception {

        if(data==null)return ValidatorResult.success();

        //将数据转成string类型进行比较
        String temp=data.toString();

        boolean isExists=false;
        //获取参数
        Object par=param.get("p_data");
        if(MyUtil.IsEmpty(par))
        {
            throw new Exception("参数不能为空或key值不是【p_data】!");
        }
        Object[] params=null;
        try {
            params = (Object[]) par;
        }
        catch (Exception e)
        {
            //转换失败说明数组就一个值
            params=new Object[]{data};
        }

        for (int i = 0; i < params.length; i++) {

            String p=(params[i]==null?"":params[i].toString());

            if(p.equals(temp))
            {
                isExists=true;
                break;
            }
        }

        if(!isExists)
        {
            //具体的值不包含对应的值
            return ValidatorResult.fail(errorMsg);
        }

        return ValidatorResult.success();
    }
}
