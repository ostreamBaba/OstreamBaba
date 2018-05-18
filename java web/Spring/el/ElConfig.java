package com.viscu.spring.el;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Spring EL
 */

@Service
class DemoService{
    @Value("�����������")
    private String another;
    //todo
    public String getAnother() {
        return another;
    }
    public void setAnother(String another) {
        this.another = another;
    }
}


@Configuration
@ComponentScan("com.viscu.spring.el")
@PropertySource("classpath:com/viscu/spring/el/test.properties")
public class ElConfig {
    @Value("#{demoService.another}")
    private String fromAnother;

    //ʹ��@Valueע����ҪPropertySourcesPlaceholderConfigurer��Bean
    @Value("${programmer.name}")
    private String programmerName;

    //ע���properties����ͨ��Environment���
    @Autowired
    private Environment environment;

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    public void outputResource(){
        System.out.println(fromAnother);
        System.out.println(programmerName);
        System.out.println(environment.getProperty("programmer.word"));
    }
}

class ElMain{
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext(ElConfig.class);
        ElConfig elConfig=applicationContext.getBean(ElConfig.class);
        elConfig.outputResource();
    }
}
