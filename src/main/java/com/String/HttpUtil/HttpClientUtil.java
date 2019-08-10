package com.novel.util;

import com.lib.StreamUitl.StreamUtil;
import com.lib.reg.RegUtil;
import com.sun.java.browser.net.ProxyService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

public class HttpClientUtil {
    private static HttpClient httpClient;

    /**
     * 获取网页内容，默认是用GBK编码
     *
     * @param url
     * @return
     */
    public static String getWebContent(String url) {
        return getWebContent(url, "gbk");
    }

    /**
     * 获取网页内容
     *
     * @param url
     * @param characterSet
     * @return
     */
    public static String getWebContent(String url, String characterSet) {
        try {
            InputStream inputStream = getWebPageStream(url);
            String responseText = StreamUtil.inputStreamConversionString(inputStream, characterSet);
            return responseText;
        } catch (IOException e) {
            throw new RuntimeException("文章流转字符串失败");
        }
    }

    /**
     * 获取网页字节流
     *
     * @param url
     * @return
     */
    public static InputStream getWebPageStream(String url) {
        try {
            HttpGet getClient = new HttpGet(url);
            getClient.setConfig(getConfig());
            httpClient = HttpClients.createDefault();
            HttpResponse httpResponse = null;
            httpResponse = httpClient.execute(getClient);
            checkHttpResponse(httpResponse);
            if (isIpRefuse(httpResponse)) {
                httpResponse = tryRequest(httpClient, getClient);
            }
            return httpResponse.getEntity().getContent();
        } catch (IOException e) {
            throw new RuntimeException(String.format("爬取网页[%s]失败", url), e);
        }
    }

    /**
     * 检查响应
     *
     * @param url
     * @return
     */
    public static void checkHttpResponse(HttpResponse httpResponse) {
        int stauteCode = httpResponse.getStatusLine().getStatusCode();
        if (stauteCode != 200) {
            throw new IllegalStateException(String.format("请求失败，http状态码【%d】不是200",stauteCode));
        }
    }

    public static boolean isIpRefuse(HttpResponse httpResponse) {
        int stauteCode = httpResponse.getStatusLine().getStatusCode();
        //TODO 请自行修改需要切换IP代理的情况
        return false;
    }


    /**
     * 获取默认配置
     *
     * @return
     */
    public static RequestConfig getConfig() {
        RequestConfig localConfig = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
                .build();
        return localConfig;
    }

    public static void changeProxy(HttpGet get) {
        /*ProxyService proxyService = SpringUtil.getBean(ProxyService.class);
        RequestConfig localConfig = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.IGNORE_COOKIES).
                        setProxy(proxyService.next()).
                        setConnectTimeout(10000).setSocketTimeout(10000).setConnectionRequestTimeout(10000)
                .build();
        get.setConfig(localConfig);*/
        //TODO 请自行变更代理
    }

    //当发生IP被拦截时使用IP代理尝试请求
    public static HttpResponse tryRequest(HttpClient httpClients, HttpGet get) {
        HttpResponse httpResponse = null;
        //失败次数
        int count = 0;
        while (true) {
            if (count > 100) {
                throw new RuntimeException(String.format("爬取网页重复次数【%d】过多，请变更策略",count));
            }
            try {
                count++;
                httpResponse = httpClient.execute(get);
                if (isIpRefuse(httpResponse)) {
                    changeProxy(get);
                    wait(1);
                    continue;
                }
                return httpResponse;
            }catch (IOException e){
                throw new RuntimeException("爬取网页失败了",e);
            }
        }
    }
    //等待一段时间
    public static void wait(int secone) {
        try {
            Thread.sleep(secone * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void downloadpath(String url,String folderPath){
        InputStream in = getWebPageStream(url);
        StreamUtil.saveFileByStream(in,folderPath+"/"+getLinkPrefix(url));
        return;
    }

    public static String getLinkPrefix(String url){
        String[] eles = url.split("/");
        return eles[eles.length-1];
    }
}
