package com.multithreading;

import org.apache.http.annotation.ThreadSafe;

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
