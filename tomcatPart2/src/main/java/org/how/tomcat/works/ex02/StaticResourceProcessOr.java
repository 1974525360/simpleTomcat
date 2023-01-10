package org.how.tomcat.works.ex02;

/**
 * @Description : TODO
 * @author: zeng.maosen
 * @date: 2023/1/9
 * @version: 1.0
 */
public class StaticResourceProcessOr {

    public void process(Response response,Request request){
        try {
            response.sendStaticResource();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
