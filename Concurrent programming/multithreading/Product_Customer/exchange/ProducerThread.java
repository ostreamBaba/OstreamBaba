package com.multithreading.Product_Customer.exchange;

import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * @ Create by ostreamBaba on 18-6-12
 * @ ����
 */
public class ProducerThread extends Thread{
    private final Exchanger<char[]> exchanger;
    private final Random random;
    private char[] buffer=null;
    private int index=0;

    public ProducerThread(String name, Exchanger<char[]> exchanger, char[] buffer,long seed) {
        super( name );
        this.buffer = buffer;
        this.exchanger = exchanger;
        this.random = new Random(seed);
    }

    //�򻺴�������ַ�
    @Override
    public void run() {
        while (true){
            try{
                for (int i = 0; i < buffer.length; i++) {
                    buffer[i]=nextChar();
                    System.out.println(Thread.currentThread().getName()+": "+buffer[i]+" -->");
                }

                //�����ַ�
                System.out.println(Thread.currentThread().getName()+": EXCHANGE BEING");
                exchanger.exchange(buffer);
                System.out.println(Thread.currentThread().getName()+": EXCHANGE END");

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    //����ַ� ֱ��������������
    //ʹ��exchange�����������Ļ��������ݸ�CustomerThread
    //���ݻ�����֮�� ��Ϊ���� ���ܿյĻ�����


    //�����ַ�
    private char nextChar()throws InterruptedException{
        char c=(char) ('A'+index%26);
        index++;
        Thread.sleep(random.nextInt(1000));
        return c;
    }
}
