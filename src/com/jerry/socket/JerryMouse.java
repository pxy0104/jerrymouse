package com.jerry.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 启动 tomcat
 */
public class JerryMouse {
    private Integer port = 8888;    // 定义socket连接端口
    private Map<String, String> urlServletMapping = new HashMap<>();    // 存储 url 和 对应的类

    public JerryMouse(Integer port) {
        this.port = port;
    }

    public void start(){
        initServletMapping();
        try{
            ServerSocket serverSocket = null;
            serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println(socket);
                //获取连接对象的输入流
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                MyRequest myRequest = new MyRequest(inputStream);
                MyResponse myResponse = new MyResponse(outputStream);
                dispatch(myRequest, myResponse);
                socket.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void dispatch(MyRequest myRequest, MyResponse myResponse) {
        String clazz = urlServletMapping.get(myRequest.getUrl());

        try {
            Class myServletClass = Class.forName(clazz);
            MyServlet myServlet = (MyServlet) myServletClass.newInstance();
            myServlet.service(myRequest, myResponse);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initServletMapping() {
        for (ServletMapping servletMapping: ServletMappingConfig.servletMappingList){
            urlServletMapping.put(servletMapping.getUrl(), servletMapping.getClazz());
        }
    }

    public static void main(String[] args) {
        JerryMouse jerryMouse = new JerryMouse(8888);
        jerryMouse.start();
    }
}

