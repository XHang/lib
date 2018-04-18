package com.lib.gzip;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class GZIPUtil {

    /**
     * 将字节数组压缩
     * @return 压缩后的字节数组
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
            gzipOut.write(bytes,0,bytes.length);
            //由于我们传入压缩流的构造器是一个字节数组流，所以可以直接取出里面的数据
          //  gzipOut.finish();
            bytes =  out.toByteArray();
            System.out.println("压缩后的字节数组大小为"+bytes.length);
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
}