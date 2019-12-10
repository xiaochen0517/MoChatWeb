package com.sxjdxy.mochat.json.domain;

import java.util.List;

/**
 * 功能：
 * 查询用户信息列表返回数据类
 * @author MoChen
 * Date  2019/12/10
 * @version 0.1
 */
public class ResContactsList {

    private int ErrorCode;//错误码

    private int NullNum;//空值条数

    private List<ContactsJson> ContactsList;//用户信息list

    @Override
    public String toString() {
        return "ResContactsList{" +
                "ErrorCode=" + ErrorCode +
                ", NullNum=" + NullNum +
                ", ContactsList=" + ContactsList +
                '}';
    }

    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int errorCode) {
        ErrorCode = errorCode;
    }

    public int getNullNum() {
        return NullNum;
    }

    public void setNullNum(int nullNum) {
        NullNum = nullNum;
    }

    public List<ContactsJson> getContactsList() {
        return ContactsList;
    }

    public void setContactsList(List<ContactsJson> contactsList) {
        ContactsList = contactsList;
    }
}
