package com.apitest.command.cmd;

import com.apitest.command.imp.Command;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/15.
 */
public class MacroCommand implements Command
{
    public ArrayList<Command> getCommandList()
    {
        return mCommandList;
    }

    ArrayList<Command> mCommandList;

    public MacroCommand()
    {
        mCommandList = new ArrayList<Command>();
    }

    @Override
    public void execute()
    {
        int length=mCommandList.size();
        for(int index=0;index<length;index++)
            mCommandList.get(index).execute();
    }

    @Override
    public void undo()
    {
        int length=mCommandList.size();
        for(int index=0;index<length;index++)
            mCommandList.get(index).undo();
    }
}
