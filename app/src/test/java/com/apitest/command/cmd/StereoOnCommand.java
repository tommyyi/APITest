package com.apitest.command.cmd;

import com.apitest.command.imp.Command;
import com.apitest.command.target.Stereo;

/**
 * Created by Administrator on 2016/6/15.
 */
public class StereoOnCommand implements Command
{
    public StereoOnCommand(Stereo stereo)
    {
        mStereo = stereo;
    }

    private Stereo mStereo;
    @Override
    public void execute()
    {
        mStereo.on();
        mStereo.setCd();
    }

    @Override
    public void undo()
    {
        mStereo.undo();
    }
}
