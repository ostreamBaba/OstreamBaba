package com.viscu.something;

/**
 * @ Create by ostreamBaba on 18-6-10
 * @ String����immutable��
 */
public class WhyStringIsImmutable {
    public static void main(String[] args) {
        String string="Bat";
        System.out.println(string.replace('B','C'));//��ȻString��replace��
        //��������ı䲻��String�ײ��char[]����(�ײ�������finalʵ��)
        System.out.println(string);
        //���´�ӡ��������Bat
    }
}
