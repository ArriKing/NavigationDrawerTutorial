package com.example.marco.navigationdrawertutorial;

/**
 * Created by Marco on 18/05/2017.
 */

public class Corso {

    int ID;
    String Nome_Corso;

    //now create constructor and getter setter method using shortcut like command+n for mac & Alt+Insert for window.
    public Corso()
    {
    }

    public Corso(int ID, String Nome_Corso) {
        this.ID=ID;
        this.Nome_Corso = Nome_Corso;
    }



    public int getID() {
        return ID;
    }

    public void setID(int ID) {

        this.ID = ID;
    }

    public String getNome_Corso() {
        return Nome_Corso;
    }

    public void setNome_Corso(String nome_Corso) {
        Nome_Corso = nome_Corso;
    }
}
