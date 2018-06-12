package com.multithreading.ReadWriteLock;

/**
 * @ Create by ostreamBaba on 18-6-12
 * @ ����
 */
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
    ReadWriteLock��Ҫ������
    ��ƽ�ԣ� ����ѡ�����Ļ�ȡ˳���Ƿ���Ϊ��ƽ�� ����ƽ ��ô�ȴ�ʱ��õ��߳̽����Ȼ�ȡ��
    �������� Reader��ɫ���߳̿��Ի�ȡ ����д����� Writer��ɫ���߳�Ҳ���Ի�ȡ ���ڶ�ȡ����
    ������ ����д����� ����Ϊ ���ڶ�ȡ����  �� ���ڶ�ȡ���� ��������Ϊ ����д�����
    ��ݷ����� �ṩ�˻�ȡ�ȴ��е��̵߳ĸ����ķ���getQueueLength�Լ�����Ƿ��ȡ������д������ķ���isWriteLocked...

 */

public class ReadWriteLockTest {
}

class Data1{
    private final char[] buffer;
    private final ReadWriteLock lock=new ReentrantReadWriteLock(true); //fair
    private final Lock readLock=lock.readLock();
    private final Lock writeLock=lock.writeLock();

    public Data1(int size) {
        this.buffer=new char[size];
        for (int i = 0; i < size; i++) {
            buffer[i]='*';
        }
    }

/*
    before();//��before�����쳣 �����try-finally��Ͳ��ᷢ��
    try{
       execute();
    }finally{
        after();
    }
*/

    public char[] read() throws InterruptedException{
        readLock.lock();
        try {
            return doRead();
        }finally {
            readLock.unlock();
        }
        /*  ��д�ɣ�
            try{
               readLock.lock();
               return doRead();
            }finally{
                readLock.unlock();
            }
            ��������������� ��������interruptʱ ����Ϳ��ܴ��ڹ������readUnlock��writeLock��Σ��
            �����߳�����lock.readLock()�н���wait ��ô���̱߳�interruptʱ����׳�InterruptException�쳣
            �˳�readLock���� ��ʱreadingReaders�ֶβ���������
            ��readLock�˳����̻߳�����finally���� ִ��readLock.unlock()
            �ڵ��� ֮ǰδ������readingReaders�ֶλ�ִ�еݼ����� ��ôreadingReaders�ֶε�ֵ���������ҪС

        */
    }

    public void write(char c) throws InterruptedException{
        writeLock.lock();
        try{
            doWrite(c);
        }finally {
            writeLock.unlock();
        }
    }

    private char[] doRead() {
        char[] newBuffer= Arrays.copyOf(buffer,buffer.length);//��������
        slowly();
        return newBuffer;
    }

    private void doWrite(char c) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i]=c;
            slowly();
        }
    }

    private void slowly(){
        try{
            Thread.sleep(50);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
