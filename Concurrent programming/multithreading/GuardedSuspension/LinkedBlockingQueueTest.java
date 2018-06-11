package com.multithreading.GuardedSuspension;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @ Create by ostreamBaba on 18-6-11
 * @ ����
 */
public class LinkedBlockingQueueTest {

    private final BlockingQueue<Request> queue=new LinkedBlockingDeque<Request>();//�̰߳�ȫ�Ķ���
    public Request getRequest(){
        Request request=null;
        try{
            request=queue.take();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return request;
    }

    public void putRequest(Request request){
        try {
            queue.put(request);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
