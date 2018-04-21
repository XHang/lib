package com.lib.gzip;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPUtil {

    /**
     * 将字节数组压缩
     * @return 压缩后的字节数组
     * 注：如果你传入的字节数组过小，则最后可能会出现压缩后的字节数组反而变大了
     * 这是因为程序采取的字节容器是字节数组输出流，这货其实里面维护了一个有初始大小的字节数组
     * 就算你写入的压缩数据只有一丁点，最后toByteArray还是会把未使用的字节数组空间也给拿出来。
     * 就会导致压缩的数据比未压缩的数据要多了。
     * 你要是传大点的字节数组来压缩，就不会出现这种情况了
     */
    public static byte[] compress(byte[] bytes) throws IOException {
        GZIPOutputStream gzipOut = null;
        try {
            if (bytes == null) {
                throw new IllegalArgumentException("参数不可为空");
            }
            System.out.println("压缩前的字节数组大小为"+bytes.length);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            //装饰器模式，其实就是把一个输出流装饰一下，让他写入的方法具有压缩功能。
            gzipOut = new GZIPOutputStream(out);
            //写入的数据，流会自动压缩成压缩流。
            gzipOut.write(bytes);
            //由于我们传入压缩流的构造器是一个字节数组流，所以可以直接取出里面的数据
            gzipOut.finish();
            bytes = out.toByteArray();
            System.out.println("压缩大小为"+bytes.length);
            return bytes;
        }finally{
            if (gzipOut != null){
                try {
                    //字节输入输出流无需关闭，因为只是在内存操作，没有占用其他资源
                    gzipOut.close();
                } catch (IOException e) {
                    throw new RuntimeException("压缩输入流公关闭失败",e);
                }
            }
        }
    }

    /**
     * 对压缩字节进行解压
     * @param bytes 压缩字节
     * @return
     */
    public static  byte[] umcompress(byte[] bytes) throws IOException {
        GZIPInputStream gzip = null;
        try {
            ByteArrayInputStream byteInput = new ByteArrayInputStream(bytes);
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            gzip = new GZIPInputStream(byteInput);
            int b ;
            while((b=gzip.read())!= -1){
                byteOut.write(b);
            }
            gzip.close();
            return byteOut.toByteArray();
        } finally {
            if(gzip !=null){
                gzip.close();
            }
        }
    }
}