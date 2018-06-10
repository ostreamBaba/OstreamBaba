package com.multithreading;

import org.apache.http.annotation.ThreadSafe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ Create by ostreamBaba on 18-6-9
 * @ ����
 */


@ThreadSafe
public class synchronizedTest {

    public synchronized void method(){
        //todo
    }

    public void method1(){
        synchronized (this){
            //todo
        }
    }

    //ʹ��this������ִ���̵߳Ļ������
}

//synchronized��̬����ÿ��ֻ����һ���߳�������
@ThreadSafe
class someThing{
    static synchronized void method(){
        //todo
    }

    void method1(){
        synchronized (someThing.class){
            //todo
        }
    }

}


class SynchronizedAndLock{

    private ReentrantLock lock=new ReentrantLock();
    //����return��������ᱻ�ͷ�
    void method(){
        lock.lock();
        /*if(�������ʽ){
           return;
        }*/
        lock.unlock();
    }

    void method2(){
        lock.lock();
        doMethod(); //�׳��쳣�����ͷ���
        lock.unlock();
    }

    private void doMethod() {
        throw new RuntimeException();
    }

    //ʹ��finally ������ζ���ִ��unlock����
    void method3(){
        lock.lock();
        try {
            //todo
        }finally {
            lock.unlock();
        }
        //finally�����÷�����Before/Afterģʽ(��ǰ/�º�ģʽ)
    }

    //synchronized��������return�����׳��쳣һ�����ͷ���
}
