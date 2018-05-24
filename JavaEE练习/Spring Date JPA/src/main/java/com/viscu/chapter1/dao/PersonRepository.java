package com.viscu.chapter1.dao;

import com.viscu.chapter1.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by sang on 2016/12/30.
 */

public interface PersonRepository extends JpaRepository<Person, Long> {
    /*
     * ͨ����ַ���в�ѯ������Ϊaddress,
     * �൱��JPQL��select p from Person p where p.address=?1
     */

    List<Person> findByAddress(String name);
    /*
     * ͨ����ַ�����ֽ��в�ѯ������Ϊname,address
     * �൱��JPQL��select p from Person p where p.name=?1 and address=?2
     */
    Person findByNameAndAddress(String name, String address);
    @Query("select p from Person p where p.name=:name and p.address=:address")//������������ѯ
    Person withNameAndAddressQuery(@Param("name") String name, @Param("address") String address);
    //������������ѯ
    Person withNameAndAddressNamedQuery(String name, String address);
}
