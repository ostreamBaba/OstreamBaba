package com.multithreading.GuardedSuspension;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ Create by ostreamBaba on 18-6-11
 * @ Guarded(���ػ���)Suspension(��ͣ)--> ����������synchronized
 * @ GuardedObject ���ػ��Ķ���
 */
public class RequestQueue {
    private final Queue<Request> queue=new LinkedList<Request>();//LinkedList�Ƿ��̰߳�ȫ��

    public synchronized Request getRequest() {
        //�ͻ����̻߳�ȡ���� ������Ϊ�յĻ� ��ô�ͽ����̼߳���ȴ�����
        //ʹ�ö���FIFO�Ƚ��ȳ���������
        while (queue.peek()==null){  //�ػ�����
            try {
                this.wait(); //�߳��ڵȴ�ʲô�� �ȴ�queueʵ������״̬�����仯
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return queue.remove();
    }

    public synchronized void putRequest(Request request){
        queue.offer(request);  //queueʵ������״̬�����仯
        /*this.notify();*/
        this.notifyAll();
        //��������put�����е�ʱ����condition���е������߳� ����sync������,��lock���о���
    }
}

/*
 java.lang.IllegalMonitorStateException �쳣
 ��ͬ�����Ʒ�����ͬ�����ƿ������wait()��notify()��notifyAll()��
 ����ڷ�ͬ�����Ʒ����������Щ������������ͨ�����룬
 �����е�ʱ�򣬽��õ�IllegalMonitorStateException�쳣��
 ��������һЩ��������Ϣ������"��ǰ�̲߳���ӵ����"����Ϣ����˼�ǣ�
 ����wait()��notify()��notifyAll()���߳��ڵ�����Щ����ǰ����"ӵ��"���������
 ��ǰ���̲߳��Ǵ˶������������ߣ�ȴ���øö����notify()��
 notify()��wait()����ʱ�׳����쳣��
 */