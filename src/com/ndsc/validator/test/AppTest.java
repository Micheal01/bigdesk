package com.ndsc.validator.test;

import com.ndsc.validator.ValidatorResult;
import com.ndsc.validator.ValidatorUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Michael
 * @since 2018-5-28
 */
public class AppTest {


    public static void main(String[] args) throws ParseException {
        //测试数值比较，此值不能大于或者小于，输入的前一值(DIGIT_COMPARE)
        Object data="2018-1-a";

        //最好传LinkedHashMap，因为这可以按照插入顺序来执行
        Map<String,Map<String,Object>> params=new LinkedHashMap<>();
        params.put("NOT_OVER_NOW_DATE",null);

        try {
            ValidatorResult result = ValidatorUtils.checkError(data,params);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
