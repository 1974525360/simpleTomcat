package org.how.tomcat.works.ex02;

import lombok.Data;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * @Description : TODO
 * @author: zeng.maosen
 * @date: 2023/1/9
 * @version: 1.0
 */
public class Response  implements ServletResponse {

    private static final int BUFFER_SIZE = 1024;
    private Request request;
    private OutputStream output;
    private PrintWriter writer;

    public OutputStream getOutput() {
        return output;
    }

    public Request getRequest() {
        return request;
    }

    public void setOutput(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }

    public Response(Request request, OutputStream output, PrintWriter writer) {
        this.request = request;
        this.output = output;
        this.writer = writer;
    }

    public Response(OutputStream output) {
        this.output = output;
    }

    public Response(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        try{

            /* request.getUri has been replaced by request.getRequestURI */
            File file = new File(Constants.getWebRoot(), request.getUri());
            if(file.exists()) {
                String header =
                        "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/html\r\n" +
                        "\r\n";
                output.write(header.getBytes());
                fis = new FileInputStream(file);
                int ch = fis.read(bytes, 0, BUFFER_SIZE);
                while (ch != -1) {
                    output.write(bytes, 0, ch);
                    ch = fis.read(bytes, 0, BUFFER_SIZE);
                }
            }
        }catch (Exception e){
            String errorMessage =
                    "HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: 23\r\n" +
                    "\r\n" +
                    "<h1>File Not Found</h1>";
            output.write(errorMessage.getBytes());
        }
        finally {
            if (fis!=null) {
                fis.close();
            }
        }
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(output,true);
    }

    @Override
    public void setContentLength(int i) {

    }

    @Override
    public void setContentType(String s) {

    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale locale) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
