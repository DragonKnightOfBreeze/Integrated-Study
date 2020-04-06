package com.windea.study.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.*;

//在第一个测试的基础上，客户端接收服务器的反馈

public class BlockingNIOTest2 {
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
            var serverSocketChannel = ServerSocketChannel.open();
            //文件的通道
            var outputChannel = FileChannel.open(Path.of("assets", "5.png"), WRITE, CREATE);

            //绑定连接（本地连接）
            serverSocketChannel.bind(new InetSocketAddress(9898));
            //获取客户端连接的通道
            var socketChannel = serverSocketChannel.accept();

            //接受客户端的数据，并保存到本地
            var buffer = ByteBuffer.allocate(1024);
            while(socketChannel.read(buffer) != -1) {
                buffer.flip();
                outputChannel.write(buffer);
                buffer.clear();
            }

            //向客户端发送反馈
            buffer.put("服务端接受数据成功".getBytes());
            buffer.flip();
            socketChannel.write(buffer);

            socketChannel.close();
            outputChannel.close();
            serverSocketChannel.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void client() {
        try {
            //获取通道
            var socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
            var inputChannel = FileChannel.open(Path.of("assets", "1.png"), READ);

            //读取本地文件并发送到服务端
            var byteBuffer = ByteBuffer.allocate(1024);
            while(inputChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                byteBuffer.clear();
            }
            socketChannel.shutdownOutput();

            //接受服务器的反馈
            int len;
            while((len = socketChannel.read(byteBuffer)) != -1) {
                byteBuffer.clear();
                System.out.println(new String(byteBuffer.array(), 0, len));
                byteBuffer.clear();
            }

            inputChannel.close();
            socketChannel.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
