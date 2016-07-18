package com.apitest.command.cmd;

import com.apitest.command.imp.Command;
import com.apitest.command.target.Light;

/**
 * Created by Administrator on 2016/6/15.
 */
public class LightOffCommand implements Command
{
    private Light mLight;

    public LightOffCommand(Light light)
    {
        mLight = light;
    }

    @Override
    public void execute()
    {
        mLight.off();
    }

    @Override
    public void undo()
    {
        mLight.undo();
    }
}
