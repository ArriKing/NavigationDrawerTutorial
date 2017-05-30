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

       if (selected.length>0){
            for(int i=0;i<selected.length;i++){
                String[] info = selected[i].split(":");
                boolean corsoFound=false;
                Corso corso = new Corso(info[0],info[1]);
                List<Corso> checkCorsi = db.getAllCorsi();
                for(Corso c : checkCorsi){
                    if(c.getNumero_Corso().equals(corso.getNumero_Corso()))
                        corsoFound=true;
                }
                if(!corsoFound)
                    db.addCorso(corso);
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