package com.design.adapter.chapter2;

/**
 * @ Create by ostreamBaba on 18-6-2
 * @ ί��
 */
public class Main {
    public static void main(String[] args) {
        //����Main�����,Banner������������������ȫ��������
        //�����ƱʼǱ�����ֻҪ��ֱ��12���ĵ�ѹ�¾�������������
        //����������֪����12���ĵ�ѹ������������100���ؽ�����ѹת��������
        Print p=new PrintBanner("viscu");
        p.printStrong();
        p.printWeak();
    }
}
