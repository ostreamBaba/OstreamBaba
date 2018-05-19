package com.viscu.springmvc.domain;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Create by ostreamBaba on 18-5-19
 * @ ����
 */

@RestController
@RequestMapping("/rest")
public class DemoRestController {
    //  /rest/getjson?id=1&name=xx
    @RequestMapping(value = "/getjson",produces = "application/json;charset=utf-8")
    public DemoObj getjson(DemoObj obj){
        return new DemoObj(obj.getId()+1,obj.getName()+"yy");
    }
    // /rest/getxml?id=1&name=xx
    //�������б�406���� ��Ҫ���ҵ�Spring�汾��jackson���汾������
    @RequestMapping(value = "/getxml",produces = "application/xml;charset=utf-8")
    public DemoObj getxml(DemoObj obj){
        return new DemoObj(obj.getId()+1,obj.getName()+"yy");
    }
}
