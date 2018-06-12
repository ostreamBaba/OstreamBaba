package com.multithreading.Product_Customer.exchange;

import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * @ Create by ostreamBaba on 18-6-12
 * @ ����
 */
public class CustomerThread extends Thread{

    private final Random random;
    private final Exchanger<char[]> exchanger;
    private char[] buffer;

    public CustomerThread(String name, Exchanger<char[]> exchanger, char[] buffer, long seed) {
        super( name );
        this.random = new Random(seed);
        this.exchanger = exchanger;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true){
            try{
                //����������
                System.out.println(Thread.currentThread().getName()+": EXCHANGE BEING");
                buffer=exchanger.exchange(buffer);
                System.out.println(Thread.currentThread().getName()+": EXCHANGE END");

                //ȡ���ַ�
                for (int i = 0; i < buffer.length; i++) {
                    System.out.println(Thread.currentThread().getName()+": --> "+buffer[i]);
                    Thread.sleep(random.nextInt(1000));
                }

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    //ʹ��exchange�������յĻ��������ݸ�ProducerThread
    //���ݿյĻ�����֮�� ��Ϊ���� ���������Ļ�����
    //��ʾ�������е��ַ�

}
/*
CustomerThread: EXCHANGE BEING
        ProducerThread: A -->  Producer ��Buffer1����ַ���
        ProducerThread: B -->
        ProducerThread: C -->
        ProducerThread: D -->
        ProducerThread: E -->
        ProducerThread: F -->
        ProducerThread: G -->
        ProducerThread: H -->
        ProducerThread: I -->
        ProducerThread: J -->
        ProducerThread: EXCHANGE BEING  ���н���
        ProducerThread: EXCHANGE END
        CustomerThread: EXCHANGE END
        CustomerThread: --> A  CustomerThread�������A->J
        ProducerThread: K -->  //�������K��T
        ProducerThread: L -->
        CustomerThread: --> L
        CustomerThread: --> C
        ProducerThread: M -->
        CustomerThread: --> D
        ProducerThread: N -->
        CustomerThread: --> E
*/
