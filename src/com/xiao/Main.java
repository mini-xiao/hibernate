package com.xiao;

import com.xiao.Entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception{
        Configuration configuration = new Configuration();
        File file= new File("src/hibernate.cfg.xml");
        configuration.configure(file);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        //查询数据库表数据
        Query query = session.createQuery("from UserEntity");
        List<UserEntity> list = query.list();
        System.out.println(list);
        //开启事务
        Transaction transaction = session.beginTransaction();

        UserEntity userEntity =new UserEntity();
        userEntity.setId(1);
        userEntity.setName("111");
        session.save(userEntity);
        //提交事务
        transaction.commit();
        //再次查询提交后的数据
        Query query1 = session.createQuery("from UserEntity");
        List<UserEntity> list1 = query1.list();
        System.out.println(list1);
        //关闭连接
        session.close();
    }
}
