package saaf.common.fmw.common.utils;


import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;


public final class SaafToolUtils {
	
	/**
	 * 生成Hbm条件
	 * @param clazz 查询的Hbm对象
	 * @param jsonParam 入参
	 * @param queryAttr 查询属性及从jsonParam取值的key
	 * @param sb HQL
	 * @param queryParamMap Hbm参数Map
	 * @param operator 查询符号：=、like、in
	 */
	public static void parperHbmParam(Class<?> clazz,JSONObject jsonParam,String queryAttr, StringBuffer sb, Map<String, Object> queryParamMap, String operator) {
		parperHbmParam(clazz, jsonParam,queryAttr, queryAttr, sb, queryParamMap, operator);
	}
	
	/**
	 * 生成Hbm条件
	 * @param clazz 查询的Hbm对象
	 * @param jsonParam 入参
	 * @param fieldName 查询属性
	 * @param queryAttr 从jsonParam取值的Key
	 * @param sb HQL
	 * @param queryParamMap Hbm参数Map
	 * @param operator 查询符号：=、like、in
	 */
	public static void parperHbmParam(Class<?> clazz,JSONObject jsonParam,String fieldName, String queryAttr, StringBuffer sb, Map<String, Object> queryParamMap, String operator){
		try {
			Field field = clazz.getDeclaredField(fieldName);
			String fieldType = field.getType().getSimpleName();
			
			if(null == jsonParam){
			    return;    
			}
			if (jsonParam.containsKey(queryAttr) && !"".equals(jsonParam.get(queryAttr).toString())&&jsonParam.getString(queryAttr)!=null) {
			    Object attributeValue = jsonParam.get(queryAttr);
			    
				if("Integer".equals(fieldType)){
					Integer attributeValueInteger = SToolUtils.object2Int(attributeValue);
			        parperParam(sb, fieldName, queryAttr, attributeValueInteger, queryParamMap, operator);
				}else if("Long".equals(fieldType)){
					Long attributeValueInteger = Long.parseLong(SToolUtils.object2String(attributeValue));
			        parperParam(sb, fieldName, queryAttr, attributeValueInteger, queryParamMap, operator);
				}else{
					String attributeValueStr = SToolUtils.object2String(attributeValue);
			        parperParam(sb, fieldName, queryAttr, attributeValueStr, queryParamMap, operator);
				}
			}
		} catch (NumberFormatException | NoSuchFieldException | SecurityException e) {
			//e.printStackTrace();
		}
	}
  
	/**
	 * 生成sql条件
	 * @param jsonParam
	 * @param queryAttr
	 * @param sb
	 * @param queryParamMap
	 * @param operator
	 */
    public static void parperParam(JSONObject jsonParam, String queryAttr, StringBuffer sb, Map<String, Object> queryParamMap, String operator) {
        parperParam(jsonParam, queryAttr, queryAttr, sb, queryParamMap, operator);
    }
    /**
     * 生成sql条件
     * @param jsonParam
     * @param fieldName
     * @param queryAttr
     * @param sb
     * @param queryParamMap
     * @param operator
     */
    public static void parperParam(JSONObject jsonParam, String fieldName, String queryAttr, StringBuffer sb, Map<String, Object> queryParamMap, String operator) {
        if(null == jsonParam){
            return;    
        }
        if (jsonParam.containsKey(queryAttr) && !"".equals(jsonParam.get(queryAttr).toString())&&jsonParam.getString(queryAttr)!=null) {
            Object attributeValue = jsonParam.get(queryAttr);
            parperParam(sb, fieldName, queryAttr, attributeValue, queryParamMap, operator);//update by zhonghanyong 2018-11-20 取消类型转换
//            if(SToolUtils.isNumber(attributeValue)){
//                //Integer attributeValueInteger = SToolUtils.object2Int(attributeValue);
//            	Long attributeValueInteger = Long.parseLong(SToolUtils.object2String(attributeValue));
//                parperParam(sb, fieldName, queryAttr, attributeValueInteger, queryParamMap, operator);
//            }else{
//                String attributeValueStr = SToolUtils.object2String(attributeValue);
//                parperParam(sb, fieldName, queryAttr, attributeValueStr, queryParamMap, operator);
//            }            
        }
    }
    
    private static void parperParam(StringBuffer sb, String fieldName, String queryAttr, Object value, Map<String, Object> queryParamMap, String operator){
        if ("like".equals(operator.toLowerCase())) {
        	sb.append(" and " + fieldName + " " + operator + " :" + queryAttr);
            queryParamMap.put(queryAttr, "%" + value + "%");
        } else if("in".equals(operator.toLowerCase())){
            if(value instanceof Integer){
                sb.append(" and " + fieldName + " in ( " + value + " )");    
            }else if(value instanceof String){
                //sb.append(" and " + fieldName + " in ( '" + value.toString().replaceAll(",", "','") + "')");
				//按照“,”分割，取得数组(英文逗号为分隔符)
				String[] str = value.toString().split(",");
				if(null != str && str.length>0){
					String valueStr = "";
					for (String k : str) {
						valueStr += "'" + k + "',";
					}
					valueStr = valueStr.substring(0,valueStr.length()-1);
					sb.append(" and " + fieldName + " in ( " + valueStr+ " )");
				}
            }
        }else {
        	sb.append(" and " + fieldName + " " + operator + " :" + queryAttr);
            queryParamMap.put(queryAttr, value);
        }
    }

