package com.ThinkingInJava.util.���л�;

import java.io.*;

/**
 * @ Create by ostreamBaba on 18-8-9
 * @ ����
 */

//�����л�����ɹ����л�֮���ǲ���һ�����Է����л��أ�

//������������л�
class Person{
    private String name;
    public Person(String name) {
        this.name = name;
    }

    public Person() { } //�Ӹ��޲ι�����

    public String getName() {
        return name;
    }
}
//�������������л�
//�����ǰ������г�������һ���༴�������л���Ҳû���޲ι���������ô��ǰ�ཫ���ܷ����л���
//������޲ι���������ô�˳��෴���л��������򽫻���null����0��false�ȵȡ�
public class Boy extends Person implements Serializable {
    private static final long serialVersionUID = -4005201724915326804L;
    private int id;
    public Boy(String name,int id) {
        super(name);
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //���л�
        File file=new File("test2.txt");
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(new Boy("lil",18));
        out.flush();
        out.close();

        //�����л�
        ObjectInputStream in=new ObjectInputStream(new FileInputStream(file));
        Boy boy= (Boy) in.readObject();
        in.close();
        System.out.println(boy.getId());
    }
}
