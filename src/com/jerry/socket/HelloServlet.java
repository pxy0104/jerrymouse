package com.jerry.socket;

/**
 * 2023/4/15
 **/


import java.io.IOException;


public class HelloServlet extends MyServlet {

    @Override
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("get hello servlet Index");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try{
            myResponse.write("post hello servlet");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

