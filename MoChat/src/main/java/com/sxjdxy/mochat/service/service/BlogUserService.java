package com.sxjdxy.mochat.service.service;

/**
 * 功能：
 *
 * @author MoChen
 * Date  2019/12/12
 * @version 0.1
 */
public interface BlogUserService {

    String login(String userid, String password);

    String logout(String authkey);

}
