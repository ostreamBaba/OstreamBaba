package com.ThinkingInJava.capter17.Queue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @ Create by ostreamBaba on 18-8-10
 * @ ����
 */
public class PriorityQueueTest {

    private static final Random RANDOM=new Random();

    public static void main(String[] args) {
        PriorityQueue<Integer> queue=new PriorityQueue<Integer>(7, maxQueue); //��С���ȶ���
        for (int i = 0; i < 7; i++) {
            queue.add(RANDOM.nextInt(100));
        }
        for (int i = 0; i < 7; i++) {
            System.out.println(queue.poll());
            System.out.println(queue);
        }
    }

    //������ȶ���
    private static Comparator<Integer> maxQueue=new Comparator<Integer>() {
        @Override
        public int compare(Integer integer, Integer t1) {
            return t1-integer;
        }
    };

}
