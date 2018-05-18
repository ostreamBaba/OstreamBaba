package com.viscu.spring.conditional;

import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

/**
 * @ Create by ostreamBaba on 18-5-19
 * @ ����
 */
//�ж���������

@Component
class WindowsCondition implements Condition{
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return conditionContext.getEnvironment().getProperty("os.name").contains("Windows");
    }
}
@Configuration
class LinuxCondition implements Condition{
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return conditionContext.getEnvironment().getProperty("os.name").contains("Linux");
    }
}
interface ListService{
    String showListCmd();
}

class WindowsListService implements ListService{
    public String showListCmd() {
        return "dir";
    }
}
class LinuxListService implements ListService{
    public String showListCmd() {
        return "ls";
    }
}


@Configuration
@ComponentScan("com.viscu.spring.conditional")
public class ConditionConfig {
    //�жϸ�ϵͳ�����ĸ�����
    @Bean
    @Conditional(WindowsCondition.class)
    public ListService windowListService(){
        return new WindowsListService();
    }
    @Bean
    @Conditional(LinuxCondition.class)
    public ListService linuxListService(){
        return new LinuxListService();
    }
}

class Main{
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext(ConditionConfig.class);
        ListService listService=applicationContext.getBean(ListService.class);
        System.out.println(applicationContext.getEnvironment().getProperty("os.name")
                +"ϵͳ�µ��б�����Ϊ�� "+
                listService.showListCmd());
    }
}