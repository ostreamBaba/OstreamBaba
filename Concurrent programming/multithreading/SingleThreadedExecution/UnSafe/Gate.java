package com.multithreading.SingleThreadedExecution.UnSafe;

import org.apache.http.annotation.NotThreadSafe;

/**
 * @ Create by ostreamBaba on 18-6-10
 * @ ��ʹ��Single Threaded Executionģʽ(�������ݾ���)
 */
/*���� BROKEN�� pass�������ݾ�������check�ж���������*/
/*�����***BROKEN***No.1978520: B, A����: */
/*�����***BROKEN***No.1979912: B, B����:
   ����ΪtoStringִ��֮ǰ ���ݾ�������
* */

@NotThreadSafe
public class Gate {
    private int count=0;
    private String name="Nobody";
    private String address="NoWhere";

    public void pass(String name, String address) {
        this.name = name;
        this.address = address;
        ++count;
        check();
    }

    @Override
    public String toString() {
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
