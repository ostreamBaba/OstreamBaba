package com.viscu.test;

import com.viscu.spring.TestBean;
import com.viscu.spring.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ Create by ostreamBaba on 18-5-19
 * @ ����
 */

@RunWith(SpringJUnit4ClassRunner.class)//��Spring�������ṩSpring TestContext Frameword�Ĺ���
@ContextConfiguration(classes = TestConfig.class)
@ActiveProfiles("prod") //�������profile
public class DemoBeanIntegrationTests {
    @Autowired
    private TestBean testBean;

    @Test
    public void test(){
        String expected="prod";
        String actual=testBean.getContent();
        Assert.assertEquals(expected,actual);
    }

}
