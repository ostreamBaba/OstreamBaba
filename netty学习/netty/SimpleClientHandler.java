package com.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.charset.Charset;

/**
 * @ Create by ostreamBaba on 18-7-25
 * @ ����
 */
public class SimpleClientHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //���ܷ�����������������
        if(msg instanceof ByteBuf){
            System.out.println(((ByteBuf) msg).toString( Charset.defaultCharset()));
        }

        ctx.channel().close();

    }
}
