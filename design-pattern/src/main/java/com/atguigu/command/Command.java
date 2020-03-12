package com.atguigu.command;


//��������ӿ�
public interface Command {

	//ִ�ж���(����)
	void execute();

	//��������(����)
	void undo();
}
