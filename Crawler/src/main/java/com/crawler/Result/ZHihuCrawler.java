package com.crawler.Result;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import com.crawler.crawlertool.Crawlertool;

/**
 * 该爬虫主要爬取相关回答，并将回答保存到本地文件中
 * 迭代开发
 * 1：给定一个url地址，能将该url地址下面的所有回答都爬出来,OK，很6，把图片也爬出来了
 * 
 * @author Administrator
 *
 */
public class ZHihuCrawler {
    private static  CloseableHttpClient httpclient = HttpClients.createDefault(); 
    public static  void saveAnswer(String url){
        HttpGet httpGet=new HttpGet(url);
        CloseableHttpResponse response=null;
        try (BufferedWriter bw=new BufferedWriter(new FileWriter("Answer.txt"))){
           response=httpclient.execute(httpGet);
            Document doc= Jsoup.parse(response.getEntity().getContent(), "utf-8", url); 
            Elements articles=doc.select("div .js-collapse-body");          
            for( Element article:articles){                         //遍历所有文章标签
                article=article.select("div").get(2);               //获取文章主体的标签 
                List<TextNode> sub=article.textNodes();   //获取文章主体下面的所有文本标签，并遍历存储到文本中
                for(int i=0;i<sub.size();i++){   
                    bw.write(sub.get(i).text());
                    bw.newLine();
                }
                for(Element e:article.select("noscript").select("img")){           //遍历文章主体的所有img标签，如果有的话就哈哈哈哈
                    String imgUrl=e.attr("src");
                    Crawlertool.downloadPhoto(imgUrl, UUID.randomUUID().toString()+".jpg");
                }
                System.out.println("-------------------------------------------------");
               bw.flush();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
                    try {
                        if(response!=null){
                            response.close();
                        }
                        System.out.println("下载完毕");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }
}
