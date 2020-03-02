package com.windea.study.nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class PipeTest {
	@Test
	public void test1() throws IOException {
		//获取数据
		var pipe = Pipe.open();
		try(
			var sinkChannel = pipe.sink();
			var sourceChannel = pipe.source()
		) {

			var buffer = ByteBuffer.allocate(1024);

			//将缓冲区中的数据写入管道
			buffer.put("hello".getBytes());
			buffer.flip();
			buffer.clear();
			sinkChannel.write(buffer);

			//从管道中读取数据
			int len = sourceChannel.read(buffer);
			System.out.println(new String(buffer.array(), 0, len));
		}
	}
}
