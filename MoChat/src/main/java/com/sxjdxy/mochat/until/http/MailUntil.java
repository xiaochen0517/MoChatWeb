package com.sxjdxy.mochat.until.http;

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

    public static boolean sendAddUserMail(String authCode, String mailAddress){
        return sendMail(MailData.MSG_ADDUSER_HEAD+
                authCode+
                MailData.MSG_ADDUSER_FOOT, mailAddress);
    }

    public static boolean sendEditPwMail(String authCode, String mailAddress){
        return sendMail(MailData.MSG_EDITPW_HEAD+
                authCode+
                MailData.MSG_EDITPW_FOOT, mailAddress);
    }

    private static boolean sendMail(String sendMsg, String mailAddress) {
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
        request.setHtmlBody(sendMsg);
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
