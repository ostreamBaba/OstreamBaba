package com.multithreading.GuardedSuspension;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ Create by ostreamBaba on 18-6-11
 * @ Guarded(���ػ���)Suspension(��ͣ)
 */
public class RequestQueue {
    private final Queue<Request> queue=new LinkedList<Request>();

    public synchronized Request getRequest() {
        //�ͻ����̻߳�ȡ���� ������Ϊ�յĻ� ��ô�ͽ����̼߳���ȴ�����
        //ʹ�ö���FIFO�Ƚ��ȳ���������
        while (queue.peek()==null){
            try {
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return queue.remove();
    }

    public synchronized void putRequest(Request request){
        queue.offer(request);
        /*this.notify();*/
        this.notifyAll();
        //��������put�����е�ʱ����condition���е������߳� ����sync������,��lock���о���
    }
}
