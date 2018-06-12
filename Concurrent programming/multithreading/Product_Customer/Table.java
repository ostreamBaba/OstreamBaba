package com.multithreading.Product_Customer;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @ Create by ostreamBaba on 18-6-12
 * @ ArrayBlockingQueueʵ�ֵ�Channel��ɫ
 */

//���������BlockingQueue(��������)
public class Table extends ArrayBlockingQueue<String>{
    public Table(int count) {
        super(count);
    }

    public void put(String cake)throws InterruptedException{
        System.out.println(Thread.currentThread().getName()+" puts "+cake);
        super.put(cake);
    }

    public String take()throws InterruptedException{
        String cake=super.take();
        System.out.println(Thread.currentThread().getName()+" takes "+cake);
        return cake;
    }
}
