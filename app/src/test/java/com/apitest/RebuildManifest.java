package com.apitest;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2016/8/4.
 */
public class RebuildManifest
{
    @Test
    public void testRebuild() throws Exception
    {
        String file="D:\\CODE\\Practice\\APITest\\app\\src\\main\\AndroidManifest.xml";

        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] buffer=new byte[fileInputStream.available()];
        System.out.println(buffer.length+"\r\n");
        fileInputStream.read(buffer);
        fileInputStream.close();

        String content=new String(buffer);
        System.out.println(content+"\r\n");

        String first="<meta-data android:name=\"test\" android:value=\"";
        String second="\"/>";
        String keyValue = content.split(first)[1].split(second)[0];
        String replace="BBB";
        String str1=first+keyValue+second;
        String str2=first+replace+second;
        content=content.replace(str1,str2);

        System.out.println(content+"\r\n");

        FileOutputStream fileOutputStream=new FileOutputStream(file);
        buffer=content.getBytes();
        fileOutputStream.write(buffer);
        fileOutputStream.close();

    }
}
