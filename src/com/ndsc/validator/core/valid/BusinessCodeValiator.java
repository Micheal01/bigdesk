package com.ndsc.validator.core.valid;

import com.ndsc.validator.ValidatorResult;
import com.ndsc.validator.core.IValidator;

import java.util.HashMap;
import java.util.Map;

/**
 * 合法性校验：工商注册号校验
 * @author Michael
 * @since 2018-5-29
 */
public class BusinessCodeValiator implements IValidator {

    private String errorMsg;//错误信息提示
    public BusinessCodeValiator(String errorMsg)
    {
        this.errorMsg=errorMsg;
    }


    @Override
    public ValidatorResult validator(Object data, Map<String,Object> param) throws  Exception {

        if(data==null) return ValidatorResult.success();

        //如果data是15位就是营业执照注册号
        //18位是社会信用代码
        String code=data.toString();
        if(code.length()==15)
        {
            boolean right = isRightBusinessCode15(code);
            if(!right)
            {
                return ValidatorResult.fail(errorMsg);
            }
        }
        else if(code.length()==18)
        {
            boolean right = isRightBusinessCode18(code);
            if(!right)
            {
                return ValidatorResult.fail(errorMsg);
            }
        }
        else
        {
            //长度不对肯定是错的
            return ValidatorResult.fail(errorMsg);
        }
        return ValidatorResult.success();
    }

    /**
     * 校验 营业执照注册号
     * @param businessLicense 代码
     * @return true校验成功，false失败
     */
    private  boolean  isRightBusinessCode15(String businessLicense){
        int ti = 0;
        int si = 0;
        int cj = 0;
        int pj = 10;
        for (int i = 0; i < businessLicense.length(); i++) {
            ti = Integer.parseInt(businessLicense.substring(i,i+1));
            si = pj + ti;
            cj = (0 == si % 10 ? 10 : si % 10) * 2;
            pj = (cj % 11) == 0 ? 10 : (cj % 11);

            if (i == businessLicense.length()-1) {
                return si % 10==1; // 返回1 表示是一个有效营业执照号
            }
        }
        return false;
    }

    /**
     * 校验 统一社会信用代码
     * @param businessLicense 代码
     * @return true校验成功，false失败
     */
    private  boolean  isRightBusinessCode18(String businessLicense){

        String baseCode = "0123456789ABCDEFGHJKLMNPQRTUWXY";
        char[] baseCodeArray = baseCode.toCharArray();
        Map<Character, Integer> codes = new HashMap<>();
        for (int i = 0; i < baseCode.length(); i++) {
            codes.put(baseCodeArray[i], i);
        }
        char[] businessCodeArray = businessLicense.toCharArray();
        Character check = businessCodeArray[17];
        if (baseCode.indexOf(check) == -1) {
            return false;
        }
        int[] wi = { 1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28 };
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            Character key = businessCodeArray[i];
            if (baseCode.indexOf(key) == -1) {
                return false;
            }
            sum += (codes.get(key) * wi[i]);
        }
        int value = 31 - sum % 31;
        return value == codes.get(check);
    }

}
