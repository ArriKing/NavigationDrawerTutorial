package com.example.marco.navigationdrawertutorial;

/**
 * Created by Marco on 17/05/2017.
 */

public class Corsi {
    String code =null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    String name =null;

    public Corsi(String code, String name, boolean selected) {
        this.code = code;
        this.name = name;
        this.selected = selected;
    }


    boolean selected =false;
}
