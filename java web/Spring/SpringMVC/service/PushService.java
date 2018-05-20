package com.viscu.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @ Create by ostreamBaba on 18-5-19
 * @ �첽��������
 */

@Controller //ͨ��������������һ���̷߳���DeferredResult
class AysncController{
    @Autowired
    private PushService pushService;
    @RequestMapping("/defer")
    @ResponseBody
    public DeferredResult<String> deferredCall(){
        System.out.println("web������: "+pushService.getDeferredResult());
        return pushService.getDeferredResult();
    }
}

@Service
public class PushService {
    private DeferredResult<String> deferredResult;
    public DeferredResult<String> getDeferredResult() {
        deferredResult = new DeferredResult<String>();
        return deferredResult;
    }
    @Scheduled(fixedDelay = 5000)
    public void refresh(){
        System.out.println("��̨������:"+deferredResult);
        if(deferredResult!=null){
            deferredResult.setResult(Long.toString(System.currentTimeMillis()));
            /*System.out.println(deferredResult.getResult());
            System.out.println(Thread.currentThread().getId());*/
        }
    }
}

//���ض�������첽������������߲��ް���������˵�ģ��������ǵ�web�������ͺ�˴�����ͬһ�����ϣ�
//��ÿ��������Ҫռ��cpuһ���ӵ�ʱ�䣬��ѹե����̨���Ե�ȫ�����ܣ�һ�������Ҳֻ�ܴ���60������
//��ʵ�ʵ���������Ǵ���ʱ�䳤�������٣�������ʱ��̵�����࣬���ҳ�ʱ�䴦��ĺ�˳��򲢲���web���������ͬһ̨�����ϣ���
//��Ҫ���ľ��ǲ�Ҫ����Ҫ��ʱ�䴦������������˹����web�������̣߳�ʹ��һЩ��ʱ�䴦�������һֱ�ò�����Ӧ��
