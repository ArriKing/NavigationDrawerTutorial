package com.example.marco.navigationdrawertutorial;

/**
 * Created by Marco on 18/05/2017.
 */

public class Corsi {
    boolean isSelected;
    String ID;
    String Nome_Corso;

    //now create constructor and getter setter method using shortcut like command+n for mac & Alt+Insert for window.


    public Corsi(String ID, String Nome_Corso,boolean isSelected ) {
        this.isSelected = isSelected;
        this.ID=ID;
        this.Nome_Corso = Nome_Corso;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNome_Corso() {
        return Nome_Corso;
    }

    public void setNome_Corso(String nome_Corso) {
        Nome_Corso = nome_Corso;
    }
}
