package com.multithreading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * @ Create by ostreamBaba on 18-6-10
 * @ ���� ArrayList�����̰߳�ȫ
 */
public class ArrayListTest {
    public static void main(String[] args) {
        //List<Integer> list=new ArrayList<Integer>();
        //ʹ��Collections.synchronizedListͬ��ArrayListʵ��
        //final List<Integer> list= Collections.synchronizedList(new ArrayList<Integer>());
        final List<Integer> list=new CopyOnWriteArrayList<Integer>();
        //дʱ���� ���Լ���ִ��д�Ĳ���ʱ �ڲ��Ѿ�ȷ����ȫ������ͻᱻ���帴�� ����֮�� ��������ʹ�õ������Դ˶�ȡԪ�ص�ʱ����Ԫ�ػᱻ�޸���
        new WriteThread(list).start();
        new ReadThread(list).start();
    }
}


class WriteThread extends Thread{

    private final List<Integer> list;

    public WriteThread(List<Integer> list) {
        super("writeThread");
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; true; i++) {
            list.add(i);
            list.remove(0);
        }
    }
}

class ReadThread extends Thread{

    private final List<Integer> list;

    public ReadThread(List<Integer> list) {
        super("ReadThread");
        this.list = list;
    }

    @Override
    public void run() {
        while (true){
            /*for (int n:list){   //ʹ��ArrayLIst�����쳣
                System.out.println(n);
            }*/
            /*synchronized (list){ //ʹ��Collections.synchronizedList��Ҫ�Ե���������
                for(int n:list){ //������ʽ��ʹ���˵����� ������������쳣(�����ڶ��Ĺ����б��޸�)
                    System.out.println(n);
                }
            }*/
            for (int n:list){   //ʹ��CopyOnWriteArrayList
                System.out.println(n);
            }
        }
    }
}



/*
class Test {
    public static void main(String[] args)  {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            Integer integer = iterator.next();
            if(integer==2)
                */
/*list.remove(integer);*//*

                iterator.remove();
        }
    }
}*/
