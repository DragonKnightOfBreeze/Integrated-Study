package com.windea.study.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

public class BufferTest {
	@Test
	public void test1() {
		//分配一个指定大小的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);

		System.out.println();
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		System.out.println(buffer.capacity());

		//存入数据
		var str = "abc";
		buffer.put(str.getBytes());

		System.out.println();
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		System.out.println(buffer.capacity());

		//切换到读取数据模式
		buffer.flip();

		System.out.println();
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		System.out.println(buffer.capacity());

		//读取数据
		System.out.println();
		var bytes = new byte[buffer.limit()];
		buffer.get(bytes);
		var string = new String(bytes);
		System.out.println(string);

		System.out.println();
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		System.out.println(buffer.capacity());

		//准备重新读取数据
		buffer.rewind();

		System.out.println();
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		System.out.println(buffer.capacity());

		//清空数据
		buffer.clear();

		System.out.println();
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		System.out.println(buffer.capacity());
	}

	@Test
	public void test2() {
		var str = "hello";
		var buffer = ByteBuffer.allocate(1024);

		buffer.put(str.getBytes());
		buffer.flip();

		var bytes = new byte[buffer.limit()];
		buffer.get(bytes, 0, 2);
		System.out.println(new String(bytes, 0, 2));
		System.out.println(buffer.position());

		//标记当前的位置
		buffer.mark();

		buffer.get(bytes, 2, 2);
		System.out.println(new String(bytes, 2, 2));
		System.out.println(buffer.position());

		//恢复到被标记的位置
		buffer.reset();
		System.out.println(buffer.position());
	}
}
