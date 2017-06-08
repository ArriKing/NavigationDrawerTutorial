package com.example.marco.navigationdrawertutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Marco on 12/05/2017.
 */

public class AccountActivity extends AppCompatActivity{
    ListView lvCorsiSeguiti;
    ListView lvNuoviCorsi;

    String[] nuoviCorsi = {
            "21055:Analisi I",
            "21011:Fisica I",
            "21010:Chimica",
            "21012:Informatica I",
            "22012:Calcolatori elettronici",
            "23012:Informatica II",
            "24012:Fisica II",
            "25052:Sistemi operativi",
            "23042:Geometria e algebra lineare",
            "21512:Fondamenti di automatica",
            "21019:Economia e organizzzazione aziendale",
            "27012:Fondamenti di elettronica"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.info_corsi);
        //inseriamo la gestione del pulsante UP per tornare indietro
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void onStart(){
        super.onStart();

        final Corsi_DBHandler db = new Corsi_DBHandler(this);
        List<Corso> corsi = db.getAllCorsi();

//GESTIONE CORSI SEGUITI
        //estraiamo tutti i corsi e li salviamo in una lista
        String[] corsiSeguiti = new String[corsi.size()];
        for(int i=0;i<corsi.size();i++){
            corsiSeguiti[i]=corsi.get(i).getNome_Corso();
        }
        //setto la ListView
        lvCorsiSeguiti =(ListView)findViewById(R.id.lv_corsi_seguiti);
        ArrayAdapter<String> adapterSeguiti = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, corsiSeguiti);
        lvCorsiSeguiti.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lvCorsiSeguiti.setAdapter(adapterSeguiti);
        ListUtils.setDynamicHeight(lvCorsiSeguiti);

        //Bottone per eliminare corso e visualizzare riepilogo
        Button deleteCorsoBtn=(Button)findViewById(R.id.bt_delete_corso);
        deleteCorsoBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                SparseBooleanArray sparseBooleanArray = lvCorsiSeguiti.getCheckedItemPositions();
                //Controllo che abbia selezionato almeno un corso
                if(sparseBooleanArray.size()==0){
                    Toast.makeText(getApplicationContext(),"Seleziona un corso da cancellare",Toast.LENGTH_SHORT).show();
                    return;
                }
                for(int i=0;i<lvCorsiSeguiti.getCount();i++){
                    if(sparseBooleanArray.get(i)){
                        db.deleteCorso(lvCorsiSeguiti.getItemAtPosition(i).toString());
                    }
                }
                refreshActivity();
            }});

//GESTIONE CORSI DA SEGUIRE
        //Gestione ListView
        lvNuoviCorsi =(ListView)findViewById(R.id.lv_nuovi_corsi);
        ArrayAdapter<String> adapterNuovi = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, nuoviCorsi);
        lvNuoviCorsi.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lvNuoviCorsi.setAdapter(adapterNuovi);
//        ListUtils.setDynamicHeight(lvNuoviCorsi);

        Button followCorsoBtn=(Button)findViewById(R.id.bt_segui_corso);
        followCorsoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selected="";
                SparseBooleanArray sparseBooleanArray = lvNuoviCorsi.getCheckedItemPositions();
                if(sparseBooleanArray.size()==0){
                    Toast.makeText(getApplicationContext(),"Seleziona un corso",Toast.LENGTH_SHORT).show();
                    return;
                }
                for(int i = 0; i < lvNuoviCorsi.getCount(); i++){
                    if(sparseBooleanArray.get(i)) {
                        selected+= lvNuoviCorsi.getItemAtPosition(i).toString() + ",";
                    }
                }
                //aggiorno il db
                addToDBCorso(selected);
                //ricarico l'activity
                refreshActivity();
            }
        });


    }//end onStart()

    //Metodo per aggiornare il db con i nuovi corsi seguiti
    public void addToDBCorso(String corsiSelezionati){
        final Corsi_DBHandler db = new Corsi_DBHandler(this);
        List<Corso> corsi = db.getAllCorsi();
        String[] selectedCorsi = corsiSelezionati.split(",");
        if (selectedCorsi.length>0){
            for(int i=0;i<selectedCorsi.length;i++){
                String[] info = selectedCorsi[i].split(":");
                boolean corsoFound=false;
                Corso corso = new Corso(info[0],info[1]);
                for(Corso c : corsi){
                    if(c.getNumero_Corso().equals(corso.getNumero_Corso()))
                        corsoFound=true;
                }
                if(!corsoFound){
                    db.addCorso(corso);
                }
            }
        }
    }
    //Metodo per ricaricare l'activity e mostrare i cambiamenti
    public void refreshActivity(){
        Intent intent = new Intent(this, AccountActivity.class);
        finish();
        startActivity(intent);
    }

//CLASSE UTILS
    public static class ListUtils {
        public static void setDynamicHeight(ListView mListView) {
            ListAdapter mListAdapter = mListView.getAdapter();
            if (mListAdapter == null) {
                // when adapter is null
                return;
            }
            int height = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
            for (int i = 0; i < mListAdapter.getCount(); i++) {
                View listItem = mListAdapter.getView(i, null, mListView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                height += listItem.getMeasuredHeight()+60;
            }
            ViewGroup.LayoutParams params = mListView.getLayoutParams();
            if(height<=500)
                params.height = height + ((mListView.getDividerHeight() + 1) * (mListAdapter.getCount() -1 ));
            else
                params.height = 500;
            mListView.setLayoutParams(params);
            mListView.requestLayout();
        }
    }
}