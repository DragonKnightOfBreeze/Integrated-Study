package com.windea.study.designpattern.command;

public class LightOffCommand implements Command {
    LightReceiver lightReceiver;

    public LightOffCommand(LightReceiver lightReceiver) {
        this.lightReceiver = lightReceiver;
    }

    @Override
    public void execute() {
        this.lightReceiver.off();
    }

    @Override
    public void undo() {

    }
}
