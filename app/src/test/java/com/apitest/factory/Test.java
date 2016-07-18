package com.apitest.factory;

/**
 * Created by Administrator on 2016/6/8.
 */
public class Test
{
    @org.junit.Test
    public void test() throws Exception
    {
//        Order order=new Order();
//        order.order(new MyFactory());
        order(new ChineseFactory());
    }

    public void order(AbstractFactory abstractFactory)
    {
        String type = getUserInput();
        Pizza pizza = abstractFactory.getPizza(type);

        if (pizza != null)
        {
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.bake();
        }
    }

    public String getUserInput()
    {
        /*String str="";
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            str = br.readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }*/
        return "cheese";
    }
}
