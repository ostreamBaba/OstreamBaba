package com.netty.NettyInAction.chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ Create by ostreamBaba on 18-7-26
 * @ ���� I/O
 */
public class BlockingIO {

    public void server(int portNumber) throws IOException {
        //int portNumber=8080;

        //����һ��ServerSocket ��������һ���ӿ�(��������)
        ServerSocket serverSocket=new ServerSocket(portNumber);
        //������ʹ��Accept���� ���һ�����Կͻ��˵�Socket���Ӷ���
        //��Accept�����ĵ��ý������� ֱ����һ�����ӽ���
        Socket clientSocket=serverSocket.accept();
        //��Щ�����������ڸ��׽��ֵ�������
        BufferedReader in=new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream())
        ); //���ַ��������ж�ȡ�ı�

        PrintWriter out=new PrintWriter(clientSocket.getOutputStream(),true);

        String request,response;
        while ((request=in.readLine())!=null){
            if("Done".equals(request)){
                break;
            }
            response=processRequest(request);  //���󴫵ݸ��������Ĵ�����
            //����������Ӧ�����͵��ͻ���
            out.print(response);
        }
    }

    private String processRequest(String request) {
        return "process";
    }


}
