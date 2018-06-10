package com.multithreading.SingleThreadedExecution.safe;

import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.annotation.ThreadSafe;

/**
 * @ Create by ostreamBaba on 18-6-10
 * @ ʹ��Single Threaded Executionģʽ(���������ݾ���)
 */

@ThreadSafe
public class Gate {
    private int count=0;
    private String name="Nobody";
    private String address="NoWhere";

    //������name address count�����ֶ� ȷ���������ֶβ�������̷߳���
    public synchronized void pass(String name, String address) {
        this.name = name;
        this.address = address;
        ++count;
        check(); //check����û�м��� ����Ϊcheck����Ϊ˽�� �����ⲿ�޷����ʸ÷���
    }

    //����
    @Override
    public synchronized String toString() {
        return "No."+count+": "+name+", "+address;
    }

    private void check() {
        if(name.charAt(0)!=address.charAt(0)){
            System.out.println("***BROKEN***"+toString());
        }
    }

    public static void main(String[] args) {
        Gate gate=new Gate();
        gate.check();
    }
}
