package com.netty.NettyInAction.chapter2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @ Create by ostreamBaba on 18-7-26
 * @ ����
 */
public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception{
        if(args.length!=1){
            System.err.println("Usage: "+EchoServer.class.getSimpleName()+" <port>");
        }
        int port=Integer.parseInt(args[0]);
        new EchoServer(port).start(); //���÷�������start����
    }

    public void start() throws InterruptedException {
        final EchoServerHandler serverHandler=new EchoServerHandler();
        EventLoopGroup group=new NioEventLoopGroup();
        try{
            ServerBootstrap bootstrap=new ServerBootstrap();
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class) //ָ����ʹ�õ�NIO����Channel
                    .localAddress(new InetSocketAddress(port))//ʹ��ָ���Ķ˿������׽��ֵ�ַ
                    .childHandler( new ChannelInitializer<SocketChannel>() {//���һ��EchoServerHandler����Channel��ChannelPipeline
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(serverHandler);//EchoServerHandler�����Ϊ@Shareable
                            // �������ǿ�������ʹ��ͬ����ʵ��
                            //�����еĿͻ���������˵ ����ʹ��ͬһ��EchoServerHandler(��Ϊ�䱻��עΪ@Sh   areable)
                        }
                    });
            ChannelFuture future=bootstrap.bind().sync();//�첽�ذ󶨷����� ����sync()���������ȴ�ֱ�������
            future.channel().closeFuture().sync(); //��ȡChannel��CloseFuture ����������ǰ�߳�ֱ�������

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully().sync(); //�ͷ�������Դ
        }
    }
}
