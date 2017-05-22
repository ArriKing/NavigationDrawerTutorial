package com.example.marco.navigationdrawertutorial;

/**
 * Created by Marco on 13/05/2017.
 */

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ClassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.percorso_di_studio);
        //inserisce la freccia di ritorno alla home
        //PROBLEMA:torna alla home,non al suo fragment ma al main fragment
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void onStart(){
        super.onStart();
        String Inizio = getIntent().getStringExtra("Time_1");
        String Fine = getIntent().getStringExtra("Time_2");
        String[] Edifici = getIntent().getStringExtra("Ed_list").split(",");

        String[] Aule = {
                "A002",
                "A104",
                "B001",
                "B105",
                "B201",
                "C101",
        };

        //Creazione dimaica tabella

        //Intestazione

        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        //riga della tabella
        TableRow tbrow0 = new TableRow(this);
        //prima colonna
        TextView tv0 = new TextView(this);
        tv0.setText(" AULA ");
        tbrow0.addView(tv0);
        //seconda colonna
        TextView tv1 = new TextView(this);
        tv1.setText(" INIZIO ");
        tbrow0.addView(tv1);
        //terza colonna
        TextView tv2 = new TextView(this);
        tv2.setText(" FINE ");
        tbrow0.addView(tv2);
        //aggiungo la riga
        stk.addView(tbrow0);
        //Toast.makeText(this,"Orario di Fine inferiore ad orario d'inizio !", Toast.LENGTH_LONG).show();

        for (int i = 0; i < Edifici.length; i++) {
            switch(Edifici[i].charAt(9)){
                case 'A':
                    for(int k=0;k<Aule.length;k++){
                        if(Aule[k].charAt(0)=='A') {
                            //ricorda di creare la riga ogni volta!
                            TableRow tbrow = new TableRow(this);
                            TextView t1v = new TextView(this);
                            t1v.setText(Aule[k]);
                            t1v.setGravity(Gravity.CENTER);
                            tbrow.addView(t1v);
                            //aggiungo la riga finita
                            stk.addView(tbrow);
                        }
                    }
                    break;
                case 'B':
                    for(int k=0;k<Aule.length;k++){
                        if(Aule[k].charAt(0)=='B') {
                            TableRow tbrow = new TableRow(this);
                            TextView t1v = new TextView(this);
                            t1v.setText(Aule[k]);
                            t1v.setGravity(Gravity.CENTER);
                            tbrow.addView(t1v);
                            stk.addView(tbrow);
                        }
                    }
                    break;
                case 'C':
                    for(int k=0;k<Aule.length;k++){
                        if(Aule[k].charAt(0)=='C') {
                            TableRow tbrow = new TableRow(this);
                            TextView t1v = new TextView(this);
                            t1v.setText(Aule[k]);
                            t1v.setGravity(Gravity.CENTER);
                            tbrow.addView(t1v);
                            stk.addView(tbrow);
                        }
                    }
                    break;
            }
            /////////////////////

//                TextView t1v = new TextView(this);
//                t1v.setText(Edifici[i]);
//                t1v.setGravity(Gravity.CENTER);
//                tbrow.addView(t1v);

            /////////////////////
//            TextView t2v = new TextView(this);
//            t2v.setText(Inizio);
//            t2v.setGravity(Gravity.CENTER);
//            tbrow.addView(t2v);
//            /////////////////////
//            TextView t3v = new TextView(this);
//            t3v.setText(Fine);
//            t3v.setGravity(Gravity.CENTER);
//            tbrow.addView(t3v);
            //////////////////////

           // stk.addView(tbrow);
        }

    }


}
