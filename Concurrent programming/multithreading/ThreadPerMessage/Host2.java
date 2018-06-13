package com.multithreading.ThreadPerMessage;


import java.util.concurrent.Executor;

/**
 * @ Create by ostreamBaba on 18-6-13
 * @ ����
 */
public class Host2 {
    private final Helper helper=new Helper();
    private final Executor executor;

    public Host2(Executor executor) {
        this.executor = executor;
    }

    //ǰ��ThreadFactory�������̴߳�����ϸ�� ����δ���ش����̵߳Ĳ���
    //ʹ����Executor��Host2�� �����̵߳Ĳ���Ҳ������������
    public void request(final int count, final char c){
        System.out.println(" request("+count+", "+c+") BEING");
        executor.execute(new Runnable() {
            @Override
            public void run() {
                helper.handle(count,c);
            }
        } );
        System.out.println(" request("+count+", "+c+") END");
    }
}
