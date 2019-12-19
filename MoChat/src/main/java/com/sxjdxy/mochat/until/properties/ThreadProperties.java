package com.sxjdxy.mochat.until.properties;

import com.sxjdxy.mochat.until.loger.Log;

import javax.swing.text.html.HTML;
import java.util.Properties;

/**
 * 功能：
 *
 * @author MoChen
 * Date  2019/12/19
 * @version 0.1
 */
public class ThreadProperties extends Thread {

    private static final String TAG = "ThreadProperties";

    private final String path;
    private final String userid;
    private final String password;
    private final String authkey;

    public ThreadProperties(String path, String userid, String password, String authkey) {
        this.path = path;
        this.userid = userid;
        this.password = password;
        this.authkey = authkey;
    }

    @Override
    public void run() {
        super.run();
        try {
            while (true) {
                sleep(60000);
                PropertiesUntil propertiesUntil = new PropertiesUntil(path);
                String timeStr = propertiesUntil.getPro(userid);
                if (timeStr.equals("")) {
                    Log.d(TAG, "获取到的时间为空，跳出循环");
                    break;
                }
                int time = Integer.parseInt(timeStr) - 1;
                if (time <= 0){
                    //计时为0，删除对应key
                    propertiesUntil.delPro(password);
                    propertiesUntil.delPro(authkey);
                    propertiesUntil.delPro(userid);
                    propertiesUntil.commit();
                    Log.d(TAG, "计时结束，用户："+userid+"已强制下线");
                    break;
                }
                String newTimeStr = time + "";
                //写入新的时间
                propertiesUntil.setPro(userid, newTimeStr);
                propertiesUntil.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
