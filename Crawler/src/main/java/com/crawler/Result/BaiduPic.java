package com.crawler.Result;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.crawler.crawlertool.Crawlertool;

public class BaiduPic {
    /**
     * 下载百度图片搜索，只需要关键字
     * @param keyWord
     * number  下载的张数(待完成)
     */
    private static CloseableHttpClient httpclient = HttpClients.createDefault(); 
    public static void  downloadBaiduPic(String keyWord,int number) throws Exception{
       String urlEncoder=null;
       //BufferedInputStream bis=null;
       CloseableHttpResponse response=null;
       urlEncoder=URLEncoder.encode(keyWord, "utf-8");
       HttpGet httpGet=new HttpGet("http://image.baidu.com/search/avatarjson?tn=resultjsonavatarnew&ie=utf-8&word="+urlEncoder);
       httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:51.0) Gecko/20100101 Firefox/51.0");
       httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
       httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
       response=httpclient.execute(httpGet);
       String jsonStr=Crawlertool.convertStreamToString(response.getEntity().getContent());
       JSONObject json=JSON.parseObject(jsonStr);
       JSONArray jsonArray=json.getJSONArray("imgs");
       for(int i=0;i<jsonArray.size();i++){
    	   String picName=System.getProperty("user.dir")+"//resources//picture//"+jsonArray.getJSONObject(i).getString("fromPageTitle").replaceAll("<(.{6,7})>|\\s", "");
    	   String picUrl=jsonArray.getJSONObject(i).getString("objURL");
    	   picName=picName+"."+jsonArray.getJSONObject(i).getString("type");
    	   Crawlertool.downloadPhoto(picUrl, picName);
    	   System.out.println("第"+i+"图片处理完毕");
       }
       response.close();
   }
}
