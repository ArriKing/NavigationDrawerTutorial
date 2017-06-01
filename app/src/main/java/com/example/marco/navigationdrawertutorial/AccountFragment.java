package com.example.marco.navigationdrawertutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class AccountFragment extends Fragment {
    ListView lvCorsiSeguiti;
    ListView lvNuoviCorsi;
    public static final int VIEW = 0;
    Activity context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context=getActivity();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    public void onStart(){
        super.onStart();
        final Corsi_DBHandler db = new Corsi_DBHandler(context);
        List<Corso> corsi = db.getAllCorsi();
/*
        int riepilogoCorsi = context.getIntent().getIntExtra("view", VIEW);
        //Questo va se voglio aggingere corsi, se voglio il riepilogo non serve aggiungere per forza!
        if(riepilogoCorsi!=0){
            String[] selected = context.getIntent().getStringExtra("c_list").split(",");
            if(selected.length>0){
                for(int i=0;i<selected.length;i++){
                    String[] info = selected[i].split(":");
                    boolean corsoFound=false;
                    Corso corso = new Corso(info[0],info[1]);
                    for(Corso c : corsi){
                        if(c.getNumero_Corso().equals(corso.getNumero_Corso()))
                            corsoFound=true;
                    }
                    if(!corsoFound){
                        //aggiorno db
                        db.addCorso(corso);
                        //aggiorno lista
                        corsi.add(corso);
                    }
                }
            }
        }
*/

/*

        //estraiamo tutti i corsi e li salviamo in una lista
        String[] arrayCorsi = new String[corsi.size()];
        for(int i=0;i<corsi.size();i++){
            arrayCorsi[i]=corsi.get(i).getNome_Corso();
        }
        //setto la ListView
        lvCorsiSeguiti =(ListView)context.findViewById(R.id.lv_corsi_seguiti);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_multiple_choice, arrayCorsi);
        lvCorsiSeguiti.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lvCorsiSeguiti.setAdapter(adapter);

        //Bottone per eliminare corso e visualizzare riepilogo
        Button deleteCorsoBtn=(Button)context.findViewById(R.id.bt_delete_corso);
        deleteCorsoBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                int cntChoice = lvCorsiSeguiti.getCount();//numero corsi presenti
                SparseBooleanArray sparseBooleanArray = lvCorsiSeguiti.getCheckedItemPositions();
                //Controllo che abbia selezionato almeno un corso
                if(sparseBooleanArray.size()==0){
                    Toast.makeText(context,"Seleziona un corso da cancellare",Toast.LENGTH_SHORT).show();
                    return;
                }
                for(int i=0;i<cntChoice;i++){
                    if(sparseBooleanArray.get(i)){
                        db.deleteCorso(lvCorsiSeguiti.getItemAtPosition(i).toString());
                    }
                }
                //ricarico il fragment
                Intent intent=context.getIntent();
                context.finish();
                context.startActivity(intent);
            }});
*/



        Button manageBtn=(Button)context.findViewById(R.id.bt_gestione_corsi);
        manageBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent=new Intent(context, BachecaActivity.class);
                startActivity(intent);
            }
        });



    }//end onStart()

}//end AccountFragment
