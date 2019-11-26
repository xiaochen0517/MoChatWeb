package com.sxjdxy.mochat.until;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.sxjdxy.mochat.data.MailData;

import javax.swing.text.StyledEditorKit;

/**
 * 功能：
 * 邮件推送工具类
 *
 * @author MoChen
 * Date  2019/11/26
 * @version 0.1
 */
public class MailUntil {

    public static boolean sendMail(String authCode, String mailAddress) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", MailData.ACCESS_KEYID, MailData.ACCESS_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        request.setRegionId("cn-hangzhou");
        request.setAccountName(MailData.MAIL_ADDRESSER);
        request.setAddressType(1);
        request.setReplyToAddress(false);
        request.setToAddress(mailAddress);
        request.setSubject(MailData.MAIL_TITLE);
        request.setTagName(MailData.MAIL_TAG);
        request.setHtmlBody("<p>您好{NickName}，欢迎使用MoChat，以下是您的验证链接：</p>" +
                "<p><a href=\""+authCode+"\">点此验证</a></p>" +
                "<p>若未注册过本产品，请忽略此邮件。</p>" +
                "<p>若有疑问请发送邮件到：lxc@xcmail.top</p>");

        try {
            SingleSendMailResponse response = client.getAcsResponse(request);
            System.out.println(response.toString());
            System.out.println(response.getRequestId());
            System.out.println(response.getEnvId());
            return true;
        } catch (ServerException e) {
            e.printStackTrace();
            return false;
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
            return false;
        }
    }

}
