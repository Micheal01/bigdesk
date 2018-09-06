package com.ndsc.validator.config;

import com.ndsc.validator.core.*;
import com.ndsc.validator.core.logic.*;
import com.ndsc.validator.core.valid.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 规则配置类
 * @author Michael
 * @since 2018-5-28
 */
public class RoleConfig {
    //饿汉单例模式
    //1.非空校验
    private static final IValidator NOT_NULL_VALIDATOR=new NotNullValidator("不能为空！");
    //2.合法性校验
     //统一社会信用代码校验
    private static final IValidator CREDIT_CODE_VALIATOR=new CreditCodeValiator("格式错误！");
    //身份证号码格式校验
    private static final IValidator ID_CARD_VALIATOR=new IdCardValiator("格式错误！");
    //日期格式校验
    private static final IValidator DATE_VALIATOR=new DateValiator("格式错误！");
    //组织结构代码校验
    private static final IValidator ORG_CODE_VALIATOR=new OrgCodeValiator("格式错误！");
    //数字格式校验
    private static final IValidator DIGIT_VALIATOR=new DigitValiator("格式错误！");
    //非法字符校验（半角、全角、非法字符、特殊符号）
    private static final IValidator ILLEGAL_CODE_VALIATOR=new IllegalCodeValiator("含有非法字符！");
    //工商注册号校验
    private static final IValidator BUSINESS_CODE_VALIATOR=new BusinessCodeValiator("格式错误！");
    //纳税人识别号校验
    private static final IValidator TAX_VALIATOR=new TaxValiator("格式错误！");
    //只能输入中文
    private static final IValidator CHINESE_VALIATOR=new ChineseValiator("格式错误！");

    //3.逻辑性校验
    //包含校验
    private static final IValidator CONTAIN_VALIDATOR=new ContainValiator(" 不在指定的值内！");
    //开始时间不能大于结束时间
    private static final IValidator DATE_COMPARE_VALIATOR=new DateCompareValiator("不能大于结束时间！");
    //排除性验证，输入值不能在规定的范围内（文本、日期）
    private static final IValidator EXCLUDE_VALIATOR=new ExcludeValiator("不在指定范围内！");
    //数值比较（数字类型比较，此值不能大于或者小于，输入的前一值）
    private static final IValidator DIGIT_COMPARE_VALIATOR=new DigitCompareValiator("不能%s%s！");
    //必须包括关键字校验，例如处罚文号必须包括 罚（XXXX）号
    private static final IValidator INCLUDE_VALIATOR=new IncludeValiator("必须包含关键字！");
    //验证值长度只能在规定的范围；
    private static final IValidator LENGTH_VALIATOR=new LengthValiator("长度必须是在规定范围内(%s-%s)");

    //将校验算法封装，通过对应的规则进行校验
    //key=规则名
    //value=算法类
    public  static  final Map<String,IValidator> ROLE_CONIG=new HashMap<>();

    static{
        //1.非空校验
        ROLE_CONIG.put("NOT_NULL",NOT_NULL_VALIDATOR);
        //2.合法性校验
        //统一社会信用代码校验
        ROLE_CONIG.put("CREDIT_CODE",CREDIT_CODE_VALIATOR);
        //身份证号码格式校验
        ROLE_CONIG.put("ID_CARD",ID_CARD_VALIATOR);
        //日期格式校验
        ROLE_CONIG.put("DATE",DATE_VALIATOR);
        //组织结构代码校验
        ROLE_CONIG.put("ORG_CODE",ORG_CODE_VALIATOR);
        //数字格式校验
        ROLE_CONIG.put("DIGIT",DIGIT_VALIATOR);
        //非法字符校验（半角、全角、非法字符、特殊符号）
        ROLE_CONIG.put("ILLEGAL_CODE",ILLEGAL_CODE_VALIATOR);
        //工商注册号校验
        ROLE_CONIG.put("BUSINESS_CODE",BUSINESS_CODE_VALIATOR);
        //纳税人识别号校验
        ROLE_CONIG.put("TAX",TAX_VALIATOR);
        //只能输入中文校验
        ROLE_CONIG.put("CHINESE",CHINESE_VALIATOR);

        //3.逻辑性校验
        //包含校验
        ROLE_CONIG.put("CONTAIN",CONTAIN_VALIDATOR);
        //开始时间不能大于结束时间
        ROLE_CONIG.put("DATE_COMPARE",DATE_COMPARE_VALIATOR);
        //排除性验证，输入值不能在规定的范围内（文本、日期）
        ROLE_CONIG.put("EXCLUDE",EXCLUDE_VALIATOR);
        //数值比较（数字类型比较，此值不能大于或者小于，输入的前一值）
        ROLE_CONIG.put("DIGIT_COMPARE",DIGIT_COMPARE_VALIATOR);
        //必须包括关键字校验，例如处罚文号必须包括 罚（XXXX）号
        ROLE_CONIG.put("INCLUDE",INCLUDE_VALIATOR);
        //验证值长度只能在规定的范围；
        ROLE_CONIG.put("LENGTH",LENGTH_VALIATOR);
    }

    //获取对应的算法
    public static IValidator getValidator(String key)
    {
        return ROLE_CONIG.get(key);
    }

}
