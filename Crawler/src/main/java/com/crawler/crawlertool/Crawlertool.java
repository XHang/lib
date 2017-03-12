package com.crawler.crawlertool;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreConnectionPNames;

public class Crawlertool {
	 public static  CloseableHttpClient httpclient = HttpClients.createDefault();        
	 /**
     * 可给定图片地址下载图片到指定的路径
     * @param picUrl   图片的地址
     * @param path      图片的路径
     */
    public  static void downloadPhoto(String picUr,String path){
    	RequestConfig conf=RequestConfig.custom()  
        .setConnectTimeout(30000) 	//连接超时和
        .setSocketTimeout(60000).build();								//获取数据超时
        HttpGet httpGet=new HttpGet(picUr);
        httpGet.setConfig(conf);  										//把超时等配置设置进去
        CloseableHttpResponse response=null;
        File file=new File(path);
        if(file.exists()){
        	StringBuilder sb=new StringBuilder(path);
        	int insert=sb.indexOf(".");
        	path=sb.insert(insert, System.currentTimeMillis()).toString();
        	file=new File(path);
        }
        try( OutputStream os=new FileOutputStream(file)) {
            response=httpclient.execute(httpGet);
            if(response.getStatusLine().getStatusCode()==200){
            	response.getEntity().writeTo(os);
                System.out.println("图片"+file.getAbsolutePath()+"下载完毕");
            }else{
            	throw new Exception();
            }
        }catch (HttpHostConnectException e){
        	System.out.println("啊哦，下载这个文件超时了，跳过咯");
        	System.out.println("超时地址是这个"+picUr);
        	file.delete();
        	return;
        } catch(Exception e){
        	file.delete();
        	System.out.println("图片丢了咯，可能是连接重置什么鬼的");
        	System.out.println("丢丢丢的地址是这个"+picUr);
        	return;
        }finally {
                try {
                    if(response!=null){
                        response.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
         }
     }
    /**
     * 功能：下载页面，利用原始的字节流
     *@return boolean 爬取成功或失败
     *@param url 要爬取的链接
     *@param filename 爬取网页的保存文件路径
     */
    public static boolean downloadpath(String url,String filename){
        HttpGet httpGet=new HttpGet(url);
        BufferedInputStream bis=null;
        try ( BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(filename))){
            CloseableHttpResponse response=httpclient.execute(httpGet);
            HttpEntity  entity=   response.getEntity();
            bis=new BufferedInputStream(entity.getContent());
            int len=0;
            byte [] b=new byte[1024];
            while((len=bis.read(b))!=-1) {
                bos.write(b, 0, len);
            }
            System.out.println("下载成功");
            response.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally{
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    /**
     * 将字节输入流转换成字符串，可以将所给的字节输入流关闭
     * @param is：字节输入流
     * @return
     */
    public static String convertStreamToString(InputStream is) {      
         BufferedReader reader = new BufferedReader(new InputStreamReader(is));      
         StringBuilder sb = new StringBuilder();      
         String line = null;      
        try {      
            while ((line = reader.readLine()) != null) {      
                 sb.append(line + "\n");      
             }      
         } catch (IOException e) {      
             e.printStackTrace();      
         } finally {      
            try {      
                 is.close();      
             } catch (IOException e) {      
                 e.printStackTrace();      
             }      
         }      
        return sb.toString();      
     }
    /**
     * 可以将所给的文件名或者url地址查找出它的文件类型，其实就是后缀
     * @param url
     * @return
     */
    public static String getUrlSuffix(String url){
    	Pattern reg=Pattern.compile("\\.\\w{3,4}$");
		Matcher p=reg.matcher(url);
		p.find();
		return p.group();
    }
}
