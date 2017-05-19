package com.example.marco.navigationdrawertutorial;

/**
 * Created by Marco on 13/05/2017.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

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
        TextView tv=(TextView)findViewById(R.id.txt_view);
        TextView tv2=(TextView)findViewById(R.id.txt_view2);
        //show text in the Intent object in the TextView
        tv.setText("Fascia oraria scelta: " + "dalle "
                + getIntent().getStringExtra("Time_1") + " alle " + getIntent().getStringExtra("Time_2"));

        String[] selected = getIntent().getStringExtra("Ed_list").split(",");
        String exit="";

        for(int i=0;i<selected.length;i++){
            exit+=selected[i] + "\n";
        }
        tv2.setText("Hai selezionato: " + "\n" + exit);


        loadTable();

    }

    //Carico elementi nella tabella
    public void loadTable(){
        List<String> classArray = Arrays.asList(getResources().getStringArray(R.array.test_class_array));
        List<String> hourArray = Arrays.asList(getResources().getStringArray(R.array.test_hour_array));

        TableLayout table=(TableLayout)findViewById(R.id.table_tm);
        //Intestazione tabella
        TableRow header=new TableRow(this);
        TextView thClass = new TextView(this);
        thClass.setText("AULA");
        header.addView(thClass);
        TextView thState = new TextView(this);
        thState.setText(("STATO"));
        thState.setGravity(Gravity.CENTER);
        header.addView(thState);
        TextView thHour = new TextView(this);
        thHour.setText("ORARIO");
        thHour.setGravity(Gravity.RIGHT);
        header.addView(thHour);
        table.addView(header);
        for(int i=0;i<classArray.size();i++){
            String cls=classArray.get(i);
            String stt="LIBERA";
            String hrr=hourArray.get(i);
            TableRow row= new TableRow(this);
            TextView tvClass=new TextView(this);
            tvClass.setText(cls);
            row.addView(tvClass);
            TextView tvState=new TextView(this);
            tvState.setText(stt);
            tvState.setGravity(Gravity.CENTER);
            row.addView(tvState);
            TextView tvHour=new TextView(this);
            tvHour.setText(hrr);
            tvHour.setGravity(Gravity.RIGHT);
            row.addView(tvHour);
            table.addView(row);;
        }
    }


}
