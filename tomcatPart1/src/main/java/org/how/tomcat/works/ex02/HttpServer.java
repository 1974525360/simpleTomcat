package org.how.tomcat.works.ex02;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author zeng.maosen
 * @Description TODO
 * @Date 2023/01/08/23:24
 * @Version 1.0
 */
public class HttpServer {

    public static final String WEB_ROOT = System.getProperty("user.dir")
            + File.separator + "webroot";

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.await();
    }

    public void await() {
        ServerSocket serverSocket = null;
        int port = 8081;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!shutdown) {
            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
                Request request = new Request(inputStream);
                request.parse();

                Response response = new Response(outputStream);
                response.setRequest(request);
                response.sendStaticResource();

                socket.close();

                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }
}
