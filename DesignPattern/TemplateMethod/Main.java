package com.design.TemplateMethod;

/**
 * @ Create by ostreamBaba on 18-6-2
 * @ ����
 */
public class Main {
    public static void main(String[] args) {
        AbstractDisplay display=new CharDisplay('1');
        //�����滻ԭ��
        display.display();
    }
}
