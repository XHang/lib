package com.lib.XStreamUitl;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * 该类是自己实现的一个XStream空转换器
 * 该转换器是通用的
 * 实现目标：
 * XStream对于对象中空字段的处理是不处理成xml的。
 * 该转换器可以实现空字段处理成空标签
 * 注意：该类无法处理对象的父类字段！
 * TODO  可能会添加父类支持
 * @author 航
 * 基本完成品。。。
 *
 */
public class XStreamNullConverter implements  Converter{
	private Map<String,String> map;

	/**
	 * 指定哪个类对象适用此转换器
	 * TODO 请选择你要转换的对象类型！
	 * @param type xStream传进查询的类对象
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public boolean canConvert(Class type) {
		return false;
	}
	/**
	 * 对象序列化成xml的主要方法
	 * @param source 需要序列化的对象
	 * @param writer 写xml数据的方法
	 * @param 上下文，一般用不到
	 */
	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer,
			MarshallingContext context) {
			//如果当前对象是List集合，则进入list集合处理阶段
			if (source instanceof List){
				listProcess(source, writer);
			}else{
				Map<String,Object> fieldsAndValue = getObjectFiledAndValue(source);
				generateXmlData(fieldsAndValue ,writer);
			}
	}
	/**
	 * 对list属性进行处理
	 * 如果传来的对象不是list对象，则不做任何处理
	 * @param source list对象
	 * @param writer 笔
	 */
	@SuppressWarnings("unchecked")
	private void listProcess(Object source, HierarchicalStreamWriter writer) {
			List<Object> beans=(List<Object>)source;
			for(Object obj:beans){
				writer.startNode(obj.getClass().getName());
				marshal(obj,writer,null);
				writer.endNode();
		}
		
		
	}
	/**
	 * 将xml数据解序列化为对象，暂时不考虑该方法的实现
	 */
	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		return null;
	}
	/**
	 * 可以将对象的某个字段名输出为其他字段名
	 * @param map key为对象的原字段名，value为xml输出的标签名
	 */
	public XStreamNullConverter(Map<String,String> map){
		this.map = map;
	}
	/**
	 * 根据对象获取里面的字段和值，根据set，get方法
	 * @param source 需要获取字段和值的对象
	 * @return 包含字段名和值的map对象
	 */
	private Map<String ,Object> getObjectFiledAndValue(Object source){
		Map<String ,Object> fieldsAndValue = new HashMap<String ,Object>();
		Method[] methods = source.getClass().getMethods();
		String fieldName="";
		Object  fieldValue=null;
		try{
			for(Method method:methods){
				if(method.getName().indexOf("get") != -1 && !("getClass").equals(method.getName())){
					String methodName=method.getName();
					fieldName=firstCharLowerCase(methodName.substring(3));
					fieldValue=method.invoke(source, new Object[]{});
					fieldsAndValue.put(fieldName, fieldValue);
				} 
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return fieldsAndValue;
	}
	/**
	 * 根据字段和值，生成xml
	 * @param fieldsAndValue
	 * @param writer
	 */
	private void generateXmlData(Map<String ,Object> fieldsAndValue ,HierarchicalStreamWriter writer){
		
		for(Map.Entry<String, Object> entry: fieldsAndValue.entrySet() ){
			writeXml(entry.getKey(),entry.getValue(),writer);
		}
	}
	/**
	 * 查找特殊字段名
	 * 主要是看传来的字段名是不是需要更改为用户指定的其他子段名
	 * 以便在xml输出用户指定的字段名
	 * 
	 * @param fieldName 原先字段名
	 * @return 用户指定的其他字段名，如果没有，返回原先字段名
	 */
	private String findSpecialFieldName(String fieldName){
		for(Map.Entry<String, String> ent:map.entrySet()){
			if(ent.getKey().equals(fieldName)){
				return ent.getValue();
			}
		}
		return fieldName;
	}
	/**
	 * 判断传进来的类型是否是基本数据类型
	 * @param type
	 * @return
	 */
	  private boolean isBaseType(Class<?> type)
	    {
	        if (type.equals(Integer.class)
	            || type.equals(Double.class)
	            || type.equals(String.class)    
	            || type.equals(Boolean.class)
	            || type.equals(Long.class)
	            ||type.equals(Short.class)
	            ||type.equals(Byte.class)
	            ||type.equals(Float.class))
	        {
	            return true;
	        }
	        return false;
	    }
	  /**
	   * 输出xml
	   * @param fieldName 作为标签名的字段名
	   * @param fieldValue  作为标签值的字段值
	   * @param writer  笔
	   */
	  private void writeXml(String fieldName,Object fieldValue,HierarchicalStreamWriter writer){
		  //要判断一下用户不想传这个值的话的情况
		  if(map!=null){
			  writer.startNode(findSpecialFieldName(fieldName));
		  }else{
			  writer.startNode(fieldName);
		  }
		 
		   if(fieldValue==null){
			   writer.setValue("");
		   }else if(!(isBaseType(fieldValue.getClass()))){
			   marshal(fieldValue,writer,null);
		   }else{
				writer.setValue(fieldValue.toString());
		   }
			writer.endNode();
	  }
	  /**
	     * 该方法负责将字符串的首字母改成小写
	     * 该方法为摘抄段
	     * @param source
	     * @return
	     */
	    private String firstCharLowerCase(String source){
	    	 char[] cs = source.toCharArray(); 
	         cs[0] += 32;
	         return String.valueOf(cs);
	    }
}
