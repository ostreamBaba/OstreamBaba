package com.multithreading.ThreadPerMessage;

import java.util.concurrent.ThreadFactory;

/**
 * @ Create by ostreamBaba on 18-6-13
 * @ ����
 */


//ʹ��java.util.concurrent.ThreadFactory
//ʹ��new����Threadʵ��ʱ ����������Thread��  �����޷����ƴ����̵߳Ĳ��� �ɸ����ԱȽϵ�
//ʹ��threadFactory������ThreadFactory���� ��threadFactory.newThread����new Thread �������� ֻҪ�滻��ֵ��threadFactory
//��ThreadFactory�Ķ��� ���Ǳ���Կ����̵߳Ĵ���
public class Host1 {
    private final Helper helper=new Helper();
    private final ThreadFactory threadFactory;

    public Host1(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
    }

    public void request(final int count,final char c){
        System.out.println(" request("+count+", "+c+") BEING");
        threadFactory.newThread(
                new Runnable() {
                    @Override
                    public void run() {
                        helper.handle(count,c);
                    }
                }
        ).start();
        System.out.println(" request("+count+", "+c+") END");
    }
}
