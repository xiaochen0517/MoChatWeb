package com.sxjdxy.mochat.data;


/**
 * 功能：
 * 聊天IM接口地址
 *
 * @author MoChen
 * Date  2019/11/25
 * @version 0.1
 */
public class IMUrlData {

    private final static String ORG_NAME = "/1114191121181676";

    private final static String APP_NAME = "/mochat";

    private final static String DOMAIN_NAME = "http://a1.easemob.com";

    public final static String CLIENT_ID = "YXA6HeDCLeGHSjyOc_V_VwLBJg";

    public final static String CLIENT_SECRET = "YXA63mCLnvDZd8-wTdpAr2j_uPHcF2Q";

    /**
     * 开发注册用户接口
     */
    public final static String ADD_USER_URL = DOMAIN_NAME + ORG_NAME + APP_NAME + "/users";

    public final static String EDIT_PASSWORD_URL = DOMAIN_NAME + ORG_NAME + APP_NAME + "/users/";

}
