package org.how.tomcat.works.ex02;

import javax.servlet.Servlet;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import java.util.Objects;

/**
 * @Description : TODO
 * @author: zeng.maosen
 * @date: 2023/1/9
 * @version: 1.0
 */
public class ServletProcessor1 {

    public void process(Request request,Response response){
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/")+1);
        URLClassLoader loader = null;
        try {
            URL[] urls = new URL[1];
            File classPath = null;
            URLStreamHandler streamHandler = null;
            classPath = new File(Constants.getWebRoot());
            String repository = (new URL("file",null,classPath.getCanonicalPath()+File.separator)).toString();
            urls[0] = new URL(null,repository,streamHandler);
            loader = new URLClassLoader(urls);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Class<?> clazz = null;
        try {
            if(Objects.nonNull(loader)) {
                clazz = loader.loadClass(servletName);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Servlet servlet = null;

        try {
            assert clazz != null;
            servlet = (Servlet) clazz.newInstance();
            servlet.service(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
