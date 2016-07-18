package com.apitest.status;

/**
 * Created by Administrator on 2016/6/15.
 */
public class Test
{
    @org.junit.Test
    public void test() throws Exception
    {
        CandyMachine candyMachine=new CandyMachine(2);
        candyMachine.printState();
        candyMachine.turnCrank();
        candyMachine.printState();
        candyMachine.insertCoin();
        candyMachine.printState();
        candyMachine.returnCoin();
        candyMachine.printState();
        candyMachine.insertCoin();
        candyMachine.printState();
        candyMachine.turnCrank();
        candyMachine.printState();
        System.out.print("******************************************************************\r\n");

        candyMachine.insertCoin();
        candyMachine.printState();
        candyMachine.returnCoin();
        candyMachine.printState();
        candyMachine.insertCoin();
        candyMachine.printState();
        candyMachine.turnCrank();
        candyMachine.printState();
        System.out.print("******************************************************************\r\n");

        candyMachine.insertCoin();
        candyMachine.printState();
        candyMachine.returnCoin();
        candyMachine.printState();
        candyMachine.insertCoin();
        candyMachine.printState();
        candyMachine.turnCrank();
        candyMachine.printState();
        System.out.print("******************************************************************\r\n");
    }
}
