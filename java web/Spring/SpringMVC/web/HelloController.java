package com.viscu.springmvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ Create by ostreamBaba on 18-5-19
 * @ ����
 */

@Controller //Spring MVC������Beanֻ����@Controller ��ͨbean����
public class HelloController {

    @RequestMapping("/index") //����RequestMapping����url�ͷ������ӳ�� ֧��Servlet��request��response��Ϊ����
    public String hello(){
        return "hello";
    }

}
