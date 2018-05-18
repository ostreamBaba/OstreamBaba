package com.viscu.spring.taskexecutor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ Create by ostreamBaba on 18-5-19
 * @ ����
 */

@Configuration
@ComponentScan("com.viscu.spring.taskexecutor")
@EnableAsync
public class TaskExecutorConfig implements AsyncConfigurer{
    //���һ�������̳߳ص�taskexecutor
    public Executor getAsyncExecutor() {
        //���̳߳ؽ�������
        ThreadPoolTaskExecutor taskExecutor=new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);//�����߳���
        taskExecutor.setMaxPoolSize(10);//����߳���
        taskExecutor.setQueueCapacity(25);//����������еĳ���
        taskExecutor.initialize();
        return taskExecutor;
    }
}
//����ִ����
@Service
class AsyncTaskService{
    @Async //�첽����
    public void executeAsyncTask(Integer i){
        System.out.println("ִ���첽����" +i);
    }
    @Async
    public void executeAsyncTaskPlus(Integer i){
        System.out.println("ִ���첽����+1��" +(i+1));
    }
}
class Main{
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
        AsyncTaskService asyncTaskService=applicationContext.getBean(AsyncTaskService.class);
        for (int i = 0; i < 10; i++) {
            asyncTaskService.executeAsyncTask(i);
            asyncTaskService.executeAsyncTaskPlus(i);
        }
    }
}
/*
corePoolSize���̳߳�ά���̵߳���������
keepAliveSeconds������Ŀ���ʱ��
maxPoolSize���̳߳�ά���̵߳��������
queueCapacity���������
rejectedExecutionHandler���Ծܾ�task�Ĵ������
�����ʱ�̳߳��е�����С��corePoolSize����ʹ�̳߳��е��̶߳����ڿ���״̬��ҲҪ�����µ��߳���������ӵ�����
�����ʱ�̳߳��е���������corePoolSize�����ǻ������workQueueδ������ô���񱻷��뻺����С�
�����ʱ�̳߳��е���������corePoolSize���������workQueue���������̳߳��е�����С��maxPoolSize�����µ��߳���������ӵ�����
�����ʱ�̳߳��е���������corePoolSize���������workQueue���������̳߳��е���������maxPoolSize����ôͨ��handler��ָ���Ĳ��������������
Ҳ���ǣ�������������ȼ�Ϊ�������߳�corePoolSize���������workQueue������߳�maximumPoolSize��������߶����ˣ�ʹ��handler�����ܾ�������
���̳߳��е��߳���������corePoolSizeʱ�����ĳ�߳̿���ʱ�䳬��keepAliveTime���߳̽�����ֹ���������̳߳ؿ��Զ�̬�ĵ������е��߳�����
*/
