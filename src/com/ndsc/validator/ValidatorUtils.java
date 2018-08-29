package com.ndsc.validator;

import com.ndsc.validator.config.RoleConfig;
import com.ndsc.validator.core.IValidator;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * 校验工具类
 * @author Michael
 * @since 2018-5-28
 */
public class ValidatorUtils {

    /**
     *  通过校验规则进行规则校验
     *  只返回一条数据
     * @param data 字段对应的数据
     * @param roleAndParams
     * @return 错误提示
     * @throws Exception
     */
    public  static  ValidatorResult checkError( Object data,
                                      Map<String,Map<String,Object>> roleAndParams) throws Exception {
        //遍历Map,取出规则
        Iterator<Map.Entry<String, Map<String, Object>>> iterator = roleAndParams.entrySet().iterator();
        while (iterator.hasNext())
        {
            //通过规则名称获取算法对象
            Map.Entry<String, Map<String, Object>> next = iterator.next();
            IValidator validator = RoleConfig.getValidator(next.getKey());
            if(validator==null)
            {
                throw new InvalidParameterException("找不到对应的规则 【"+ next.getKey() +"】！");
            }
            ValidatorResult result=validator.validator(data, next.getValue());
            if(result.getCode()==ResultCode.失败)
            {
                //出现报错立即返回
                return  result;
            }
        }
        return ValidatorResult.success();
    }

    /**
     * 通过校验规则进行规则校验
     * 将一个字段所有的错误返回
     * @param data 字段对应的数据
     * @param roleAndParams
     * @return 错误提示
     * @throws Exception
     */
    public  static ArrayList<ValidatorResult> checkErrorReturnAll(Object data,
                                                 Map<String,Map<String,Object>> roleAndParams) throws Exception {
        ArrayList<ValidatorResult> list=new ArrayList<>();
        //遍历Map,取出规则
        Iterator<Map.Entry<String, Map<String, Object>>> iterator = roleAndParams.entrySet().iterator();
        while (iterator.hasNext())
        {
            //通过规则名称获取算法对象
            Map.Entry<String, Map<String, Object>> next = iterator.next();
            IValidator validator = RoleConfig.getValidator(next.getKey());
            if(validator==null)
            {
                throw new InvalidParameterException("找不到对应的规则 【"+ next.getKey() +"】！");
            }
            ValidatorResult result=validator.validator(data, next.getValue());
            if(result.getCode()==ResultCode.失败)
            {
                list.add(result);
            }
        }
        return list.size()==0 ? null:list;
    }
}
