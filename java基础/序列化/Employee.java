package com.ThinkingInJava.util.���л�;

import java.io.*;
import java.util.zip.ZipFile;

/**
 * @ Create by ostreamBaba on 18-8-8
 * @ �����˲������л��Ķ�����Ķ���Ҳ�ǲ������л��ġ�
 */
public class Employee implements Serializable {

    private static final long serialVersionUID = -858657284703348618L;

    private ZipFile zf=null; //�������л�

    public Employee(ZipFile zf) {
        this.zf = zf;
    }

    public static void main(String[] args) throws IOException {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(new File("test1.txt")));
        out.writeObject(new Employee(new ZipFile("/home/ios666/Desktop/1.zip")));
    }
}
