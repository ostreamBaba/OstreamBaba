package com.multithreading.GuardedSuspension;

/**
 * @ Create by ostreamBaba on 18-6-11
 * @ ģ��ͻ��������˷�������
 */
public class Request {
    private final String name;
    public Request(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return "["+name+"]";
    }
}
