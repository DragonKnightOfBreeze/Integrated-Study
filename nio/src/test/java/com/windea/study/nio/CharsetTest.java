package com.windea.study.nio;

import org.junit.Test;

import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

public class CharsetTest {
    @Test
    public void test1() throws CharacterCodingException {
        var gbk = Charset.forName("GBK");
        var encoder = gbk.newEncoder();
        var decoder = gbk.newDecoder();

        var charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("你好，世界！");
        var byteBuffer = encoder.encode(charBuffer);
        System.out.println(byteBuffer.toString());

        var charBuffer2 = decoder.decode(byteBuffer);
        System.out.println(charBuffer2.toString());
    }
}
