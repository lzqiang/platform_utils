/**  
 * Project Name:platform_utils
 * File Name:ResourceUtil.java  
 * Package Name:com.zwsafety.platform.utils  
 * Date:2015年7月27日
 * Copyright (c) 2015,zwsafety All Rights Reserved.   
 */
package com.zwsafety.platform.utils;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;


/**
* @ClassName:ResourceUtil 
* @Description:TODO(资源工具类) 
* @date:2014年8月21日 
* @author  xufeng  
* @version   1.0
* @since  JDK 1.7
 */
public final class ResourceUtil {
    /**
    * Creates a new instance of ResourceUtil.
     */
    private ResourceUtil() {
        // TODO Auto-generated constructor stub  
    }

    /**
     * ResourceBundle
     */
    private static final ResourceBundle BUNDLE = java.util.ResourceBundle
            .getBundle("sysconfig");

    /**
     * 获取配置文件参数
     * 
     * @param name String
     * @return String
     */
    public static final String getConfigByName(String name) {
        return BUNDLE.getString(name);
    }

    /**
     * 获得文件上传的根目录
     * 
     * @param request HttpServletRequest
     * @author xufeng
     * @date 2015.6.6
     * @return String
     */
    public static String getUploadRootPath(HttpServletRequest request) {
        String targetDirectory = request.getSession().getServletContext()
                .getRealPath("/WEB-INF/"); // 指定上传文件的保存地址
        String[] rootDict = {};
        if (targetDirectory.indexOf("/") != -1) {
            rootDict = targetDirectory.split("/");
        }
        if (targetDirectory.indexOf("\\") != -1) {
            rootDict = targetDirectory.split("\\\\");
        }
        StringBuffer root = new StringBuffer("");
        String osname = System.getProperties().getProperty("os.name");
     // 部署系统是linux系统
        if (osname.indexOf("linux") != -1) {
            root.append("/");
        }
        if (rootDict != null && rootDict.length > 2) {
            for (int i = 0, len = rootDict.length - 2; i < len; i++) {
                root.append(rootDict[i]).append("/");
            }
        }
        root.append("ulab_uploadFiles");
        return root.toString();
    }

    /**
     * 获得文件模板的根目录
     * 
     * @param request HttpServletRequest
     * @author xufeng
     * @date 2015.6.6
     * @return String
     */
    public static String getTemplateRootPath(HttpServletRequest request) {
        String targetDirectory = request.getSession().getServletContext()
                .getRealPath("/WEB-INF/"); // 指定上传文件的保存地址
        String[] rootDict = {};
        if (targetDirectory.indexOf("/") != -1) {
            rootDict = targetDirectory.split("/");
        }
        if (targetDirectory.indexOf("\\") != -1) {
            rootDict = targetDirectory.split("\\\\");
        }
        StringBuffer root = new StringBuffer("");
        String osname = System.getProperties().getProperty("os.name");
     // 部署系统是linux系统
        if (osname.indexOf("linux") != -1) {
            root.append("/");
        }
        if (rootDict != null && rootDict.length > 2) {
            for (int i = 0, len = rootDict.length - 2; i < len; i++) {
                root.append(rootDict[i]).append("/");
            }
        }
        root.append("ulab/template");
        return root.toString();
    }

}
