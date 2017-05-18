package com.example.marco.navigationdrawertutorial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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
        TextView tv=(TextView)findViewById(R.id.textView5);
        String[] selected = getIntent().getStringExtra("c_list").split(",");

        tv.setText("Corsi scelti: " + selected[0]);
    }
}