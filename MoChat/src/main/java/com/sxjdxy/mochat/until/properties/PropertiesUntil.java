package com.sxjdxy.mochat.until.properties;

import org.apache.log4j.PropertyConfigurator;

import java.io.*;
import java.util.Properties;

/**
 * 功能：
 *
 * @author MoChen
 * Date  2019/10/7
 * @version 0.1
 */
public class PropertiesUntil {

    private String path;

    private Properties properties = null;

    private FileOutputStream output = null;

    public PropertiesUntil() {
    }

    public PropertiesUntil(String path) throws IOException {
        load(path);
    }

    public void load(String path) throws IOException {
        this.path = path;
        FileInputStream input = new FileInputStream(path);
        properties = new Properties();
        properties.load(input);
        input.close();
    }

    /**
     * 修改properties中的参数
     *
     * @param key   要修改的属性名
     * @param value 要修改的属性值
     * @return 是否修改成功
     * @throws IOException io异常
     */
    public void setPro(String key, String value) throws IOException {
        if (properties != null) {
            properties.setProperty(key, value);
        } else {
            throw new IOException("Please use load method");
        }
    }

    /**
     * 修改properties中的参数
     *
     * @param key 要删除的属性名
     * @return 是否修改成功
     * @throws IOException io异常
     */
    public void delPro(String key) throws IOException {
        if (properties != null) {
            properties.remove(key);
        } else {
            throw new IOException("Please use load method");
        }
    }

    /**
     * 保存修改
     *
     * @return 是否成功
     * @throws IOException
     */
    public boolean commit() throws IOException {
        if (properties != null) {
            if (output == null){
                // 文件输出流
                output = new FileOutputStream(path);
            }
            // 将Properties集合保存到流中
            properties.store(output, null);
            output.close();// 关闭流
            return true;
        } else {
            throw new IOException("Please use load method");
        }
    }

    /**
     * 根据key获取properties中的value
     *
     * @param key  要获取的属性名
     * @return 属性值
     * @throws IOException io异常
     */
    public String getPro(String key) throws IOException {
        if (properties != null) {
            return properties.getProperty(key, "");
        } else {
            throw new IOException("Please use load method");
        }
    }

    public static class Builder {
        /**
         * @param path 项目文件的绝对路径，获取方法：Thread.currentThread().getContextClassLoader().getResource("").getPath();
         *             当properties文件在resource的properties目录下时，在项目路径后面拼接"properties/xx.properties";
         * @throws IOException io异常
         */
        private Properties load(String path) throws IOException {
            FileInputStream input = new FileInputStream(path);
            Properties properties = new Properties();
            properties.load(input);
            return properties;
        }

        /**
         * 修改properties中的参数
         *
         * @param path  要修改的文件路径
         * @param key   要修改的属性名
         * @param value 要修改的属性值
         * @return 是否修改成功
         * @throws IOException io异常
         */
        public Boolean updatePro(String path, String key, String value) throws IOException {
            Properties properties = load(path);
            properties.setProperty(key, value);
            // 文件输出流
            FileOutputStream output = new FileOutputStream(path);
            // 将Properties集合保存到流中
            properties.store(output, null);
            output.close();// 关闭流
            return true;
        }

        /**
         * 修改properties中的参数
         *
         * @param key 要删除的属性名
         * @return 是否修改成功
         * @throws IOException io异常
         */
        public boolean delPro(String path, String key) throws IOException {
            Properties properties = load(path);
            properties.remove(key);
            // 文件输出流
            FileOutputStream output = new FileOutputStream(path);
            // 将Properties集合保存到流中
            properties.store(output, null);
            output.close();// 关闭流
            return true;
        }

        /**
         * 根据key获取properties中的value
         *
         * @param path 文件路径
         * @param key  要获取的属性名
         * @return 属性值
         * @throws IOException io异常
         */
        public String getPro(String path, String key) throws IOException {
            Properties properties = load(path);
            return properties.getProperty(key, "");
        }

    }


}
