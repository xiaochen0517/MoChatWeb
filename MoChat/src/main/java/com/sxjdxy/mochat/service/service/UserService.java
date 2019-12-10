package com.sxjdxy.mochat.service.service;

/**
 * 功能：
 * user service 用户
 * @author MoChen
 * Date  2019/11/25
 * @version 0.1
 */
public interface UserService {

    String addUser(String userid, String nikename, String password, String mail);

    String verifyMail(String userId, String authKey);

    String editPw(String mail);

    String verifyEditPw(String mail, String authcode);

    String setPw(String mail, String authkey, String password);

    String getContactsDataList(String useridJsonStr);

    String getContactsDetailed(String userid);

}
