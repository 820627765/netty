package com.example.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServerSocket {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        while(true){
            //该方法是一个阻塞方法，会等待客户端的连接
            Socket accept = serverSocket.accept();
            //每次只能处理一个客户端连接，只有当上一个请求处理完成以后，才能处理下一个请求，单线程执行，性能不好。
            singleThreadHandle(accept);
            //
            multiThreadHandle(accept);
        }
    }

    /**
     * 处理方式1：
     * 使用主线程处理客户端请求（获取客户端发送的数据）
     *
     * 这种方法存在的问题是：
     *  - 每次只能处理一个客户端连接，只有当上一个请求处理完成以后，才能处理下一个请求，单线程执行，性能不好。
     * @param socket
     * @throws IOException
     */
    public static void singleThreadHandle(Socket socket) throws IOException {
        //从socket获取输入流
        InputStream inputStream = socket.getInputStream();
        byte[] buf = new byte[1024];
        //该方法是一个阻塞方法，会等待客户端输入
        int readLen = inputStream.read(buf);
        System.out.println("收到客户端的发送的数据：" + new String(buf,0,buf.length));
    }

    /**
     * 处理方式2：使用多线程优化
     * 多线程处理客户端socket
     * 客户端连接进来后，单独开启一个线程处理请求数据，不让读取输入里路数据阻塞主线程，让ServerSocket 又可以接收客户端请求
     *
     * 这种方式存在的问题：
     *  - 如果客户端来 10000+个连接请求，得开启10000+的线程，线程太多，线程之间的切换也会消耗整体性能。
     *    如果这些请求不发送数据 或 发送数据很慢，会造成连接一直占用。线程太多也会造成栈溢出。
     *  - 如果采用线程池：也会存在线程池满后，其他连接就只有进入阻塞队列了（当然这个看线程池的拒绝策略了）
     *
     * @param socket
     * @throws IOException
     */
    public static void multiThreadHandle(Socket socket) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //从socket获取输入流
                InputStream inputStream = null;
                try {
                    inputStream = socket.getInputStream();
                    byte[] buf = new byte[1024];
                    //该方法是一个阻塞方法，会等待客户端输入
                    int readLen = inputStream.read(buf);
                    System.out.println("收到客户端的发送的数据：" + new String(buf,0,buf.length));
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
