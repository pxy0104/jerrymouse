package com.jerry.socket;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 2023/4/15
 **/

public class MyResponse {
    private OutputStream outputStream;
    public MyResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    //将文本转换为字节流
    public void write(String content) throws IOException {
        StringBuffer httpResponse = new StringBuffer();
        httpResponse.append("HTTP/1.1 200 OK\n")      //按照HTTP响应报文的格式写入
                .append("Content-Type:text/html\n")
                .append("\r\n")
                .append("<html><head><link rel=\"icon\" href=\"data:;base64,=\"></head><body>")
                .append(content)          //将页面内容写入
                .append("</body></html>");
        outputStream.write(httpResponse.toString().getBytes());      //将文本转为字节流
        outputStream.close();
    }

    public String getResponseMessage(String code, String message) {
        return "HTTP/1.1 " +code+"\r\n"
                + "Content-type: text/html\r\n"
                + "Content-Length: " + message.length()
                +"\r\n"
                +"\r\n"
                +message;
    }
}

