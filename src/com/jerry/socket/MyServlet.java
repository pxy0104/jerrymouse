package com.jerry.socket;

/**
 * 2023/4/15
 **/

public class MyServlet {
    public void service(MyRequest myRequest, MyResponse myResponse){
        if(myRequest.getMethod().equalsIgnoreCase("POST")){
            doPost(myRequest,myResponse);
        }else if(myRequest.getMethod().equalsIgnoreCase("GET")){
            doGet(myRequest, myResponse);
        }
    }

    public void doGet(MyRequest myRequest, MyResponse myResponse) {


    }

    public void doPost(MyRequest myRequest, MyResponse myResponse) {

    }
}
