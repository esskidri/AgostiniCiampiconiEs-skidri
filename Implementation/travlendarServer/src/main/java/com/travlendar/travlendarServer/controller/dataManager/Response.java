package com.travlendar.travlendarServer.controller.dataManager;

import com.travlendar.travlendarServer.model.clientModel.EventClient;

import java.util.List;

public class Response {
    private String message;
    private String obj;

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

    public void setObj(String obj) {
        this.obj = obj;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
