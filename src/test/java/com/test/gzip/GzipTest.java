package com.test.gzip;

import com.lib.gzip.GZIPUtil;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.GZIPOutputStream;


public class GzipTest {



    public static void main(String [] args) throws IOException {
        String str = "说起来你可能不信，这是一个即将加压的数据,什么，不信，那我就再多说几句，咦咦咦咦咦";
        System.out.println("即将加压的字符串为"+str);
        System.out.println("压缩前的数据长度是:"+str.getBytes().length);
        byte[] compressBytes = GZIPUtil.compress(str.getBytes("UTF-8"));
        System.out.println("加压后的字节数组长度为:"+compressBytes.length);
        System.out.println("加压后的字符串是");
        System.out.println(new String(compressBytes,"utf-8"));
        byte[] umCompressBytes = GZIPUtil.umcompress(compressBytes);
        System.out.println("解压后的字节长度为:"+umCompressBytes.length);
        System.out.println("解压后的字符串为:"+new String(umCompressBytes,"UTF-8"));
    }


}
