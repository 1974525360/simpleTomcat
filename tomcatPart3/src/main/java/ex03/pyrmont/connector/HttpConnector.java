package ex03.pyrmont.connector;

import lombok.Data;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;

/**
 * @Description : TODO
 * @author: zeng.maosen
 * @date: 2023/1/13
 * @version: 1.0
 */
@Data
public class HttpConnector implements Callable<Object> {

    boolean stopped = false;
    private String scheme = "http";

    @Override
    public Object call() throws Exception {
        ServerSocket serverSocket =null;
        int port=8080;
        try {
            serverSocket =  new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        }catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (!stopped){
            // Accept the next incoming connection from the server socket
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            }
            catch (Exception e) {
                continue;
            }
            // Hand this socket off to an HttpProcessor
            HttpProcessor processor = new HttpProcessor(this);
            processor.process(socket);
        }
    }


}
