package com.apitest;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/7/28.
 */
public class URL2FetchIpTest
{
    @Test
    public void testFetchIp() throws Exception
    {
        URL2FetchIp url2FetchIp=new URL2FetchIp();
        url2FetchIp.getIp();
    }
}