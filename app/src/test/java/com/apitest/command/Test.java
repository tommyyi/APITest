package com.apitest.command;

import com.apitest.command.cmd.CommandControl;
import com.apitest.command.cmd.LightOffCommand;
import com.apitest.command.cmd.LightOnCommand;
import com.apitest.command.target.Stereo;
import com.apitest.command.cmd.StereoOffCommand;
import com.apitest.command.cmd.StereoOnCommand;
import com.apitest.command.target.Light;

/**
 * Created by Administrator on 2016/6/14.
 */
public class Test
{
    @org.junit.Test
    public void test()throws Exception
    {
        CommandControl commandControl=new CommandControl();
        Light light=new Light();
        Stereo stereo=new Stereo();
        LightOnCommand lightOnCommand=new LightOnCommand(light);
        LightOffCommand lightOffCommand=new LightOffCommand(light);
        StereoOnCommand stereoOnCommand=new StereoOnCommand(stereo);
        StereoOffCommand stereoOffCommand=new StereoOffCommand(stereo);

        commandControl.setCommand(0,lightOnCommand,lightOffCommand);
        commandControl.setCommand(1,stereoOnCommand,stereoOffCommand);

//        commandControl.buttonOff(0);
//        commandControl.buttonOff(1);
//        commandControl.buttonOff(2);
//        commandControl.buttonOn(0);
//        commandControl.buttonOn(1);
//        commandControl.buttonOn(2);
    }
}
