package com.design.adapter.chapter2;

/**
 * @ Create by ostreamBaba on 18-6-2
 * @ ����������--����������
 */
public class PrintBanner extends Print{
    private Banner banner;//ί�й�ϵ ��PrintBanner���printWeak�����õ�ʱ�򣬲�����PrintBanner���Լ�����,
    //���ǽ�����������ʵ��(Bannerʵ��)��showWithParen()��������
    public PrintBanner(String string) {
        banner=new Banner(string);
    }
    @Override
    public void printWeak() {
        banner.showWithParen();
    }
    @Override
    public void printStrong() {
        banner.showWithAster();
    }
}
