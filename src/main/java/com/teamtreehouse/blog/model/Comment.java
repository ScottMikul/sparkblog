package com.teamtreehouse.blog.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {
    private String name;
    private String date;
    private String message;

    public Comment(String name, String message) {
        if(name ==null ||name.equals("")){
            this.name= "Anonymous";
        }
        else {
            this.name = name;
        }
        this.message = message;
        this.date = DateUtil.getTodaysDate();
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
