package com.multithreading.WorkerThread;

/**
 * @ Create by ostreamBaba on 18-6-13
 * @ Workerģʽ ����ThreadPoolģʽ ����ģʽ
 */
public class Main {
    public static void main(String[] args) {
        Channel channel=new Channel(5);
        channel.startWorkers();
        new ClientThread("Lil",channel).start();
        new ClientThread("Pump",channel).start();
        new ClientThread("Gucci",channel).start();
    }
}
