package com.lib.StreamUitl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamUitl {
	/**
	 * 可以从输入流读取文件，保存到指定路径下<br>
	 * 注意：该方法会关流
	 * @param in
	 */
   public static void saveFileByStream(InputStream in,String path){
	   BufferedOutputStream bos=null;
	   try{
		   bos=new BufferedOutputStream(new FileOutputStream(path));
		   byte b[]=new byte[1024];
		   int len=0;
		   while((len=in.read(b))>-1){
			   bos.write(b, 0, len);
		   }
		   bos.flush();
	   }catch(Exception e){
		   e.printStackTrace();
	   }finally{
		   try{
			   if(bos!=null){
				   bos.close();
			   }
			   in.close();
		   }catch(Exception e){
			   e.printStackTrace();
		   }
	   }
   }
   /**
    * 将字节流转换成字符串
    * @param in 需要转换的字节流
    * @param characterSet字符编码
    * @return 转换完毕的字符串
    * @throws IOException
    */
      public static String inputStreamConversionString(InputStream in,String characterSet) throws IOException{
		   BufferedReader read = new BufferedReader(new InputStreamReader(in,characterSet));
		   StringBuilder sb = new StringBuilder();
		   String line = "";
		   while((line = read.readLine())  !=  null){
			   sb.append(line);
		   }
		   return sb.toString();
	}
}
