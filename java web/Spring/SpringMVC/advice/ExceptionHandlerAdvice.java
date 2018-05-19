package com.viscu.springmvc.advice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ Create by ostreamBaba on 18-5-19
 * @ ����
 */

@ControllerAdvice  //�����@Componentע�� ע��Spring��Bean
public class ExceptionHandlerAdvice {
    @ExceptionHandler(value = Exception.class)//������������ ���������е�Exception
    //@ExceptionHandler����ȫ�ִ������������쳣
    public ModelAndView exception(Exception exception, WebRequest webRequest){
        ModelAndView modelAndView=new ModelAndView("error");//errorҳ��
        modelAndView.addObject("errorMessage",exception.getMessage());
        return modelAndView;
    }
    @ModelAttribute //����ֵ����ӵ�ȫ�� ����ע��@RequestMapping�ķ����ɻ�ô˼�ֵ��(�����������ǰ󶨼�ֵ�Ե�Model��)
    public void addAttributes(Model model){
        model.addAttribute("msg","������Ϣ");
    }
    @InitBinder //����WebDataBinder(�Զ���ǰ̨���������Model��)
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id"); //����request������id
    }
}


@Controller
class AdviceController{
    @RequestMapping("/advice")
    public String getSomething(@ModelAttribute("msg")String msg,DemoObj demoObj){
        throw new IllegalArgumentException("�ǳ���Ǹ����������/"+"����@ModelAttribute:"+msg);
    }
}


class DemoObj{
    private Long id;
    private String name;
    public DemoObj() {
        super();
    }
    public DemoObj(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
}
