package com.crawler.Result;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.crawler.Enum.TranslateType;
import com.crawler.Model.CurrentWeather;
import com.crawler.Model.Weather;


public class WeatherCrawler {
   static  CloseableHttpClient httpclient = HttpClients.createDefault();        
    /**
     * 
     * @param cityName 城市名
     * @return 查询成功或者失败
     * 该函数直接打印当前的天气和未来几天的天气，如果需要更改，请修改service的源码
     */
    public static boolean getWeatherByXml(String cityName){

        BufferedInputStream bis=null;
        HttpGet httpGet=new HttpGet("http://wthrcdn.etouch.cn/WeatherApi?city="+cityName);
        CloseableHttpResponse response=null;
        SAXReader reader = new SAXReader();
        try {
            response=httpclient.execute(httpGet);
            HttpEntity  entity=   response.getEntity();
            bis=new BufferedInputStream(entity.getContent());
            org.dom4j.Document doc=reader.read(bis);
            WeatherCrawler.fillingWeather(doc);
            
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch( DocumentException e){
            
        }
        return false;
    }
    
    /**
     * 传来一个包含天气的xml文档，返回未来几天天气对象的集合
     * 该函数和xml文档绑定，如果xml文档发生改变，该方法也将废弃
     * @param doc
     * @return
     */
    public static List<Weather> fillingWeather(org.dom4j.Document doc){
		List<Weather> weathers=new ArrayList<Weather>();
		org.dom4j.Element rootElement=doc.getRootElement();                                              //获取xml文档的根标签
		org.dom4j.Element error=rootElement.element("error");
		if(error!=null){
		    throw new RuntimeException("城市值非法！");
		}
		String city=rootElement.elementText("city");                                              //获取xml文档里面的城市名称
		CurrentWeather currentWeather=new CurrentWeather();
		currentWeather.setCity(city);
		currentWeather.setUpTime(rootElement.elementText("updatetime"));
		currentWeather.setTemperature(rootElement.elementText("wendu"));
		currentWeather.setWindPower(rootElement.elementText("fengli"));
		currentWeather.setHumidity(rootElement.elementText("shidu"));
		currentWeather.setSunrise(rootElement.elementText("sunrise_1"));
		currentWeather.setSunset(rootElement.elementText("sunset_1"));
		System.out.println(currentWeather);
		List<org.dom4j.Element> li=rootElement.element("forecast").elements("weather");   //便利每一个weather取出最近几天的天气状况
		 for(org.dom4j.Element e:li){
			 Weather w=new Weather();
			 w.setCity(city);
			 w.setDate(e.elementText("date"));
			 w.setTemperatureRange(e.elementText("low")+"到"+e.elementText("high"));
			 org.dom4j.Element day=e.element("day");
			 org.dom4j.Element night=e.element("night");
			 w.setDayWeather(day.elementText("type"));
			 w.setDayDirectionAndWindpower(day.elementText("fengxiang")+day.elementText("fengli"));
			 w.setNightWeather(night.elementText("type"));
			 w.setNightDirectionAndWindpower(night.elementText("fengxiang")+day.elementText("fengli"));
			 System.out.println(w);
			 weathers.add(w);
		 }
		return weathers;
	}
}




