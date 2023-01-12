package org.how.tomcat.works.ex02;

import java.io.File;

/**
 * @Description : TODO
 * @author: zeng.maosen
 * @date: 2023/1/9
 * @version: 1.0
 */
public class Constants {
    private static final String WEB_ROOT =
            System.getProperty("user.dir")+ File.separator  + "webroot";

    public static String getWebRoot() {
        return WEB_ROOT;
    }
}
