package com.netty.NettyInAction.chapter1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @ Create by ostreamBaba on 18-7-26
 * @ �ص�ʵս
 */
public class ConnectExample {

    private static final Channel CHANNEL=new NioSocketChannel();

    public static void connect(){
        Channel channel=CHANNEL;
        final ChannelFuture future=channel.connect(
                new InetSocketAddress("192.168.0.1",25)
        ); //�첽�����ӵ�Զ�̽ڵ�
        //Does no block
        //ע��һ��ChannelFutureListener �Ա��ڲ������ʱ���֪ͨ
        future.addListener( new ChannelFutureListener() {
            //������״̬
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if(channelFuture.isSuccess()){
                    //�����ɹ�����һ��ByteBuf�Գ�������
                    ByteBuf buffer= Unpooled.copiedBuffer("hello", Charset.defaultCharset());
                    //�������첽�ط��͵�Զ�̵Ľڵ�
                    ChannelFuture wf=channelFuture.channel().writeAndFlush(buffer);
                }else{
                    Throwable cause=channelFuture.cause(); //���ʹ������������ԭ���Throwable
                    cause.printStackTrace();
                }
            }
        } );
    }
}
