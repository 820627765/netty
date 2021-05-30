package com.example.bio;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        //创建客户端的Socket，客户端的Socket 也是用 DatagramSocket 类。注意需要指定客户端端口号
        DatagramSocket datagramSocket = new DatagramSocket(8000);

        //创建一个接收数据的数据包
        DatagramPacket datagramPacket = new DatagramPacket(new byte[1024],1024);

        /*
        通过 DatagramSocket 对象的 receive方法，获取数据，并将数据封装到指定的数据包中。
        注意，这是一个阻塞方法，即等待着服务方发送数据。
         */
        datagramSocket.receive(datagramPacket);

        /*
        从数据包中获取接收到的数据
        所以，只要有数据发送和接收，就能通过Socket 得到数据发送方的 IP 和 端口号  和数据
         */
        System.out.println("接收到数据：" + datagramPacket.getAddress()
                + ":" +  datagramPacket.getPort()
                + ":" + new String(datagramPacket.getData(),0,datagramPacket.getLength()));

        datagramSocket.close();
    }
}
