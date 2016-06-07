package com.apitest;

/**
 * Created by Administrator on 2016/5/25.
 */
public class TianLaiResponse
{

    /**
     * result : 0
     * sid : 6970
     * sms : Base64Encode(sms1)& Base64Encode(sms2)
     * port :  1065843601&1065842230
     * req : 0FEE6CB9AA6D8BE793590F6142CD0D10
     * orderno : CT48625684957176
     */

    private int result;
    private String sid;
    private String sms;
    private String port;
    private String req;
    private String orderno;

    public int getResult()
    {
        return result;
    }

    public void setResult(int result)
    {
        this.result = result;
    }

    public String getSid()
    {
        return sid;
    }

    public void setSid(String sid)
    {
        this.sid = sid;
    }

    public String getSms()
    {
        return sms;
    }

    public void setSms(String sms)
    {
        this.sms = sms;
    }

    public String getPort()
    {
        return port;
    }

    public void setPort(String port)
    {
        this.port = port;
    }

    public String getReq()
    {
        return req;
    }

    public void setReq(String req)
    {
        this.req = req;
    }

    public String getOrderno()
    {
        return orderno;
    }

    public void setOrderno(String orderno)
    {
        this.orderno = orderno;
    }
}
