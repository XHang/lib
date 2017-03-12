package com.Blog.Util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

public class DownloadFileUtil {
	/**
	 * 
	 * @param filename  文件名
	 * @param type 文件类型
	 * @param path	   文件所在目录
	 * @param response 响应
	 * @throws UnsupportedEncodingException 
	 */
	public static void downloadFile(String filename, String path,HttpServletResponse response) throws UnsupportedEncodingException {
		 File file = new File(path+filename);	//获取被下载文件对象
		 Pattern p=Pattern.compile("(\\.\\w{3,})$");		//匹配后缀名
		 Matcher m=p.matcher(filename);
		 m.find();
		 String type=m.group();
		 //设置响应类型：如果不设置怎么样？
		 if("pdf".equals(type)){
             response.setContentType("application/pdf;charset=GBK");
          }else if("csv".equals(type)){
             response.setContentType("application/msexcel;charset=GBK");
          }else if("doc".equals(type)){
             response.setContentType("application/msword;charset=GBK");
          }else if("xls".equals(type)){
                 response.setContentType("application/msexcel;charset=GBK");
          }
		  //设置http头信息，当浏览器接受到这个头信息，会激发文件下载对话框
		  response.setHeader("Content-Disposition", "attachment;filename=\"" + new String(filename.getBytes(), "ISO8859-1") + "\"");
		  response.setContentLength((int) file.length());				//设置内容长度
		  byte[] buffer = new byte[4096];								//设置缓冲区
		  BufferedOutputStream output = null;
          BufferedInputStream input = null;
          try {
              output = new BufferedOutputStream(response.getOutputStream());
              input = new BufferedInputStream(new FileInputStream(file));
              int n = -1;
              //遍历，开始下载
              while ((n = input.read(buffer, 0, 4096)) != -1) {
                 output.write(buffer, 0, n);
              }
              output.flush();   
              response.flushBuffer();
            } catch (Exception e) {
             	e.printStackTrace();     
            } finally {
            	try{
            		if (input != null)
                         input.close();
                    if (output != null)
                         output.close();
            	}catch(IOException e){
            		System.out.println("关闭流失败！");
            	}
              
            }
	}

}
