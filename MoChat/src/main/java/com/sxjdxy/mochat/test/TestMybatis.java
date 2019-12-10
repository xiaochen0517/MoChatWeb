package com.sxjdxy.mochat.test;

import com.alibaba.fastjson.JSON;
import com.sxjdxy.mochat.dao.UserDao;
import com.sxjdxy.mochat.domain.User;
import com.sxjdxy.mochat.json.domain.ContactsJson;
import com.sxjdxy.mochat.json.domain.ResContactsList;
import com.sxjdxy.mochat.until.loger.Log;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：
 *
 * @author MoChen
 * Date  2019/11/24
 * @version 0.1
 */
public class TestMybatis {

    private static final String TAG = "TestMybatis";
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
        String[] a = {
                "two",
                "three",
                "four1"
        };
        List<ContactsJson> contactsJsonList = new ArrayList<>();
        int nullNum = 0;
        for (String userid : a){
            User user = userDao.findUserSingle(userid);
            if (user != null && !user.getUserid().equals("")) {
                Log.d(TAG, user.getNickname());
                Log.d(TAG, user.getAddress());
                ContactsJson contactsJson = new ContactsJson();
                contactsJson.setUserid(user.getUserid());
                contactsJson.setNickname(user.getNickname());
                contactsJson.setIntroduce(user.getIntroduce());
                contactsJson.setSex(user.getSex());
                contactsJsonList.add(contactsJson);
            }else {
                nullNum ++;
            }
        }
        ResContactsList resContactsList = new ResContactsList();
        resContactsList.setErrorCode(0);
        resContactsList.setNullNum(nullNum);
    }

}
