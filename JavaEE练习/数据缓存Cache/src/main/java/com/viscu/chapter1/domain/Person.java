package com.viscu.chapter1.domain;

import javax.persistence.*;

/**
 * Created by sang on 2016/12/30.
 * �������򹤳�ͨ��ʵ�������ɱ�
 */
@Entity
@NamedQuery(name = "Person.withNameAndAddressNamedQuery",
        query = "select p from Person p where p.name=?1 and p.address=?2")
public class Person {
    @Id //��������
    @GeneratedValue(strategy = GenerationType.AUTO) //Ĭ��ʹ�����������ķ�ʽ mysql
    private Long id;
    private String name;
    private Integer age;
    private String address;
    public Person() { }
    public Person(Long id, String name, Integer age, String address) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }
    public Person(String name, Integer age, String address) {
        super();
        this.name = name;
        this.age = age;
        this.address = address;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
