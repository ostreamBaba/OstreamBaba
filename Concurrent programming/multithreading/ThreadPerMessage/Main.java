package com.multithreading.ThreadPerMessage;

import java.util.concurrent.*;

/**
 * @ Create by ostreamBaba on 18-6-12
 * @ Thread-Per-Message ÿ����Ϣһ���߳�(Ϊÿ����������������һ���µ��߳�) ��Ϣ��ί�ж˺�ִ�ж��ǲ�ͬ���߳�
 */

/*
    Thread-Per-Messageģʽ

    �����Ӧ�� �����ӳ�ʱ��(��ģʽ�ܹ������Client��ɫ��Ӧ��Host��ɫ����Ӧ�� �����ӳ�ʱ��--������handle�����ǳ���ʱ�� ����handle������Ҫ�ȴ�����/���ʱ)
    ȱ�㣺 �����̻߳Ứ��ʱ�� ��Ҫ�����Ӧ��ʱ ����ʹ�ø�ģʽȡ����handle�����ѵ�ʱ����߳���������ʱ��ľ���

    �ʺϲ���˳��û��Ҫ��ʱ(��ģʽ�� handle������û�а���request�������õ�˳����ִ��)

    �����ڲ���Ҫ����ֵ

    Ӧ���ڷ����� �����������߳�ȥ���ܿͻ��˵����� ����Щ�����ʵ�ʴ����ǽ��������߳���ִ�� ������������߳��򷵻� ȥ�ȴ��ͻ��˵���������

    ���÷���+�����߳�-->������Ϣ

 */

public class Main {
    public static void main(String[] args) {
        //Client ί����
        System.out.println("main BEING");
        /*Host host=new Host();*/
        /*Host1 host=new Host1( new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable);
            }
        } );*/
        Host1 host=new Host1( Executors.defaultThreadFactory());
        //Executors.defaultThreadFactory() ���Ի�ȡ��ǰĬ�����õ�ThreadFactory����
        host.request(10,'A');
        host.request(20,'B');
        host.request(30,'C');
        System.out.println("main END");
    }
}

class Main1{
    public static void main(String[] args) {
        System.out.println("main BEING");
        Host2 host=new Host2(new Executor(){
            @Override
            public void execute(Runnable runnable) {
                new Thread(runnable).start();
            }
        });
        host.request(10,'A');
        host.request(20,'B');
        host.request(30,'C');
        System.out.println("main END");
    }
}

class Main2{
    public static void main(String[] args) {
        System.out.println("main BEING");
        //ʹ��ExecutorService����������Host����
        //Executors.newCachedThreadPool�����ᴴ�� �ɸ����߳���ExecutorService����
        //��Ȼ����ʹ����Executor�����˳��� �����ջ���Ҫ�Լ��ֶ�ִ��new Thread{..}
        //������ϸ���벢����ÿ�ζ����봴���߳� ֻҪ��ѭExecutor��� ���ǿ���ʹ�� "�Ḵ����Щ����ִ�н���������������߳�" ����--ExecutorService
        ExecutorService executorService=Executors.newCachedThreadPool();
        Host2 host=new Host2(executorService);
        try{
            host.request(10,'a');
            host.request(20,'b');
            host.request(30,'C');
        }finally {
            executorService.shutdown();
        }
        System.out.println("main END");
    }
}

class Main3{
    public static void main(String[] args) {
        System.out.println("main BEING");
        ScheduledExecutorService scheduledExecutorService=Executors.newScheduledThreadPool(5);//5��ʾ�޹���ʱҲ��һֱ���е��̸߳���
        Host3 host=new Host3(scheduledExecutorService);
        try{
            host.request(10,'a');
            host.request(20,'b');
            host.request(30,'c');
        }finally {
            scheduledExecutorService.shutdown();
        }
        System.out.println("main END");
    }
}