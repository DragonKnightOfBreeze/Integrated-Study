package com.windea.study.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.*;

public class BlockingNIOTest1 {
	@Test
	public void test() throws InterruptedException {
		var server = new Thread(() -> server());
		var client = new Thread(() -> client());
		server.start();
		client.start();
		server.join();
		client.join();
	}

	public void server() {
		try {
			//获取通道
			var serverChannel = ServerSocketChannel.open();
			//文件的通道
			var outputChannel = FileChannel.open(Path.of("assets", "4.png"), WRITE, CREATE);
			//绑定连接（本地连接）
			serverChannel.bind(new InetSocketAddress(9898));

			//获取客户端连接的通道
			var socketChannel = serverChannel.accept();

			//接受客户端的数据，并保存到本地
			var buffer = ByteBuffer.allocate(1024);
			while(socketChannel.read(buffer) != -1) {
				buffer.flip();
				outputChannel.write(buffer);
				buffer.clear();
			}

			socketChannel.close();
			outputChannel.close();
			serverChannel.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void client() {
		try {
			//获取通道
			var clientChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
			var inputChannel = FileChannel.open(Path.of("assets", "1.png"), READ);

			//读取本地文件并发送到服务端
			var byteBuffer = ByteBuffer.allocate(1024);
			while(inputChannel.read(byteBuffer) != -1) {
				byteBuffer.flip();
				clientChannel.write(byteBuffer);
				byteBuffer.clear();
			}

			inputChannel.close();
			clientChannel.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
