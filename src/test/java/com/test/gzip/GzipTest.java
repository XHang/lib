package com.test.gzip;

import com.lib.gzip.GZIPUtil;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.GZIPOutputStream;


public class GzipTest {



    public static void main(String [] args) throws IOException {
        String str = "dgf";
        System.out.println("压缩前的数据是:"+str.getBytes().length);
        byte[] bytes = GZIPUtil.compress(str.getBytes());
        System.out.println(bytes.length);
    }


}
