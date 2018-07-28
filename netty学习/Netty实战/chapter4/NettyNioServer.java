package com.netty.NettyInAction.chapter4;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @ Create by ostreamBaba on 18-7-27
 * @ ����
 */
public class NettyNioServer {

    public void server(int port) throws InterruptedException {
        final ByteBuf buf= Unpooled.unreleasableBuffer(
                Unpooled.copiedBuffer("Hi!\r\n",
                        Charset.forName("UTF-8"))
        );
        EventLoopGroup group=new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap=new ServerBootstrap(); //����ServerBootstrap
            bootstrap.group(group)
                    .channel( NioServerSocketChannel.class) //ʹ��NioEventLoopGroup����������ģʽ
                    .localAddress(new InetSocketAddress(port))
                    .childHandler( new ChannelInitializer<SocketChannel>() { //ָ��ChannelInitializer ����ÿ���ѽ��ܵ����Ӷ�������
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast(new ChannelInboundHandlerAdapter(){ //���غʹ����¼�
                                        @Override
                                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                            ctx.writeAndFlush(buf.duplicate())
                                                    .addListener( ChannelFutureListener.CLOSE);//����Ϣд��ͻ���
                                            // �����ChannelFutureListener�Ա���Ϣһ��д��͹ر�����
                                        }
                                    });
                        }
                    } );
            ChannelFuture future=bootstrap.bind().sync(); //�󶨷������Խ�������
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully().sync(); //�ͷ����е���Դ
        }
    }
}
