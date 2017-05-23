package com.example.marco.navigationdrawertutorial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Marco on 12/05/2017.
 */

public class BachecaActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_corsi);

        //inseriamo la gestione del pulsante UP per tornare indietro
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void onStart(){
        super.onStart();
        final Corsi_DBHandler db = new Corsi_DBHandler(this);
        TextView tv=(TextView)findViewById(R.id.textView5);
        String[] selected = getIntent().getStringExtra("c_list").split(",");



        String exit="";
        //voglio vedere il DB anche se ho selezionato alcun corso
        //se commenti questo IF lo fa, mi sa che sbaglio la condizione -.-
       if (selected.length>0){
            for(int i=0;i<selected.length;i++){
                String[] info = selected[i].split(":");
                //numero_Corso è un numero progressivo, vorrei darglielo io!
                /**
                 * Questo è stato risolto cambiando la struttura di Corso
                 * come ti ho scritto
                 * l'ID progressivo viene assegnato cmq ma visualizzo il
                 * numero del corso
                 */
                db.addCorso(new Corso(info[0],info[1]));
            }
        }

        List<Corso> corsi = db.getAllCorsi();
        for (Corso corso : corsi) {
            String log = "Id: " + corso.getNumero_Corso() + " - Name: " + corso.getNome_Corso()+"\n";
            exit+=log;
        }
        tv.setText(exit);
    }
}