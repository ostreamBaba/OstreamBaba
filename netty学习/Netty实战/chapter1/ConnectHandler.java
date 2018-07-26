package com.netty.NettyInAction.chapter1;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @ Create by ostreamBaba on 18-7-26
 * @ Netty�ڲ�ʹ�ûص��������¼� ��һ���ص�������ʱ ��ص��¼����Ա�һ��interface-ChannelHandler��ʵ�ִ���
 */
public class ConnectHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //��һ���µ������Ѿ�������ʱ���÷����ᱻ����
        System.out.println("Client "+ctx.channel().remoteAddress()+" connect");
    }
}
