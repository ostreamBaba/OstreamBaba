package com.viscu.test;

import com.viscu.springmvc.MyMvcConfig;
import com.viscu.springmvc.springmvcTest.DemoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @ Create by ostreamBaba on 18-5-20
 * @ Spring MVC�Ĳ��� ����web��Ŀͨ����������Ŀ ��ҪһЩServlet��ص�ģ�����
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyMvcConfig.class)
@WebAppConfiguration("src/main/resources") //ָ�����ص�ApplicationContext��һ��WebApplicationContext
//������web��Դ��λ�� Ĭ����src/main/webapp ��ָ��src/main/resources

public class TestControllerIntegrationTests {
    private MockMvc mockMvc; //ģ��MVC����
    @Autowired
    private DemoService demoService;
    @Autowired
    WebApplicationContext wac; //ע��WebApplicationContext
    @Autowired
    MockHttpSession session; //��ע��ģ��� Http session
    @Autowired
    MockHttpServletRequest request; //��ע��ģ��� Http request
    @Before
    public void setup(){
        this.mockMvc= MockMvcBuilders.webAppContextSetup(this.wac).build();
    }//��ʼ��MVC����
    @Test  //����ǰ�ĳ�ʼ������
    public void testNormalController() throws Exception{
        mockMvc.perform(get("/normal")) //ģ����/normal ����get����
                .andExpect(status().isOk()) //Ԥ��״ֵ̬Ϊ200
                .andExpect(view().name("page")) //Ԥ��view����Ϊpage
                .andExpect(forwardedUrl("/WEB-INF/classes/views/page.jsp")) //Ԥ��ҳ��ת��������·��/WEB-INF/classes/views/page.jsp
                .andExpect(model().attribute("msg",demoService.hello()));//Ԥ��model�����ֵ��demoService.hello())�ķ���ֵ
    }
    @Test
    public void testRestController()throws Exception{
        mockMvc.perform(get("/testRest"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(demoService.hello()))//Ԥ�ڷ��ص�ý��������demoService.hello())
                .andExpect(content().string(demoService.hello()));//Ԥ�ڷ��ص�ֵ��demoService.hello())�ķ���ֵhello
    }
}

