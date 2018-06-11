package com.multithreading.Product_Customer;

/**
 * @ Create by ostreamBaba on 18-6-12
 * @ Channel ͨ�� λ��producer��customer֮�� �е����ڴ���Data(Cake producer���� customerʹ��)��ɫ����תվ ͨ��������
 * @ ��--��
 */


//�ػ���ȫ��Channel��ɫ

//�����м��ɫ�����壺�̵߳�Э��������Ҫ���ǡ������м�Ķ����� �̵߳Ļ��⴦����Ҫ���ǡ�Ӧ�ñ����Ķ�����

public class table {
    private final String[] buffer;
    private int tail;//��һ�η��õ����λ��
    private int head;//��һ��ȡ�������λ��
    private int count;

    public table(int count) {
        this.buffer = new String[count];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
    }

    //���õ���
    //throws InterruptedException���㺬�壺 �ǻ���ʱ��ķ��� �ǿ���ȡ���ķ���
    public synchronized void put(String cake)throws InterruptedException{
        System.out.println(Thread.currentThread().getName()+" puts "+cake);
        while (count>=buffer.length){  //��� ���õ������������ ��ômake������̼߳���condition����
            wait();
        }
        buffer[tail]=cake;
        tail=(tail+1)%buffer.length;
        ++count;
        notifyAll();  //���� condition�����е������߳�(����put cake��take cake ���Լ������õ����ȡ����)
    }
    //ȡ������
    public synchronized String take()throws InterruptedException{
        while (count<=0){  //�������ϵĵ���ȡ���� �Ѹ��̼߳���condition����
            wait();
        }
        String cake=buffer[head];
        head=(head+1)%buffer.length;
        --count;
        System.out.println(Thread.currentThread().getName()+" takes "+cake);
        notifyAll();
        return cake;
    }
}
