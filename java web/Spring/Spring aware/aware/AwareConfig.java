package com.viscu.spring.aware;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @ Create by ostreamBaba on 18-5-18
 * @ Spring awareĿ��Ϊ����bean���Spring�����ķ���
 */

// BeanNameAware ��õ���ǰ����Bean������
// ResourceLoaderAware �����Դ������

@Service
class AwareService implements BeanNameAware,ResourceLoaderAware{
    private String beanName;
    private ResourceLoader resourceLoader;
    public void setBeanName(String beanName) {
        this.beanName=beanName;
    }
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader=resourceLoader;
    }
    public void outputResult(){
        System.out.println("Bean������:"+beanName);
        Resource resource=resourceLoader.getResource("classpath:com/viscu/spring/aware/test.txt");
        try{
            System.out.println("��Դ���������ص������ǣ�"+ IOUtils.toString(resource.getInputStream()));
        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

@Configuration
@ComponentScan("com.viscu.spring.aware")
public class AwareConfig {
}

class Main{
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext(AwareConfig.class);
        AwareService awareService=applicationContext.getBean(AwareService.class);
        awareService.outputResult();
        applicationContext.close();
        System.out.println(awareService.getClass().getSimpleName());
    }
}

