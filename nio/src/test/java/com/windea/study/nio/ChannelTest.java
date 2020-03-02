package com.windea.study.nio;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;

import static java.nio.channels.FileChannel.MapMode.READ_ONLY;
import static java.nio.channels.FileChannel.MapMode.READ_WRITE;
import static java.nio.file.StandardOpenOption.*;

public class ChannelTest {
	@Test
	public void testDir() {
		System.out.println(new File("1.txt").getAbsolutePath()); //相对于项目的工作路径（如果是子项目则为子项目的）
		System.out.println(new File("/1.txt").getAbsolutePath()); //相对于磁盘
		System.out.println();
		System.out.println(ChannelTest.class.getResource("1.txt")); //file协议，url，相对于classpath中对应的包，url编码
		System.out.println(ChannelTest.class.getResource("1.txt").toExternalForm()); //同上
		System.out.println(ChannelTest.class.getResource("1.txt").getPath()); //同上
		System.out.println();
		System.out.println(ChannelTest.class.getResource("1.txt").getFile()); //以/开头，url编码，相对于classpath中的对应的包
		System.out.println(ChannelTest.class.getResource("/1.txt").getFile()); //同上格式，相对于classpath
		System.out.println(ChannelTest.class.getClassLoader().getResource("1.txt").getFile()); //同上格式，相对于classpath
		//System.out.println(ChannelTest.class.getClassLoader().getResource("/1.txt").getFile()); //NPE
	}

	//使用通道完成文件的复制（非直接缓冲区）
	@Test
	public void test1() {
		//注意这里的路径相当于项目的工作目录的，区分子项目，不区分main和test
		try(
			var inputStream = new FileInputStream("assets/1.png");
			var outputStream = new FileOutputStream("assets/2.png");
			var inputChannel = inputStream.getChannel();
			var outputChannel = outputStream.getChannel()
		) {
			//分配指定大小的缓冲区
			var buffer = ByteBuffer.allocate(1024);
			//将通道中的数据写入缓冲区中
			while(inputChannel.read(buffer) != -1) {
				buffer.flip(); //切换成读取数据的模式
				//将缓冲区中的数据写入通道中
				outputChannel.write(buffer);
				//清空缓冲区
				buffer.clear();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	//使用通道完成文件的复制（直接缓冲区，内存映射文件）
	@Test
	public void test2() throws IOException {
		var inputChannel = FileChannel.open(Path.of("assets", "1.png"), READ);
		var outputChannel = FileChannel.open(Path.of("assets", "3.png"), READ, WRITE, CREATE);

		//代表内存映射文件
		var inputMappedBuffer = inputChannel.map(READ_ONLY, 0, inputChannel.size());
		var outputMappedBuffer = outputChannel.map(READ_WRITE, 0, inputChannel.size());

		//直接往缓冲区中放入数据即可
		byte[] dst = new byte[inputMappedBuffer.limit()];
		inputMappedBuffer.get(dst);
		outputMappedBuffer.put(dst);

		inputChannel.close();
		outputChannel.close();
	}

	@Test
	public void test3() throws IOException {
		var inputChannel = FileChannel.open(Path.of("assets", "1.png"), READ);
		var outputChannel = FileChannel.open(Path.of("assets", "3.png"), READ, WRITE, CREATE);

		inputChannel.transferTo(0, inputChannel.size(), outputChannel);

		inputChannel.close();
		outputChannel.close();
	}

	@Test
	public void test4() throws IOException {
		var file = new RandomAccessFile("assets/1.txt", "rw");

		var channel1 = file.getChannel();

		var buffer1 = ByteBuffer.allocate(100);
		var buffer2 = ByteBuffer.allocate(1024);

		//分散读取
		ByteBuffer[] buffers = {buffer1, buffer2};
		channel1.read(buffers);

		for(ByteBuffer buffer : buffers) {
			buffer.flip();
		}

		System.out.println(new String(buffer1.array()));
		System.out.println(new String(buffer2.array()));

		System.out.println();

		//聚集写入
		var file2 = new RandomAccessFile("assets/2.txt", "rw");
		var channel2 = file2.getChannel();
		channel2.write(buffers);
	}
}
