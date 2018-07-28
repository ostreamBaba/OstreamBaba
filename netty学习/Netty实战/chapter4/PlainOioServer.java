package com.netty.NettyInAction.chapter4;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @ Create by ostreamBaba on 18-7-27
 * @ OIO
 */
public class PlainOioServer {


    public void serve(int port) throws IOException {
        ServerSocket socket=new ServerSocket(port); //���������󶨵�ָ���˿�
        try{
            for (;;){
                final Socket clientSocket=socket.accept(); //��������
                System.out.println(
                        "Accepted connection form "+clientSocket
                );
                new Thread(new Runnable() { //����һ���߳������������
                    @Override
                    public void run() {
                        OutputStream out;
                        try{
                            out=clientSocket.getOutputStream();
                            out.write("Hi!1\r\n".getBytes(  //����Ϣд�������ӵĿͻ���
                                    Charset.forName("UTF-8")
                            ));
                            out.flush();
                            clientSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }finally {
                            try {
                                clientSocket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } ).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
