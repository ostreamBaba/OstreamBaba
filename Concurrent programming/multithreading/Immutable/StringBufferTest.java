package com.multithreading.Immutable;

/**
 * @ Create by ostreamBaba on 18-6-10
 * @ �ɱ����ʹ����ΪfinalҲ����immutable �㶮��
 */
public class StringBufferTest {
    private final StringBuffer info;

    public StringBufferTest(String address,String name) {
        this.info=new StringBuffer(address+","+name);
    }

    public StringBuffer getInfo() {
        return info;
    }

    public static void main(String[] args) {
        StringBufferTest test=new StringBufferTest("a","a");
        System.out.println(test.getInfo().append("fuck"));
        System.out.println(test.getInfo());
    }
}
