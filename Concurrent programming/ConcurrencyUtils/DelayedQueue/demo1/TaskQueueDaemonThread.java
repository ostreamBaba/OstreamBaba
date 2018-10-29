package com.viscu.concurrency.DelayedQueue.demo1;

import org.junit.Test;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ Create by ostreamBaba on 18-10-26
 * @ ����
 */

public class TaskQueueDaemonThread {

    @Test
    public void testTimeUnit(){
        //Test ��Ҫ���е�Ĭ�Ϲ�����
        System.out.println(TimeUnit.SECONDS.convert(3600,TimeUnit.MINUTES));
    }

    public TaskQueueDaemonThread(){}

    private static class Holder{
        private final static TaskQueueDaemonThread INSTANCE=new TaskQueueDaemonThread();
    }

    public static TaskQueueDaemonThread getInstance(){
        return Holder.INSTANCE;
    }

    private Executor executor= Executors.newFixedThreadPool(10);

    private Thread daemonThread;//�ػ��߳�

    //�����ػ��߳���ִ�и��ӳٶ���
    public void init(){
        daemonThread=new Thread(this::execute);
        daemonThread.setDaemon(true);
        daemonThread.setName("Task Queue Daemon Thread");
        daemonThread.start();
    }

    private void execute() {
        System.out.println("start: "+System.nanoTime());
        while (true){
            try {
                //���ӳٶ�����ȡֵ,���û�ж�����������һֱ�ȴ�
                Task task=t.take();
                /*System.out.println(t.size());*/
                if(task!=null){
                    Runnable t1=task.getTask();
                    if(t1!=null){
                        executor.execute(t1);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    private final DelayQueue<Task<Runnable>> t=new DelayQueue<>();

    public void put(long time,Runnable task){
        //msת����ns
        long nanoTime=TimeUnit.NANOSECONDS.convert(time, TimeUnit.MILLISECONDS);
        //����һ������
        Task<Runnable> tt=new Task<>(nanoTime,task);
        //������ʱ����
        t.put(tt);
    }

    public boolean endTask(Task<Runnable> task){
        //��������
        return t.remove(task);
    }

    public static void main(String[] args) throws InterruptedException {
        TaskQueueDaemonThread task=TaskQueueDaemonThread.getInstance();
        for (int i = 0; i < 10; i++) {
            Runnable t=()->{
                System.out.println(Thread.currentThread().getName()+" ִ����");
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" ִ�����");
            };
            task.put((i+1)*2000,t);
        }
        task.init();
        for(;;);
    }

    static class Main{

        public static void main(String[] args) {
            long st=System.nanoTime();
            System.out.println(st);
            System.out.println(st+TimeUnit.NANOSECONDS.convert(1000,TimeUnit.MILLISECONDS));
        }
    }

}
