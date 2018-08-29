package com.ndsc.convert.core;

/**
 * 转换基类
 * @author Michael
 * @since 2018-5-30
 */
public interface IConvert {

    /**
     * 将值按照规则进行转换
     * @param data 字段值
     * @param param 参数
     * @return 转换的具体值
     */
    String convert(String data,String param);
}
