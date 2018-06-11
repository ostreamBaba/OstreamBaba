package com.multithreading.GuardedSuspension;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @ Create by ostreamBaba on 18-6-11
 * @ ����
 */
public class RequestQueue1 {
    private final BlockingQueue<Request> queue=new LinkedBlockingDeque<Request>();//�̰߳�ȫ�Ķ���
    public Request getRequest(){
        Request request=null;
        try{
            request=queue.take();
            //��ȡ���Ƴ��˶��е�ͷ�������û��Ԫ����ȴ�����������
            //ֱ����Ԫ�ؽ����ѵȴ��߳�ִ�иò���
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return request;
    }

    public void putRequest(Request request){
        try {
            queue.put(request);
            //��ָ����Ԫ�ز���˶��е�β����
            //����ö�����������һֱ�ȴ���������
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    //take����ȡ������Ԫ�� put���������ĩβ���Ԫ��
    //take put �Ѿ����ǻ��� ���Բ���Ҫ����
}
