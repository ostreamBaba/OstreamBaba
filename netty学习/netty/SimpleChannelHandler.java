package com.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * @ Create by ostreamBaba on 18-7-25
 * @ ����
 */
public class SimpleChannelHandler extends ChannelInboundHandlerAdapter{

    /**
     * @ ���� ��ȡ�ͻ���ͨ������
     * @ param ctx
     * @ param msg
     * @ return void
     * @ create by ostreamBaba on ����8:31 18-7-25
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof ByteBuf){
            System.out.println(((ByteBuf) msg).toString( Charset.defaultCharset()));
            ctx.channel().writeAndFlush("is ok");
        }
    }
}
