package com.multithreading.GuardedTimed;

import java.util.concurrent.TimeoutException;

/**
 * @ Create by ostreamBaba on 18-6-11
 * @ Guarded Timed��ʵ��(ʹ��wait) wait����0 ��ʾû�г�ʱʱ�� ����ʱʱ�����޳� ���븺�����׳��쳣
 */
public class Host {

    private final long timeout;
    private boolean ready=false;

    public Host(long timeout) {
        this.timeout = timeout;
    }

    public synchronized void setExecutable(boolean on){
        ready=on;
        notifyAll();
    }

    //�ж�wait�Ƿ�ʱ�ķ���
    public synchronized void execute()throws InterruptedException,TimeoutException{
        long start=System.currentTimeMillis(); //��ʼʱ��
        while (!ready){
            long now=System.currentTimeMillis(); //��ǰʱ��
            long rest=timeout-(now-start); //ʣ��ȴ�ʱ��
            if(rest<=0){
                throw new TimeoutException("now-start="+(now-start)+",timeout="+timeout);
            }
            wait(rest);
        }
        doExecute();
    }

    private void doExecute() {
        System.out.println(Thread.currentThread().getName()+" calls doExecute");
    }
}
