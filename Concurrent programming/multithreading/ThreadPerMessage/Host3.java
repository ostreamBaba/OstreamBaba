package com.multithreading.ThreadPerMessage;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ Create by ostreamBaba on 18-6-13
 * @ ����
 */
public class Host3 {
    private final Helper helper=new Helper();
    private final ScheduledExecutorService scheduledExecutorService;

    public Host3(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = scheduledExecutorService;
    }

    //��������
    //ScheduledExecutorService�����Ƴٲ�����ִ��
    public void request(final int count, final char c){
        System.out.println(" request("+count+", "+c+") BEING");
        scheduledExecutorService.schedule(
                new Runnable() {
                    @Override
                    public void run() {
                        helper.handle(count,c);
                    }
                }, 3L, TimeUnit.SECONDS
        ); //request���󵽴�Լ3�����ִ��
        System.out.println(" request("+count+", "+c+") END");
    }
}
