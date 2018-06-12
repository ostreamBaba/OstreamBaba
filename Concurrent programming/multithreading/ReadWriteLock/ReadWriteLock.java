package com.multithreading.ReadWriteLock;

/**
 * @ Create by ostreamBaba on 18-6-12
 * @ ����
 */

// ��ȫ�ԣ� ���ֳ�ͻ
// ��ȡ �� д�� �ĳ�ͻ
// д�� �� д�� �ĳ�ͻ(���������ڳ�ͻ)

/*
   ���̻߳�ȡ��������ʱ�� ���߳�ִ��д���� ��ȴ�(��Ȼ����read-write conflict);���߳�ִ�ж����� ����ȴ�
   ���̻߳�ȡ��������ʱ�� �����߳�����ʲô���� ���ȴ�

 */


/*
    �ŵ㣺���� ��ȡ���� ���߳�֮�䲻���ͻ����������߳�������
    �ʺ϶�ȡƵ�ʱȽϷ���ʱ(�ķ�ʱ��)
    �ʺ϶�ȡƵ�ʱ�д��Ƶ�ʸ�ʱ(��д��Ƶ�ʱȽϸߵĻ� writer��ɫ��Ƶ��ֹͣ��reader��ɫ�Ĵ��� ���޷�����Read-Write Lock���ŵ���)
 */

public final class ReadWriteLock {
    private int readingReaders=0; //ʵ�����ڶ�ȡ�е��̸߳���
    private int waitingWriters=0; //���ڵȴ�д����̸߳���
    private int writingWriters=0; //ʵ������д���е��̸߳���

    private boolean preferWriter=true;//��д������ ��Ϊtrue

    /*����preferWriter�ֶε�ԭ��
        ����̶߳�ȡ����ͻ
        ��ʱ���߳̽��ж�ȡ����ʱ ��ôд�߳̽�������
        �����߳��ͷ��� ����(����д)�߳̾����� ������̻߳���� ��ô�ͽ������� ���д�̻߳���� ��ô����д�����(�������γ��˽���������� ���Ƚ���д����)
    */

    //synchronized�������ڻ�ȡʵ������(ÿ��ʵ����ӵ��һ���� ����ͬһ�������������������ϵ��߳�ͬʱ��ȡ --> ������)
    //ReadWriteLock��ʵ���� ���ڶ�ȡ���� �� ����д����� �����߼���(������Ա�Լ�ʵ�ֵ�) ��ʵ���������߼�����������ֻ��һ�� �Ǿ���ReadWriteLockʵ������


    public synchronized void readLock() throws InterruptedException{  //ĳ�̻߳�ö�����
        while (writingWriters>0||(preferWriter&&waitingWriters>0)){  //�����߳�����д��� ��ȴ�
            wait();
        }
        ++readingReaders; //ʵ�����ڶ�ȡ���̼߳�1    //��û���߳���д�� ����Զ�
    }

    public synchronized void readUnlock(){  //ĳ�߳��ͷ���
        --readingReaders;   //���ڶ�ȡ���̵߳�����-1
        preferWriter=true;  //read����������ִ��write����
        notifyAll(); //���������ڵȴ���д �߳�
    }

    public synchronized void writeLock() throws InterruptedException{ //ĳ�̻߳��д����
        ++waitingWriters; //���ڵȴ�д����̼߳�1
        try{
            while (readingReaders>0||writingWriters>0){  //�����߳����ڶ�ȡ����д�� ��ô��д�߳� �ȴ�
                wait();
            }
        }finally {  //��û���߳����ڶ�ȡ����д�� ��ôִ�и�д�߳�
            --waitingWriters; //���ڵȴ�д����߳��̸߳�����1
        }
        ++writingWriters; //ʵ������д����̸߳�����1
    }

    public synchronized void writeUnlock(){  //ĳ�߳��ͷ���
        --writingWriters; //ʵ������д����̼߳�1
        preferWriter=false;
        notifyAll(); //�������е�д�߳� �� ���߳�
    }
}
