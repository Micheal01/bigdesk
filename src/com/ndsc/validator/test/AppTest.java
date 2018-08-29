package com.ndsc.validator.test;

import com.ndsc.validator.ValidatorResult;
import com.ndsc.validator.ValidatorUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Michael
 * @since 2018-5-28
 */
public class AppTest {


    public static void main(String[] args) {
        //测试数值比较，此值不能大于或者小于，输入的前一值(DIGIT_COMPARE)
        Object data="1";
        //最好传LinkedHashMap，因为这可以按照插入顺序来执行
        Map<String,Map<String,Object>> params=new LinkedHashMap<>();

        params.put("NOT_NULL",null);

//        Map map=new HashMap<String, Object[]>();
//        map.put("p_data","1");
//        map.put("role",">=");
//        params.put("DIGIT_COMPARE",map);
//
//        params.put("TAX",null);

        try {
            ValidatorResult result = ValidatorUtils.checkError(data,params);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
