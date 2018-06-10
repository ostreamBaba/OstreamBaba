package com.multithreading.Immutable;

/**
 * @ Create by ostreamBaba on 18-6-10
 * @ ����ʵ���� ״̬�޷��ı�
 */
public final class Person {
    private final String name;
    private final String address;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "[Person:name="+name+", address="+address+"]";
    }
}



class test{
    public static void main(String[] args) {
        String str="123";
        StringBuffer sb1=new StringBuffer(str);
        String str2=new String(sb1);
        System.out.println(str2);
        //String��StringBuffer�ǳɶԵ� �ҿ����໥ת��(StringBuffer�˽�)
    }
}


//�ɶԵ�mutable���immutable�� -- StringBuffer��String
//������õ�immutableģʽ���� String BigInteger(BigDecimal)
//������ʽģʽ��Pattern ��װ����(װ������) java.awt.Color(java.awt.Point����immutableģʽ)
