package com.ThinkingInJava.util.���л�;

import java.io.ObjectStreamClass;
import java.io.Serializable;

/**
 * @ Create by ostreamBaba on 18-8-9
 * @ ����
 */

//�ܶ�ʱ���ඨ��ĸı����ڱ��У����ֲ�ϣ���������л��Ĳ������ԡ����ǾͿ���ͨ����������ʾ�Ķ���serialVersionUID��
// ������һ����ȷ��longֵ���ɡ��������ӹ�JVM��Ĭ�ϼ����Լ�顣����������������ĸı�ᵼ�·����л���
// �ı��������ֻ�ܵõ�Ĭ�ϵ�null����0����falseֵ��

public class Student implements Serializable{
    //private static final long serialVersionUID = 2169561812859380129L;
    private String name;//�޸�ǰ
    //private String name1;�޸ĺ�

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        long sUID= ObjectStreamClass.lookup(Class.forName("com.ThinkingInJava.util.���л�.Student")).getSerialVersionUID();
        System.out.println(sUID);
    }
}
