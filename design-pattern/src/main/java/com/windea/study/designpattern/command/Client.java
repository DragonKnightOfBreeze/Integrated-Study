package com.windea.study.designpattern.command;

public class Client {
    public static void main(String[] args) {
        var lightReceiver = new LightReceiver();
        var lightOnCommand = new LightOnCommand(lightReceiver);
        var lightOffCommand = new LightOffCommand(lightReceiver);

        var remoteController = new RemoteController();
        remoteController.setCommand(0, lightOnCommand, lightOffCommand);

        System.out.println("开灯。");
        remoteController.onButtonWasPushed(0);
        System.out.println("关灯。");
        remoteController.offButtonWasPushed(0);
        System.out.println("撤销最近操作。");
        remoteController.undoButtonWasPushed();
    }
}
