校验规则调用说明
1、主要方法有
    /**
     *  通过校验规则进行规则校验
     *  只返回一条数据
     * @param data 字段对应的数据
     * @param roleAndParams  非空校验和合法校验直接传法为{map.put("规则名称",null)}即可，逻辑校验传值规则为，第一个key是规则名称，value是规则对应的参数，对应具体key看下面的“配置项说明”
     * @return ValidatorResult{code=200成功(0失败),message错误提示}
     * @throws Exception
     */
    public  static  ValidatorResult checkError( Object data,
                                      Map<String,Map<String,Object>> roleAndParams) throws Exception 
	{
	
		
	}			
	===========================================================
    /**
     * 通过校验规则进行规则校验
     * 将一个字段所有的错误返回
     * @param data 字段对应的数据
     * @param roleAndParams 同第一个方法规则说明
     * @return ArrayList<ValidatorResult>{code=200成功(0失败),message错误提示}
     * @throws Exception
     */
    public  static ArrayList<ValidatorResult> checkErrorReturnAll(Object data,
                                                 Map<String,Map<String,Object>> roleAndParams) throws Exception {
	}												 

	========================================
	配置项说明（括号内的是对应的配置项）

											
	非空校验(NOT_NULL)，返回值“不能为空！”

	统一社会信用代码校验(CREDIT_CODE)，返回值“格式错误！”

	身份证号码格式校验(ID_CARD)，返回值“格式错误！”

	日期格式校验(DATE)，返回值“格式错误！”
	
	组织结构代码校验(ORG_CODE)，返回值“格式错误！”
	
	数字格式校验(DIGIT)，返回值“格式错误！”
	
	非法字符校验(ILLEGAL_CODE)，返回值“含有非法字符！”

	工商注册号校验(BUSINESS_CODE)，返回值“格式错误！”

	纳税人识别号校验(TAX)，返回值“格式错误！”

	纳税人识别号校验(CHINESE)，返回值“格式错误！”


	逻辑性校验
	包含校验(CONTAIN)，参数key值p_data，value可以是数字和单个数字，返回值“不在指定的值内！”

	日期比较（DATE_COMPARE)，参数key值是p_data（起范围）和role（可以是>,>=,<,<=,=），value可以是日期和字符串，
	返回值“必须%s%s！”，第一个%s会自动替换到关系，第二个%s会替换掉传来的参数，最终效果是“必须大于2018-1-1！“
	
	排除性验证，输入值不能在规定的范围内（文本、日期）(EXCLUDE)，参数key值是begin（起范围）和end（止范围），value可以是数字或日期，返回值“不在指定范围内！”

	数值比较，此值不能大于或者小于，输入的前一值(DIGIT_COMPARE)，参数key值是p_data（起范围）和role（可以是>,>=,<,<=,=），value可以是数字，
	         返回值“不能%s%s！”，第一个%s会自动替换到关系，第二个%s会替换掉传来的参数，最终效果是“不能大于5！“
 
	必须包括关键字校验(INCLUDE),参数key值是p_data,value是对应的正则，返回值“必须包含关键字！”

	验证值长度只能在规定的范围(LENGTH)，参数key值是begin（起范围）和end（止范围），value是数字，返回值“长度必须是在规定范围内(%s-%s)！”，
	        第一个%s会自动替换到起范围，第二个%s会替换掉止范围，最终效果是“长度必须是在规定范围内(1-10)！“
	
	
		//测试例子
	    //返回字段所有错误例子
	    public static void main(String[] args) {

            Object data="0";
          //最好传LinkedHashMap，因为这可以按照插入顺序来执行
          Map<String,Map<String,Object>> params=new LinkedHashMap<>();

            Map map=new HashMap<String, Object[]>();
            map.put("p_data","1");
            map.put("role",">=");
            //测试数值比较，此值不能大于或者小于，输入的前一值(DIGIT_COMPARE)
            params.put("DIGIT_COMPARE",map);
            //校验纳税人号
            params.put("TAX",null);

            try {
                List<ValidatorResult> result = ValidatorUtils.checkErrorReturnAll(data,params);
                System.out.println(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
     }

            //测试例子
    	    //返回单个错误例子
    	    public static void main(String[] args) {

                Object data="0";
               //最好传LinkedHashMap，因为这可以按照插入顺序来执行
               Map<String,Map<String,Object>> params=new LinkedHashMap<>();

                Map map=new HashMap<String, Object[]>();
                map.put("p_data","1");
                map.put("role",">=");
                //测试数值比较，此值不能大于或者小于，输入的前一值(DIGIT_COMPARE)
                params.put("DIGIT_COMPARE",map);

                try {
                    ValidatorResult result = ValidatorUtils.checkError(data,params);
                    System.out.println(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
	
	
