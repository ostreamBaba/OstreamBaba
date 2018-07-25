package com.netty;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @ Create by ostreamBaba on 18-7-25
 * @ ����
 */
public class SocketClient {

    public static void main(String[] args) throws IOException {
        Socket socket=new Socket("localhost",8080);
        DataOutputStream output=new DataOutputStream(socket.getOutputStream());
        output.writeInt(4);
        output.writeBytes("sssssss\r\nssss");
        //��һ��������ֻȡ \r\nǰ�������
        output.flush();

        DataInputStream input=new DataInputStream(socket.getInputStream());
        int length=input.readInt();
        byte[] bytes=new byte[length];
        input.readFully(bytes,0,length);
        System.out.println(new String(bytes));

        socket.close();
    }

}
