package com.apitest.command.cmd;

import com.apitest.command.imp.Command;
import com.apitest.command.imp.Control;

import java.util.Stack;

/**
 * Created by Administrator on 2016/6/15.
 */
public class CommandControl implements Control
{
    Command onCommands[];
    Command offCommands[];
    MacroCommand mMacroCommandOn=new MacroCommand();
    MacroCommand mMacroCommandOff=new MacroCommand();
    Stack<Command> mCommandStack=new Stack<Command>();
    public CommandControl()
    {
        onCommands=new Command[5];
        offCommands=new Command[5];

        for(int index=0;index<offCommands.length;index++)
        {
            onCommands[index]=new NoCommand();
            offCommands[index]=new NoCommand();
        }
    }

    @Override
    public void buttonOn(int slot)
    {
        onCommands[slot].execute();
        mCommandStack.push(onCommands[slot]);
    }

    @Override
    public void buttonOff(int slot)
    {
        offCommands[slot].execute();
        mCommandStack.push(offCommands[slot]);
    }

    @Override
    public void undo()
    {
        mCommandStack.pop().undo();
    }

    @Override
    public void batchExecuteOn()
    {
        mMacroCommandOn.execute();
    }

    public void setCommand(int slot,Command onCommand,Command offCommand)
    {
        onCommands[slot]=onCommand;
        offCommands[slot]=offCommand;
        mMacroCommandOn.getCommandList().add(onCommand);
        mMacroCommandOff.getCommandList().add(offCommand);
    }
}
