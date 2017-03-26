package com.lib.StreamUitl;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class StreamUitl {
	/**
	 * 可以从输入流读取文件，保存到指定路径下<br>
	 * 注意：该方法会关流
	 * @param in
	 */
   public static void saveFileByStream(InputStream in,String path){
	   try{
		   BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(path));
	   }catch(Exception e){
		   
	   }
		   
   }
}
