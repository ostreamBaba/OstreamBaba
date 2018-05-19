package com.viscu.springmvc;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @ Create by ostreamBaba on 18-5-19
 * @ Web����
 */
public class WebInitializer implements WebApplicationInitializer{
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx=new AnnotationConfigWebApplicationContext();
        ctx.register(MyMvcConfig.class);
        ctx.setServletContext(servletContext);

        //ע��Spring MVC��DispatcherServlet
        //Spring��ҪҲ��ͨ��DispatcherServletʵ����Servlet����ӿڣ��ֽ�ǰ�˿�������
        //����ǰ�˵�������ȵ�����������𵽺�̨ȥƥ����ʵ�handler��DispatcherServlet����Ҫ�����������£�
        //ǰ�����󵽴�DispatcherServlet��
        //ǰ�˿���������HandlerMappering ��Handler��
        //������ҵ����ڵĴ���������һ��ȥ����service��dao��
        //���ؽ���ٵ�controller�㣬��Ⱦ�������ͼ�����ؽ����ҳ�档
        ServletRegistration.Dynamic servlet=servletContext.addServlet("dispatcher",new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }
}
