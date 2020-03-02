package com.windea.study.nio;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Scanner;

import static java.nio.channels.SelectionKey.OP_READ;

public class NonBlockingNIOTest2 {
	@Ignore //这段代码在gradle+junit4环境下不可行，不知道视频中为什么能够测试通过
	@Test
	public void test() throws InterruptedException {
		var send = new Thread(() -> send());
		var receive = new Thread(() -> receive());
		send.start();
		receive.start();
		send.join();
		receive.join();
	}

	public void receive() {
		try {
			try(var dc = DatagramChannel.open()) {
				dc.configureBlocking(false);
				dc.bind(new InetSocketAddress(9898));
				var selector = Selector.open();
				dc.register(selector, OP_READ);
				while(selector.select() > 0) {
					for(Iterator<SelectionKey> iterator = selector.selectedKeys().iterator(); iterator.hasNext(); ) {
						SelectionKey selectedKey = iterator.next();
						if(selectedKey.isReadable()) {
							var buffer = ByteBuffer.allocate(1024);
							dc.receive(buffer);
							buffer.flip();
							System.out.println(new String(buffer.array()));
							buffer.clear();
						}
						iterator.remove();
					}
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void send() {
		try {
			try(var dc = DatagramChannel.open()) {
				dc.configureBlocking(false);
				ByteBuffer buffer = ByteBuffer.allocate(1024);

				var scanner = new Scanner(System.in);
				while(scanner.hasNext()) {
					var str = scanner.next();
					buffer.put((LocalDateTime.now().toString() + "\n" + str).getBytes());
					buffer.flip();
					dc.send(buffer, new InetSocketAddress("127.0.0.1", 9898));
					buffer.clear();
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	//虽然视频中是这么写的，但是实际测试junit无法等待控制台输入，多线程还有坑
	@Test
	public void scannerTest() {
		var scanner = new Scanner(System.in);
		while(scanner.hasNext()) {
			var str = scanner.next();
			System.out.println("output: " + str);
		}
	}
}
