package com.ndsc.validator.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 时间格式化工具类
 * @author Michael
 * @since 2018-5-30
 */
public class SimpleDateFormatUtil {

    //饿汉单例，防止多次初始化
    private static  final SimpleDateFormat SIMPLE_DATE_FORMAT_1=new SimpleDateFormat("yyyy-MM-dd");
    private static  final SimpleDateFormat SIMPLE_DATE_FORMAT_2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static  final SimpleDateFormat SIMPLE_DATE_FORMAT_3=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static  final SimpleDateFormat SIMPLE_DATE_FORMAT_4=new SimpleDateFormat("yyyy/MM/dd");
    private static  final SimpleDateFormat SIMPLE_DATE_FORMAT_5=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static  final SimpleDateFormat SIMPLE_DATE_FORMAT_6=new SimpleDateFormat("yyyy/MM/dd HH:mm");
    private static  final SimpleDateFormat SIMPLE_DATE_FORMAT_7=new SimpleDateFormat("yyyy年MM月dd日");
    private static  final SimpleDateFormat SIMPLE_DATE_FORMAT_8=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
    private static  final SimpleDateFormat SIMPLE_DATE_FORMAT_9=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
    private static  final SimpleDateFormat SIMPLE_DATE_FORMAT_10=new SimpleDateFormat("yyyyMMdd");
    private static  final SimpleDateFormat SIMPLE_DATE_FORMAT_11=new SimpleDateFormat("yyyyMMdd HHmmss");
    private static  final SimpleDateFormat SIMPLE_DATE_FORMAT_12=new SimpleDateFormat("yyyyMMdd HHmm");

    private static  final List<SimpleDateFormat> list=new ArrayList<>();
    static
    {
        //越精细的时间格式放前面，比如2017-11-10 9:10:12 这种格式进来，如果先执行yyyy-MM-dd的话就好自动去掉时分秒，所以会出现下面的顺序
        list.add(SIMPLE_DATE_FORMAT_2);
        list.add(SIMPLE_DATE_FORMAT_3);
        list.add(SIMPLE_DATE_FORMAT_1);

        list.add(SIMPLE_DATE_FORMAT_5);
        list.add(SIMPLE_DATE_FORMAT_6);
        list.add(SIMPLE_DATE_FORMAT_4);

        list.add(SIMPLE_DATE_FORMAT_8);
        list.add(SIMPLE_DATE_FORMAT_9);
        list.add(SIMPLE_DATE_FORMAT_7);

        list.add(SIMPLE_DATE_FORMAT_11);
        list.add(SIMPLE_DATE_FORMAT_12);
        list.add(SIMPLE_DATE_FORMAT_10);

    }

    //字符串转date类型
    public  static Date stringToDate(String data)
    {
        for (int i = 0; i < list.size(); i++) {
            SimpleDateFormat simpleDateFormat=list.get(i);
            try {
                Date date = simpleDateFormat.parse(data);
                if(date!=null)
                {
                    return date;
                }
            } catch (ParseException e) {

            }
        }
        return null;
    }
    //转成时间格式
    public static String dateToString(Date date)
    {
        return SIMPLE_DATE_FORMAT_1.format(date);
    }

}
