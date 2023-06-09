package com.jerry.socket;

import java.io.IOException;
import java.io.InputStream;

/**
 * 2023/4/15
 * 请求接收类
 **/

public class MyRequest {
    // 请求路径
    private String url;
    // 请求方法
    private String method;

    // 读取输入字节流，封装成字符串格式的请求内容
    public MyRequest(InputStream inputStream) throws IOException {
        String httpRequest = "";

        byte[] httpRequestBytes = new byte[1024];

        int length =0;

        if ((length = inputStream.read(httpRequestBytes)) > 0) {
            httpRequest = new String(httpRequestBytes, 0 ,length);
        }

        // HTTP 请求协议：首行的内容依次为请求方法、请求路径以及请求协议及其对应版本号
        // GET / HTTP/1.1
        String httpHead = httpRequest.split("\n")[0]; // 取出 HTTP 请求协议的首行
        System.out.println(httpHead);
        method = httpHead.split("\\s")[0];    // 按照空格进行分割，第一个是请求的方法
        url = httpHead.split("\\s")[1];    // 按照空格进行分割，第二个是请求的路径
        System.out.println(this);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "MyRequest{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}
