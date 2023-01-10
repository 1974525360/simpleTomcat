package org.how.tomcat.works.ex02;

import lombok.Data;

import java.io.InputStream;

/**
 * @Author zeng.maosen
 * @Description TODO
 * @Date 2023/01/08/22:00
 * @Version 1.0
 */
@Data
public class Request {

    private InputStream inputStream;
    private String uri;

    public Request(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void parse() {

        StringBuffer request = new StringBuffer();
        int i = 0;
        byte[] buffer = new byte[1024];
        try {
            i = inputStream.read(buffer);
        } catch (Exception e) {
            e.printStackTrace();
            i = -1;
        }

        for (int j = 0; j < i; j++) {
            request.append((char) buffer[j]);
        }
        System.out.println(request.toString());
        uri = parseUri(request.toString());
    }

    private String parseUri(String requestString) {
        int a, b;
        a = requestString.indexOf(' ');
        if (a != -1) {
            b = requestString.indexOf(' ', a + 1);
            if (b > a) {
                return requestString.substring(a + 1, b);
            }
        }
        return null;
    }
}
