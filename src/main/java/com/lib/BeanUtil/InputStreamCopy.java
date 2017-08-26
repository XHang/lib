package com.lib.BeanUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * 该类可以实现一些常见对象的复制
 * @author Mr-hang
 *
 */
public class InputStreamCopy {
	/**
	 * 可以复制InputStream对象
	 * @param in
	 * @return
	 * @throws IOException 
	 */
	private static InputStreamCopy beanCopy=new InputStreamCopy();;
	private InputStreamCopy(){}
	public static InputStreamCopy getBeanCopyObject(){
		return beanCopy ;
	}
	byte[] cache=new byte[1024*1024];			//存储inputStream流的字节数组
	int cacheSize=0;							//inputStream流的字节数组的大小
	/**
	 * 该方法可以拷贝一个InputStream对象<br>
	 *注意：本方法不负责关流。<br>
	 *注意，一旦执行了该方法，参数的输入流就不再可读，除非重置指针
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public  InputStream inputStreamCopy(InputStream in) throws IOException{
		if(cacheSize==0){
			byte [] b=new byte[1024];
			int len=0;
			while((len=in.read(b))>-1){
				System.out.println("len"+len);
				System.out.println("已经拷贝"+cacheSize);
				addByte(b,len);
			}
		}
		return new ByteArrayInputStream(cache, 0, cacheSize);
	}
	/**
	 * 该函数可以将从输出流取得的一小段字节数组存到类的缓存中。
	 * 实现缓存效果
	 * @param b  一小段字节数组
	 * @param len 该字节数组的有效长度
	 */
	private void addByte(byte[] b,int len){
		if(cacheSize+len>=cache.length){		//如果当前的缓冲区实际大小加上即将加入的数据量大于缓冲区的最大限度，则需要扩充缓冲区
			expandCache();
		}
		for(int poi=0;poi<len;poi++){
			cache[cacheSize]=b[poi];
			cacheSize++;
		}
	}
	/**
	 * 该函数可以扩充类的缓冲区，可增加1M的存储量
	 */
	private void expandCache(){
		byte[] temp=new byte[cacheSize+1024*1024];
		System.arraycopy(cache, 0, temp, 0, cacheSize);			//复制的长度是上一个缓冲区的大小
		cache=temp;
	}
	
	/**
	 * 递归方法，实例bean对象的所有属性
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public  static  Object ClassToObj(Class<?> clazz) throws Exception{
		Object obj = clazz.newInstance();
		for(Method method:clazz.getMethods()){
		if((method.getName().startsWith("get"))){
				Class<?> returnclazz  = method.getReturnType();
				if(isInstantiation(returnclazz)){
					String setMethod = "set"+method.getName().substring(3);
					Method setmethod = null;
					try {
						setmethod = clazz.getMethod(setMethod, returnclazz);
					} catch (NoSuchMethodException e) {
						//有set方法，却没有get方法，就不设置属性了。
						continue;
					}
					Object returnObj = ClassToObj(returnclazz);
					setmethod.invoke(obj, returnObj);
				}
			}
		}
		return obj;
		
	}
	/**
	 * 判断类型是否可用于json输出
	 * @param clazz
	 * @return
	 */
	public static boolean isInstantiation(Class<?> clazz){
		try{
				clazz.newInstance();
				/*if(clazz.equals(Object.class)){
					return false;
				}*/
				return true;
		}catch(Exception e){
			return false;
		}
	}
	
}
