package com.multithreading.Immutable;

/**
 * @ Create by ostreamBaba on 18-6-10
 * @ ����
 */
public class Main {
    public static void main(String[] args) {
        Person person=new Person("A","B");
        new PersonThread(person).start();
        new PersonThread(person).start();
        new PersonThread(person).start();
    }
}
