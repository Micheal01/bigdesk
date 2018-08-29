package com.ndsc.validator.util;

import java.security.Timestamp;
import java.util.Date;

/**
 *  常用工具类
 * @author Michael
 * @since 2018-5-30
 */
public class MyUtil {

    //校验某个值是否为空
    public static boolean IsEmpty(Object data)
    {
        if (data == null)
        {
            return true;
        }
        else
        {
            if(data instanceof String)
            {
                //字段串过滤空数据
                if("".equals(data.toString()))
                {
                    return true;
                }
            }
        }
        return  false;
    }

    //校验数组是否为空
    public static  boolean  IsEmpty(Object[] datas)
    {
        if(datas==null)
        {
            return  true;
        }
        if(datas.length==0)
        {
           return  true;
        }
        return  false;
    }

    //将字符串转成时间类型
    public static Date convertDate(String date) throws Exception
    {
        Date result=SimpleDateFormatUtil.stringToDate(date);
        if(result==null)
        {
            throw new Exception("时间转换格式错误！");
        }
        return result;
    }
    //转换成数字
    public static Double getDouble(Object data)
    {
        if(data instanceof String)
        {
            try
            {
                return Double.parseDouble(data.toString());
            }
            catch (Exception e)
            {
                return  null;
            }
        }
        else if(data instanceof Byte||data instanceof Short
                ||data instanceof Integer||data instanceof  Float
                ||data instanceof Long||data instanceof Double
                )
        {
            return Double.parseDouble(data.toString());
        }
        return  null;
    }

    //转换成时间格式
    public static Date getDate(Object data)
    {
        if(data instanceof String)
        {
            try {
                return MyUtil.convertDate(data.toString());
            } catch (Exception e) {
                return null;
            }
        }
        else if(data instanceof Date
                ||data instanceof java.sql.Date)
        {
            return (Date)data;
        }
        else if(data instanceof Timestamp)
        {
            return ((Timestamp)data).getTimestamp();
        }

        return  null;
    }

    /**
     * 判断该字符串是否为中文
     * @param string
     * @return
     */
    public static boolean isChinese(String string){
        int n = 0;
        for(int i = 0; i < string.length(); i++) {
            n = (int)string.charAt(i);
            if(!(19968 <= n && n <40869)) {
                return false;
            }
        }
        return true;
    }


}
