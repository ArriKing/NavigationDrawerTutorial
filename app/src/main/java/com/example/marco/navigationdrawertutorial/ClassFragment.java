package com.example.marco.navigationdrawertutorial;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClassFragment extends DialogFragment {
    private String BOT;
    private String EOT;
    private int A,B,C,All,firstPos;
    Activity context;

    //CREARE UN FRAGMENT
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context=getActivity();
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_class, container, false);
    }
//VARIABILI GLOBALI PER SALVARE IL VALORE SELEZIONATO NELLO SPINNER
    private void tempo_inizio(String t){
        BOT=t;
    }
    private void tempo_Fine(String t){
        EOT=t;
    }
    private void EdificioA(int a) {
        A=a;
    }
    private void EdificioB(int b) {B=b;}
    private void EdificioC(int c) {C=c;}
    private void EdificioAll(int all) {
        All=all;
    }

    public void onStart(){
        super.onStart();
        //Creiamo un listener sullo spinner che funzioni prima che venga premuto il bottone
        //spinner di inzio
        Spinner sp=(Spinner)context.findViewById(R.id.spinner_begin);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapter, View view, int pos, long id) {
                String selected = (String)adapter.getItemAtPosition(pos);
                tempo_inizio(selected.toString());
            }
            public void onNothingSelected(AdapterView<?> arg0) {}
        });
        //spinner di fine
        Spinner sp2=(Spinner)context.findViewById(R.id.spinner_end);
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapter, View view, int pos, long id) {
                String selected = (String)adapter.getItemAtPosition(pos);
                tempo_Fine(selected.toString());
            }
            public void onNothingSelected(AdapterView<?> arg0) {}
        });

        //CheckBo edifici
        CheckBox yourCheckBox_1 = (CheckBox) context.findViewById(R.id.chk_A);
        yourCheckBox_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {EdificioA(1);}
                else EdificioA(0);
            }
        });
        CheckBox yourCheckBox_2 = (CheckBox) context.findViewById(R.id.chk_B);
        yourCheckBox_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {EdificioB(1);}
                else EdificioB(0);
            }
        });
        CheckBox yourCheckBox_3 = (CheckBox) context.findViewById(R.id.chk_C);
        yourCheckBox_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {EdificioC(1);}
                else EdificioC(0);
            }
        });

        //Listener del button
        Button bt=(Button)context.findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //create an Intent object
                Intent intent=new Intent(context, SecondActivity.class);
               // CheckBox chkA = (CheckBox) context.findViewById(R.id.chk_A);
                //add data to the Intent object

                intent.putExtra("Time_1", BOT);
                intent.putExtra("Time_2", EOT);
               // intent.putExtra("Ed_A",checkBox_A.isChecked());
                intent.putExtra("Ed_A", A);
                intent.putExtra("Ed_B", B);
                intent.putExtra("Ed_C", C);
                //start the second activity
                startActivity(intent);
            }

        });
    }

}
