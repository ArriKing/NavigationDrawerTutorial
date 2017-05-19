package com.example.marco.navigationdrawertutorial;

/**
 * Created by rm on 19/05/2017.
 */

public class Messaggio {
    private String title;
    private String description;
    private String msg_time;


    public Messaggio() {
    }

    public Messaggio(String title, String description, String msg_time) {
        this.title = title;
        this.description = description;
        this.setMsg_time(msg_time);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMsg_time() {
        return msg_time;
    }

    public void setMsg_time(String msg_time) {
        this.msg_time = msg_time;
    }
}
