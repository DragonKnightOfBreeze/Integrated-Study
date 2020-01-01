package com.windea.study.designpattern.command;

public class LightOnCommand implements Command {
	LightReceiver lightReceiver;

	public LightOnCommand(LightReceiver lightReceiver) {
		this.lightReceiver = lightReceiver;
	}

	@Override
	public void execute() {
		this.lightReceiver.on();
	}

	@Override
	public void undo() {

	}
}
