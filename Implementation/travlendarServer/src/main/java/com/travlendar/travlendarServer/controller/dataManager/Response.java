package com.travlendar.travlendarServer.controller.dataManager;

public class Response {
    private String message;
    private Object obj;

    public Response(String message) {
        this.message = message;
        this.obj=null;
    }

    public String getMessage() {
        return message;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
