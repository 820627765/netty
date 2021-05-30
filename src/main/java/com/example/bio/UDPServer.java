package com.example.bio;

import java.io.IOException;
import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        /*
        UDP协议的服务端编写步骤：
        1）创建服务端Socket 对象，DatagramSocket 就是UDP协议的服务端对象
        2）把要发送的数据打包
        3）发送

        DatagramSocket 代表服务端 或 客户端 的Socket 套接字
        DatagramPacket 代表Socket 发送和接收的数据包

         */
        DatagramSocket datagramSocket = new DatagramSocket();
        //要发送的数据
        byte[] buf = "hello UDP Client".getBytes();
        //将要发送的数据通过 DatagramPacket 封装成数据包，数据包需要指定要发送的数据，客户端IP，客户端端口
        DatagramPacket datagramPacket = new DatagramPacket(buf,buf.length,
                InetAddress.getByName("127.0.0.1"),8000);
        /*
        调用套接字发送数据。因为UDP是不可靠协议，所以这里发送了，就算客户端没有，也会发送成功
         */
        datagramSocket.send(datagramPacket);

        //关闭资源
        datagramSocket.close();



    }
}
