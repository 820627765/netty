package com.example.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        /*
        1，创建服务端Socket 对象，TCP协议的服务端Socket 用 ServerSocket 来表示，需要指定服务端Socket 端口
           其实端口，默认用的就是 本机的ip。
        2，监听客户端的socket 连接
        3，通过监听到的Socket对象来获取IO流，通过这个IO流来实现和客户端的读写交流
        4，关闭输入输出流，ServerSocket 是不能关闭的，因为服务端肯定不需要关闭
         */
        ServerSocket server = new ServerSocket(8000);
        /*
        等待客户端的连接，一旦有连接，就会返回当前和服务端连接的这个Socket对象
        这是一个阻塞方法，且同一时刻只能接收一个客户端的Socket连接。
         */
        Socket accept = server.accept();
        InputStream inputStream = accept.getInputStream();
        byte[] buf = new byte[1024];
        //这是一个阻塞方法，只有当客户端发送数据，才能执行，否则一直阻塞等待
        int readLen = inputStream.read(buf);
        if(readLen != -1){
            System.out.println("接收到客户端的数据" + new String(buf,0,buf.length));
        }

        //向客户端发送数据
        accept.getOutputStream().write("hello client1".getBytes());
        accept.getOutputStream().write("hello client1 again".getBytes());
        //注意执行一次 flush 才表示发送一次数据，不执行就没有发送
        accept.getOutputStream().flush();

        accept.getOutputStream().write("hello client2".getBytes());
        accept.getOutputStream().flush();

        accept.getOutputStream().write("hello client3".getBytes());
        accept.getOutputStream().flush();

        //关闭输出流
        accept.getOutputStream().close();

        accept.getOutputStream().write("hello client4".getBytes());
        accept.getOutputStream().flush();
        accept.getOutputStream().close();
    }
}
