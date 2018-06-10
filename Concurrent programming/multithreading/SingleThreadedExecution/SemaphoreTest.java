package com.multithreading.SingleThreadedExecution;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @ Create by ostreamBaba on 18-6-10
 * @ ��ʾ�����ź������� Semaphore
 */
class Log{
    public static void println(String s){
        System.out.println(Thread.currentThread().getName()+": "+s);
    }
}

//��Դ��������
class BoundedResource{
    private final Semaphore semaphore;
    private final int permits;
    private final static Random RANDOM=new Random(314159);

    public BoundedResource(int permits) {
        this.semaphore = new Semaphore(permits);
        this.permits = permits;
    }

    public void use() throws InterruptedException{ //ʹ��һ��������Դ
        semaphore.acquire(); //�ж��Ƿ���ڿ�����Դ ���̴߳�acquire()�̷߳���ʱ ��һ�����ڿ�����Դ
        //�������ڿ�����Դ �߳���������acquire������ ֱ�����ֿ�����Դ
        try {
            doUse();
        }finally {
            semaphore.release(); //�ͷ���Դ  Before/Afterģʽ
        }
    }

    protected void doUse() throws InterruptedException{
        Log.println("BEGIN: used= "+(permits-semaphore.availablePermits()));
        //permits-semaphore.availablePermits()��ʾ��ǰ�����õ���Դ��
        Thread.sleep(RANDOM.nextInt(5000));
        Log.println("END: used= "+(permits-semaphore.availablePermits()));
    }
}

class UserThread extends Thread{
    private final static Random RANDOM=new Random(26535);
    private final BoundedResource resource;

    public UserThread(BoundedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true){
            try {
                resource.use();
                TimeUnit.MILLISECONDS.sleep(RANDOM.nextInt(3000));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

public class SemaphoreTest {
    public static void main(String[] args) {
        //����������Դ
        BoundedResource resource=new BoundedResource(3);

        //ģ�������Դ����
        for (int i = 0; i < 10; i++) {
            new UserThread(resource).start();
        }
    }
}
