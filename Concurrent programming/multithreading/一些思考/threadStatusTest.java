package com.multithreading.doSomeTest;

/**
 * @ Create by ostreamBaba on 18-6-11
 * @ ���startһ���̻߳��׳�java.lang.IllegalThreadStateException(�߳�״̬�Ƿ��쳣)�쳣
 * @ It is never legal to start a thread more than once.��ε���start��������һ���߳��ǷǷ���
 */
public class threadStatusTest {
    public static void main(String[] args) {
        Thread thread = new TestThread();
        /*while (true){
            thread.start();
        }*/
        thread.start();
        thread.start();
        //����debug
        // if (this.threadStatus != 0) {
        //    throw new IllegalThreadStateException();
        // }
        // A zero status value corresponds to state "NEW". ˵��thread�ڶ���startʱ���״ֵ̬�Ѿ�����0��
        // �߳��������ڣ� �½�->����->����->����->����
    }
}


class TestThread extends Thread{
    @Override
    public void run() {
        System.out.println("BEING");
        for (int i = 0; i < 50; i++) {
            try{
                System.out.println(".");
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("END");
    }
}


