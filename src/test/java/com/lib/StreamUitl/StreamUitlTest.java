package com.lib.StreamUitl;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class StreamUitlTest {

    @Test
    public void inputStreamToByte() throws IOException {

        InputStream in = this.getClass().getResource("/testFile.txt").openStream();
        byte[] b = StreamUitl.inputStreamToByte(in);
        System.out.println(new String(b,"utf-8"));
    }
}