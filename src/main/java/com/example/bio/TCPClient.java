package com.example.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        /*
        1，创建客户端Socket 对象，需要指定客户端Socket 的IP 和 端口
        2，建立连接
        3，通过io流来读写和服务端交流数据，通过Socket对象来获取IO流
        4，关闭Socket，关闭Socket即可，不需要关闭通过Socket获取的输入/输出流
         */
        Socket client = new Socket("127.0.0.1",8000);
        //通过 Socket 获取向服务端发送数据的 输出流 对象
        OutputStream outputStream = client.getOutputStream();
        //向服务端发送数据
        outputStream.write("hello server".getBytes());


        /*
        接收服务端的数据，当然这也是一个阻塞方法，会一直等待。
         */
        InputStream inputStream = client.getInputStream();
        byte[] buf = new byte[1024];
        while(true){
            /*
            该方法会获取一次服务端 flush 的数据。所以这里加了一个 死循环来，获取服务端每次 flush 的数据。
             */
            int readLen = inputStream.read(buf);
            if(readLen != -1){
                System.out.println("获取到服务端的数据：" + new String(buf,0,buf.length));
            }
        }
    }
}
