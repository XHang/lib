package com.crawler.Result;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.crawler.Enum.TranslateType;

public class Translation {
	private static  CloseableHttpClient httpclient = HttpClients.createDefault(); 
		/**
	    * 调用谷歌简单的api进行翻译
	    * 注：不需要cookie，需要和被翻译的文字计算出tk值才可以
	    * 然而我选择了最简单的谷歌api
	    * 
	    * @param keyWord          翻译的关键字
	    * @param TranslateType 选择从什么文字翻译到什么文字
	    * @return 翻译是否成功，翻译结果是直接打印出来的。
	    */
	    public static boolean translationByGoogle(String keyWord,TranslateType trpe ){
	        String urlEncoder=null;
	        try {
	            urlEncoder=URLEncoder.encode(keyWord, "utf-8");
	        } catch (UnsupportedEncodingException e1) {
	            e1.printStackTrace();
	        }
	        HttpGet httpGet=new HttpGet("http://translate.google.cn/translate_a/single?client=gtx&"+trpe.getParameter()+"&dt=t&q=google="+urlEncoder);
	        httpGet.setHeader("User-Agent:", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:51.0) Gecko/20100101 Firefox/51.0");                               //必须设置这个消息头，否则会乱码且查询不正确
	        try {
	            CloseableHttpResponse httpResponse=httpclient.execute(httpGet);
	            HttpEntity  entity=httpResponse.getEntity();
	            InputStream in=entity.getContent();
	            StringBuilder jsonSb=new StringBuilder();               //定义存储json的字符串缓冲区
	            StringBuilder tagerSb=new StringBuilder();              //定义存储翻译结果的字符串缓冲区
	            int len=0;
	            byte [] b=new byte[1024];
	            while((len=in.read(b))!=-1) {
	                jsonSb.append(new String(b,0,len,"utf-8") );    
	            }
	            in.close();
	            JSONArray jsonArray=JSON.parseArray(jsonSb.toString()).getJSONArray(0); //获取存储翻译结果的第二层数组，该数组里面的数组对象才是真正存储翻译结果的数据
	            for(int i=0;i<jsonArray.size();i++){
	                String tager=jsonArray.getJSONArray(i).get(0).toString();
	                tager= tager.substring(tager.indexOf("=")==-1?0:tager.indexOf("=")+1);
	                tagerSb.append(tager);
	            }
	            System.out.println("翻译结果："+tagerSb.toString());
	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
}
