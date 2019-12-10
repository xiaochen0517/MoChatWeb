package com.sxjdxy.mochat.data;

/**
 * 功能：
 * 邮件推送Data
 * @author MoChen
 * Date  2019/11/26
 * @version 0.1
 */
public class MailData {

    //发信地址
    public static final String MAIL_ADDRESSER = "mochen@support.xcmail.top";

    //邮件标签
    public static final String MAIL_TAG = "MoChat验证邮件";

    //邮件标题
    public static final String MAIL_TITLE = "MoChat验证邮件";

    public static final String ACCESS_KEYID = "LTAI4FbsRzqF8xiBc8fTmfNN";

    public static final String ACCESS_SECRET = "ixs1X59s8OU6OA4iOSqhTiqFnXHnYq";

    /**
     * 注册用户模板
     */
    public static final String MSG_ADDUSER_HEAD = "<p>您好，欢迎使用MoChat，以下是您的验证链接：</p>" +
            "<p><a href=\"";

    public static final String MSG_ADDUSER_FOOT = "\">点此验证</a></p>" +
            "<p>若未注册过本产品，请忽略此邮件。</p>" +
            "<p>若有疑问请发送邮件到：lxc@xcmail.top</p>";

    public static final String MSG_EDITPW_HEAD = "<p>您好，欢迎使用MoChat，以下是您的验证码：</p>" +
            "<p>";

    public static final String MSG_EDITPW_FOOT = "</p>" +
            "<p>如非本人操作，请忽略此消息！</p>" +
            "<p>若有疑问请发送邮件到：lxc@xcmail.top</p>";

}
