package com.ndsc.validator.core;

import com.ndsc.validator.ValidatorResult;

import java.util.Map;

/**
 * 校验接口
 * @author Michael
 * @since 2018-5-25
 */
public interface IValidator {

    /**
     *  校验字段的数据
     *  @param data 字段值
     * @param param  需要传入的其他参数
     * @return  校验结果
     */
     ValidatorResult validator(Object data, Map<String,Object> param) throws  Exception;


}
