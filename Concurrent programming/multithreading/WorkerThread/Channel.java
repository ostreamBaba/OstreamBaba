package com.multithreading.WorkerThread;

/**
 * @ Create by ostreamBaba on 18-6-13
 * @ ���𴫵ݹ����������Լ����湤���̵߳���
 */
public class Channel {

    private static final int MAX_REQUEST=100;
    private final Request[] requestQueue;
    private int tail; //�´�putRequest��λ��
    private int head; //�´�takeRequest��λ��
    private int count; //Request������

    private final WorkerThread[] threadPool;

    public Channel(int threads) {
        this.requestQueue=new Request[MAX_REQUEST];
        tail=0;
        head=0;
        count=0;
        threadPool=new WorkerThread[threads];
        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i]=new WorkerThread("Worker-"+i,this);
        }
    }
    public void startWorkers(){
        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i].start();
        }
    }

    public synchronized void putRequest(Request request){
        while (count>=requestQueue.length){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        requestQueue[tail]=request;
        tail=(tail+1)%MAX_REQUEST;
        ++count;
        notifyAll();
    }

    public synchronized Request takeRequest(){
        while (count<=0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Request request=requestQueue[head];
        head=(head+1)%MAX_REQUEST;
        --count;
        notifyAll();
        return request;
    }



}
