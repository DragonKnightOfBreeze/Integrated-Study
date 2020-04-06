package com.windea.study.designpattern.command;

/**
 * 空命令。当调用空命令时，对象什么都不做。
 */
public class NoCommand implements Command {
    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
