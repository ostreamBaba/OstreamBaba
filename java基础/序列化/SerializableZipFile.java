package com.ThinkingInJava.util.���л�;

import java.io.*;
import java.util.zip.ZipFile;

/**
 * @ Create by ostreamBaba on 18-8-9
 * @ ����ͨ���������������ʹ��ԭ���������л�����Ĳ���������������л�
 */
public class SerializableZipFile implements Serializable{
    private static final long serialVersionUID = -3247994804080736475L;
    public ZipFile zipFile;

    public SerializableZipFile(String filename) throws IOException {
        zipFile=new ZipFile(filename);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(zipFile.getName());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        String filename= (String) in.readObject();
        zipFile=new ZipFile(filename);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file=new File("aaa");
        ObjectOutputStream oout=new ObjectOutputStream(new FileOutputStream(file));
        oout.writeObject(new SerializableZipFile("/home/ios666/Desktop/1.zip"));
        oout.close();
        System.out.println("���л��ɹ�");
        //�����л�
        ObjectInputStream oin=new ObjectInputStream(new FileInputStream(file));
        Object o=oin.readObject();
        oin.close();
        System.out.println("�����л��ɹ�:"+((SerializableZipFile) o).zipFile.getName());
    }


}
