package com.windea.study.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.time.LocalDateTime;
import java.util.Iterator;

import static java.nio.channels.SelectionKey.OP_ACCEPT;

public class NonBlockingNIOTest1 {
    @Test
    public void test() {
        var client = new Thread(() -> client());
        client.start();
        server();
    }

    public void server() {
        try {
            //获取通道
            var serverChannel = ServerSocketChannel.open();

            //切换到非阻塞模式
            serverChannel.configureBlocking(false);
            //绑定连接
            serverChannel.bind(new InetSocketAddress(9898));

            //获取选择器
            var selector = Selector.open();
            //注册选择器并且监听接收事件
            serverChannel.register(selector, OP_ACCEPT);
            //轮询获取选择器上已经准备就绪的事件
            while(selector.select() > 0) {
                //迭代获取选择键
                for(Iterator<SelectionKey> iterator = selector.selectedKeys().iterator(); iterator.hasNext(); ) {
                    SelectionKey selectionKey = iterator.next();
                    //判断具体是什么事件准备就绪
                    if(selectionKey.isAcceptable()) {
                        //若接收就绪，就获取客户端的连接
                        var socketChannel = serverChannel.accept();
                        //切换到非阻塞模式
                        socketChannel.configureBlocking(false);
                        //监听读状态
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if(selectionKey.isReadable()) {
                        //若读就绪，则获取对应的通道
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                        //读取数据
                        var buffer = ByteBuffer.allocate(1024);
                        int len;
                        while((len = socketChannel.read(buffer)) != -1) {
                            buffer.flip();
                            System.out.println(new String(buffer.array(), 0, len));
                            buffer.clear();
                        }
                    }
                    //取消选择键
                    iterator.remove();
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void client() {
        try {
            //获取通道
            var clientChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

            //切换到非阻塞模式
            clientChannel.configureBlocking(false);

            //发送数据到服务端
            var buffer = ByteBuffer.allocate(1024);
            buffer.put(LocalDateTime.now().toString().getBytes());
            buffer.flip();
            clientChannel.write(buffer);
            buffer.clear();

            clientChannel.close();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
