package com.windea.study.designpattern.command;

public class RemoteController {
    private static Command noCommand = new NoCommand();
    private Command[] onCommands;
    private Command[] offCommands;
    private Command undoCommand; //这里可以使用栈跟踪历史命令

    public RemoteController() {
        onCommands = new Command[5];
        offCommands = new Command[5];

        for(int i = 0; i < 5; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
    }

    public void setCommand(int i, Command onCommand, Command offCommand) {
        onCommands[i] = onCommand;
        offCommands[i] = offCommand;
    }

    public void onButtonWasPushed(int i) {
        //执行命令
        onCommands[i].execute();
        //记录这次操作，用于撤销
        undoCommand = onCommands[i];
    }

    public void offButtonWasPushed(int i) {
        offCommands[i].execute();
        undoCommand = offCommands[i];
    }

    public void undoButtonWasPushed() {
        undoCommand.execute();
        undoCommand = noCommand;
    }
}
