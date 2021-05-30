1，NIO简介 及与 IO 的区别
一，Java NIO (New IO 也可以理解为 Non Blocking IO) 简介
        NIO 是从 jdk1.4版本开始引入的一个新的 IO API。可以替代标准的 java IO API。
        NIO 与 原来的 IO 有同样的作用和目的（都是用来传输数据的），但是使用方式完全不同，
        NIO支持面向缓冲区的、基于通道的IO操作。NIO 将以更加高效的方式进行文件的读写操作。
       
        NIO 在 jdk1.4 时，就有了。只不过那个时候，对于高并发的需求量，并不大，所以当时并没有流行起来。

        在jdk1.7 时，又对 NIO 进行了改进，以至于在jdk1.7 时对 NIO 的改动，称之为 NIO2 。

二，Java NIO 与 IO 的主要区别
IO	NIO
面向流（Stream）
单向的：输入流、输出流	面向缓冲区（Buffer）
双向的：即能输入，也能输出
阻塞IO（Blocking IO）
非阻塞IO（Non Blocking IO）
无	选择器（Selectors）
传统IO图解：

       面向流，且是单向流。
NIO 图解


通道：表示打开 程序 和 IO设备（如，文件，套接字）之间的连接。
缓冲区（Buffer）：一个用于特定基本数据类型的容器。由java.nio包定义的，所有缓冲区都是Buffer
      抽象类的子类。主要用于与NIO通道进行交互，数据是从通道读入缓冲区，从缓冲区写入通道中的。