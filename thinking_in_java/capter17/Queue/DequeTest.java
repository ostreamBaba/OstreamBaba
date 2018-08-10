package com.ThinkingInJava.capter17.Queue;

import java.util.*;

/**
 * @ Create by ostreamBaba on 18-8-10
 * @ ����
 */

//��Deque��Queue���ӽӿ�,����֪��Queue��һ�ֶ�����ʽ,��Deque����˫�����,
// ��֧�ִ������˵㷽������Ͳ���Ԫ��,���Deque�ȿ���֧��LIFO��ʽҲ����֧��LIFO��ʽ.
// Deque�ӿ���һ�ֱ�Stack��Vector��Ϊ�ḻ�ĳ���������ʽ,��Ϊ��ͬʱʵ������������.
//ͨ��ʵ����Ҫ������ʵ����ArrayDeque��LinkedList.
public class DequeTest {

    static void fillTest(Deque<Integer> deque){
        for (int i = 20; i < 27; i++) {
            deque.addFirst(i);
        }
        for (int i = 50; i < 55; i++) {
            deque.addLast(i);
        }
    }

    public static void main(String[] args) {
        Deque<Integer> di=new ArrayDeque<Integer>(50);
        fillTest(di);
        System.out.println(di);
        while (di.size()!=0){
            System.out.print(di.removeFirst()+" ");
        }
        System.out.println();
        fillTest(di);
        while (di.size()!=0){
            System.out.print(di.removeLast()+" ");
        }
    }
}
