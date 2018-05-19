package com.viscu.springmvc;

import com.viscu.springmvc.interceptor.DemoInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @ Create by ostreamBaba on 18-5-19
 * @ Spring MVC�Ķ���������Ҫ���ǵ������˼̳�WebMvcConfigurerAdapter��
 */


@Configuration
@EnableWebMvc //������Spring MVC��֧��
@ComponentScan("com.viscu.springmvc")
public class MyMvcConfig extends WebMvcConfigurerAdapter{
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //��Ӿ�̬��Դ addResourceHandler���Ⱪ¶����·�� addResourceLocationsָ�����ļ����õ�·��
        registry.addResourceHandler("/assets/").addResourceLocations("classpath:/assets/");
    }
    //������������bean ҵ����Ϊ����ÿһ������Ĵ���ʱ��
    @Bean
    public DemoInterceptor demoInterceptor(){
        return new DemoInterceptor();
    }
    //ע���������
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoInterceptor());
    }
    //ʵ��ҳ�����ת ûʲôҵ�����
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("index").setViewName("index");
        registry.addViewController("toUpload").setViewName("upload");
    }

    //��·��������.�Ļ�.����Ĳ����ᱻ���� /pathvar/xx.yy  anno/pathvar/xx.yy
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false); //���ɺ���.����Ĳ���
    }
    //�ļ��ϴ�
    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver();
        multipartResolver.setMaxInMemorySize(1000000);
        multipartResolver.setDefaultEncoding("utf-8");//�����ļ�Ĭ�ϱ���
        return multipartResolver;
    }

}
