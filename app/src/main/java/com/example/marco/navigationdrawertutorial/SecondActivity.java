package com.example.marco.navigationdrawertutorial;

/**
 * Created by Marco on 13/05/2017.
 */

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comunication);
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
        TextView tv=(TextView)findViewById(R.id.txt_view);
        TextView tv2=(TextView)findViewById(R.id.txt_view2);
        //show text in the Intent object in the TextView
        tv.setText("Fascia oraria scelta: " + "dalle " + getIntent().getStringExtra("Time_1") + " alle " + getIntent().getStringExtra("Time_2"));
        tv2.setText("Hai selezionato: " + "Edificio A " + getIntent().getIntExtra("Ed_A",0)+ " Edificio B " + getIntent().getIntExtra("Ed_B",0)+ " Edificio C " + getIntent().getIntExtra("Ed_C",0));
    }

}
