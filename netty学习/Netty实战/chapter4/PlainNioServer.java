package com.netty.NettyInAction.chapter4;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @ Create by ostreamBaba on 18-7-27
 * @ NIO
 */
public class PlainNioServer {

    public void server(int port) throws IOException {
        ServerSocketChannel serverChannel=ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        ServerSocket serverSocket=serverChannel.socket();
        InetSocketAddress address=new InetSocketAddress(port);
        serverSocket.bind(address);//���������󶨵��˿�
        Selector selector=Selector.open();//��Selector������Channel
        serverChannel.register(selector, SelectionKey.OP_ACCEPT); //��ServerChannelע�ᵽSelector�Խ�������
        ByteBuffer msg=ByteBuffer.wrap("Hi!\r\n".getBytes());
        for(;;){
            try{
                selector.select(); //�ȴ���Ҫ��������¼��� ��������������һ�������¼�
            }catch (IOException e){
                e.printStackTrace();
                break;
            }
            Set<SelectionKey> readyKeys=selector.selectedKeys();//��ȡ���н����¼���SelectionKeyʵ��
            Iterator<SelectionKey> it=readyKeys.iterator();
            while (it.hasNext()){
                SelectionKey key=it.next();
                it.remove();
                try{
                    if(key.isAcceptable()){ //����¼��Ƿ���һ���µ��Ѿ��������Ա����ܵ�����
                        ServerSocketChannel server=(ServerSocketChannel) key.channel();
                        SocketChannel client=server.accept();
                        client.configureBlocking(false);
                        client.register(selector,SelectionKey.OP_WRITE|SelectionKey.OP_READ,
                                msg.duplicate()); //���ܿͻ��� ������ע�ᵽѡ����
                        System.out.println("Accepted connection form "+client);
                    }
                    if(key.isWritable()){ //����׽����Ƿ��Ѿ�׼����д����
                        SocketChannel client=(SocketChannel)key.channel();
                        ByteBuffer buffer= (ByteBuffer) key.attachment();
                        while (buffer.hasRemaining()){
                            if(client.write(buffer)==0){ //������д���Ѿ����ӵĿͻ���
                                break;
                            }
                        }
                        client.close();
                    }
                }catch (IOException e){
                    key.cancel();
                    try {
                        key.channel().close();
                    }catch (IOException ex){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
