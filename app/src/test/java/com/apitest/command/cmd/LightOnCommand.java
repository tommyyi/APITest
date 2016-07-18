package com.apitest.command.cmd;

import com.apitest.command.imp.Command;
import com.apitest.command.target.Light;

/**
 * Created by Administrator on 2016/6/15.
 */
public class LightOnCommand implements Command
{
    private Light mLight;

    public LightOnCommand(Light light)
    {
        mLight = light;
    }

    @Override
    public void execute()
    {
        mLight.on();
    }

    @Override
    public void undo()
    {
        mLight.undo();
    }
}