	/**
	 * json对象转换成java对象
	 *
	 * @param clazz      要转换的java对象类型
	 * @param jsonObject json对象
	 * @param obj        如果提供该参数，则会将数据封装到该对象中，否则新实例化一个对象
	 * @param <T>        java对象泛型
	 * @return 返回转换后的java对象
	 * @throws Exception 反射类产生的异常
	 * @autor xuwen
	 */
	public static <T> T json2javaObj(Class<T> clazz, JSONObject jsonObject, T obj) throws Exception {
		return json2javaObjWithSuf(clazz, jsonObject, obj, null);
	}

	/**
	 * json对象转换成java对象
	 *
	 * @param clazz      要转换的java对象类型
	 * @param jsonObject json对象
	 * @param obj        如果提供该参数，则会将数据封装到该对象中，否则新实例化一个对象
	 * @param <T>        java对象泛型
	 * @param suffix     json的键映射到bean的属性需要添加的后缀，如果键和bean的属性名一样，则不需要该参数
	 * @return 返回转换后的java对象
	 * @throws Exception 反射类产生的异常
	 * @autor xuwen
	 * @date 2018/12/14
	 */
	public static <T> T json2javaObjWithSuf(Class<T> clazz, JSONObject jsonObject, T obj, String suffix) throws Exception {
		if (obj == null) {
			obj = clazz.newInstance();
		}
		if (suffix == null) {
			suffix = "";
		}
		//从json中取值的键
		String key;
		//获取该bean的属性描述
		BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		//将属性赋值给javabean
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			//使用该bean的一个属性加后缀构成从json中取值的键
			key = propertyDescriptor.getName() + suffix;
			if (jsonObject.get(propertyDescriptor.getName() + suffix) == null) {
				//该键不存在
				continue;
			}
			//反射将json键对应的值映射到bean的对应属性中
			Class<?> propertyType = propertyDescriptor.getPropertyType();
			String jsonMethodName = "get" + propertyType.getSimpleName();
			Method jsonMethod = JSONObject.class.getDeclaredMethod(jsonMethodName, String.class);
			propertyDescriptor.getWriteMethod().invoke(obj, jsonMethod.invoke(jsonObject, key));
		}
		return obj;
	}

	/**
	 * 生成邮件模板
	 * <pre>
	 *     代码示例：
	 *     String[] paragraphs = {"hello world!!", "zhangsan lisi wangwu!!"};
	 *     String[] tableTitles = new String[]{"物料名称", "物料号"};
	 *     List&lt;Map&lt;String, String&gt;&gt; rowList = new ArrayList&lt;&gt;();
	 *     Map&lt;String, String&gt; tabRow = new HashMap&lt;&gt;(10);
	 *     tabRow.put(tableTitles[0], "hello");
	 *     tabRow.put(tableTitles[1], "world");
	 *     rowList.add(tabRow);
	 *     String template = generateEmailTemplate("hello!!", paragraphs, tableTitles, rowList);
	 * </pre>
	 *
	 * @param firstLine   第一行文字，没有缩进效果，可以为null
	 * @param paragraphs  多个段落，有缩进效果，可以为null
	 * @param tableTitles 表格标题内容，可以为null
	 * @param rows        表格行数据，每个map为一行数据，map需要使用表格的标题内容作为键，可以为null
	 * @return 返回生成的邮件模板字符串
	 * @author xuwen
	 * @date 2018/12/3
	 */
	public static String generateEmailTemplate(String firstLine, String[] paragraphs, String[] tableTitles, List<Map<String, String>> rows) {
		StringBuilder template = new StringBuilder();
		//first line
		if (firstLine != null && !"".equals(firstLine)) {
			template.append("<p>").append(firstLine).append("</p>");
		}
		//paragraph
		if (paragraphs != null && paragraphs.length != 0) {
			for (String paragraph : paragraphs) {
				template.append("<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;").append(paragraph).append("</p>");
			}
		}
		//tableTitle
		if (tableTitles == null || tableTitles.length == 0 || rows == null || rows.size() == 0) {
			return template.toString();
		}
		template.append("<table border=\"1\" cellspacing=\"0\" style=\"text-align:center;table-layout:fixed;width:80%\">");
		template.append("<thead><tr>");
		for (String tabTitle : tableTitles) {
			template.append("<th>").append(tabTitle).append("</th>");
		}
		template.append("</tr></thead>");
		//tableRow
		template.append("<tbody>");
		for (Map<String, String> tabRow : rows) {
			template.append("<tr>");
			for (String tabTitle : tableTitles) {
				template.append("<td style=\"word-wrap: break-word\">")
						.append(tabRow.get(tabTitle) == null ? "" : tabRow.get(tabTitle))
						.append("</td>");
			}
			template.append("</tr>");
		}
		template.append("</tbody>");
		template.append("</table>");
		return template.toString();
	}

	/**使用HashSet实现List去重(无序)
	 *
	 * @param list
	 * */
	public static List removeDuplicationByHashSet(List<Integer> list) {
		HashSet set = new HashSet(list);
		//把List集合所有元素清空
		list.clear();
		//把HashSet对象添加至List集合
		list.addAll(set);
		return list;
	}

}
