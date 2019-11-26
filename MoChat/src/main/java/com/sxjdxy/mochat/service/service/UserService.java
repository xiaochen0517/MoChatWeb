package com.sxjdxy.mochat.service.service;

/**
 * 功能：
 * user service 用户
 * @author MoChen
 * Date  2019/11/25
 * @version 0.1
 */
public interface UserService {

    /**
     * 注册用户
     * @param userid userid
     * @param nikename 昵称
     * @param password 密码
     * @return 处理结果
     */
    String addUser(String userid, String nikename, String password);

}
