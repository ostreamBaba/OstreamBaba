package com.multithreading.ThreadPerMessage;

/**
 * @ Create by ostreamBaba on 18-6-12
 * @ Thread-Per-Message ÿ����Ϣһ���߳�(Ϊÿ����������������һ���µ��߳�) ��Ϣ��ί�ж˺�ִ�ж��ǲ�ͬ���߳�
 */

/*
    Thread-Per-Messageģʽ

    �����Ӧ�� �����ӳ�ʱ��(��ģʽ�ܹ������Client��ɫ��Ӧ��Host��ɫ����Ӧ�� �����ӳ�ʱ��--������handle�����ǳ���ʱ�� ����handle������Ҫ�ȴ�����/���ʱ)
    ȱ�㣺 �����̻߳Ứ��ʱ�� ��Ҫ�����Ӧ��ʱ ����ʹ�ø�ģʽȡ����handle�����ѵ�ʱ����߳���������ʱ��ľ���

    �ʺϲ���˳��û��Ҫ��ʱ(��ģʽ�� handle������û�а���request�������õ�˳����ִ��)

    �����ڲ���Ҫ����ֵ

    Ӧ���ڷ����� �����������߳�ȥ���ܿͻ��˵����� ����Щ�����ʵ�ʴ����ǽ��������߳���ִ�� ������������߳��򷵻� ȥ�ȴ��ͻ��˵���������

    ���÷���+�����߳�-->������Ϣ

 */

public class Main {
    public static void main(String[] args) {
        //Client ί����
        System.out.println("main BEING");
        Host host=new Host();
        host.request(10,'A');
        host.request(20,'B');
        host.request(30,'C');
        System.out.println("main END");
    }
}
