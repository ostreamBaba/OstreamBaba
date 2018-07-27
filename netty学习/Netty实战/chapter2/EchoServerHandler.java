package com.netty.NettyInAction.chapter2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;


/**
 * @ Create by ostreamBaba on 18-7-26
 * @ ʵ��ҵ���߼�����
 */

@ChannelHandler.Sharable  //���ChannelHandler���Ա����Channel��ȫ�ع���
public class EchoServerHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof ByteBuf){
            ByteBuf in=(ByteBuf) msg;
            //����Ϣ��¼�ڿ���̨
            System.out.println("Server received: "+in.toString(CharsetUtil.UTF_8));
            ctx.write(in); //�����ܵ�����Ϣд�������� ������ˢ��վ��Ϣ
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //��δ����Ϣ��ˢ��Զ�̽ڵ� ���ҹرո�Channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace(); //��ӡ�쳣ջ����
        ctx.close(); //�رո�Channel
    }
}
