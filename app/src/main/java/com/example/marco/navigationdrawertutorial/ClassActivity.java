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

public class ClassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.percorso_di_studio);
        //inserisce la freccia di ritorno alla home
        //PROBLEMA:torna alla home,non al suo fragment ma al main fragment
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
   /* public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.second, menu);
        return true;
    }*/

    public void onStart(){
        super.onStart();
        String Inizio = getIntent().getStringExtra("Time_1");
        String Fine = getIntent().getStringExtra("Time_2");
        String[] Edifici = getIntent().getStringExtra("Ed_list").split(",");

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


        for (int i = 0; i < Edifici.length; i++) {
            TableRow tbrow = new TableRow(this);
            /////////////////////
            TextView t1v = new TextView(this);
            t1v.setText(Edifici[i]);
            t1v.setGravity(Gravity.CENTER);
            tbrow.addView(t1v);
            /////////////////////
            TextView t2v = new TextView(this);
            t2v.setText(Inizio);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);
            /////////////////////
            TextView t3v = new TextView(this);
            t3v.setText(Fine);
            t3v.setGravity(Gravity.CENTER);
            tbrow.addView(t3v);
            //////////////////////

            stk.addView(tbrow);
        }

//        for (int i = 0; i<Edifici.length; i++) {
//
//            TableRow row= new TableRow(this);
//            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
//            row.setLayoutParams(lp);
//
//            TextView Ed = new TextView(this);
//            Ed.setText(Edifici[i]);
//            TextView Ini = new TextView(this);
//            Ini.setText(Edifici[i]);
//            TextView Fin = new TextView(this);
//            Fin.setText(Edifici[i]);
//
//            row.addView(Ed);
//            row.addView(Ini);
//            row.addView(Fin);
//            ll.addView(row,i);
//        }



    }


}
