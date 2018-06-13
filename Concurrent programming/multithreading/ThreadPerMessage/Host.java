package com.multithreading.ThreadPerMessage;

/**
 * @ Create by ostreamBaba on 18-6-12
 * @ ����
 */

//Host��ɫ�յ�Client��ɫ������֮�� �ᴴ��������һ���µ��߳�
public class Host {
    private final Helper helper=new Helper();
    public void request(final int count,final char c){
        System.out.println(" request("+count+", "+c+") BEING");

        new Thread(){
            @Override
            public void run() {
                helper.handle(count,c);
            }
        }.start();

        /*new Thread(  //����
                new Runnable() {
                    @Override
                    public void run() {
                        helper.handle(count,c);
                    }
                }
        ).start();

        Runnable runnable=new Runnable() {
            @Override  //����
            public void run() {
                helper.handle(count,c);
            }
        };
        new Thread(runnable).start();*/

        System.out.println(" request("+count+", "+c+") END");
    }
}
