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
        String exit="";

        for(int i=0;i<selected.length;i++){
            String[] info = selected[i].split(":");
            exit+= "Codice corso: " + info[0] + " Nome corso: " + info[1] + "\n" + "\n";
        }


        tv.setText(exit);
    }
}