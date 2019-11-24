package com.sxjdxy.mochat.test;

import com.sxjdxy.mochat.dao.UserDao;
import com.sxjdxy.mochat.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 功能：
 *
 * @author MoChen
 * Date  2019/11/24
 * @version 0.1
 */
public class TestMybatis {

    private SqlSession session;

    @Before
    public void b(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        SqlSessionFactory factory = (SqlSessionFactory) context.getBean("sqlSessionFactory");
        // 创建sqlSession对象
        session = factory.openSession();
    }

    @After
    public void a(){
        session.close();
    }

    @Test
    public void testMyBatis(){
        System.out.println("start Test");
        UserDao userDao = session.getMapper(UserDao.class);
        List<User> user = userDao.select();
        for (User u:user){
            System.out.println(u.toString());
        }
    }

}