package com.design.FactoryMethod.idcard;

import com.design.FactoryMethod.framework.Product;

/**
 * @ Create by ostreamBaba on 18-6-2
 * @ ����
 */
public class IdCard extends Product{

    private String owner;

    public IdCard(String owner) {
        System.out.println("����"+owner+"��ID��");
        this.owner = owner;
    }

    @Override
    public void use() {
        System.out.println("ʹ��"+owner+"��ID��");
    }

    public String getOwner() {
        return owner;
    }
}
