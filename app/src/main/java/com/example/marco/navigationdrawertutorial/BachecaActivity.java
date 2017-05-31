package com.example.marco.navigationdrawertutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static com.example.marco.navigationdrawertutorial.R.id.parent;

/**
 * Created by Marco on 12/05/2017.
 */

public class BachecaActivity extends AppCompatActivity{
    ListView myList;
    public static final int VIEW = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.info_corsi);
        //inseriamo la gestione del pulsante UP per tornare indietro
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void onStart(){
        super.onStart();
        //valore che mi discrtizza il riepilogo dall'aggiunta di un corso
        int Riepilogo_corsi = getIntent().getIntExtra("view",VIEW);

        final Corsi_DBHandler db = new Corsi_DBHandler(this);

        List<Corso> corsi = db.getAllCorsi();
        //Questo va se voglio aggingere corsi, se voglio il riepilogo non serve aggiungere per forza!
        if(Riepilogo_corsi!=0){
        String[] selected = getIntent().getStringExtra("c_list").split(",");
        if (selected.length>0){
            for(int i=0;i<selected.length;i++){
                String[] info = selected[i].split(":");
                boolean corsoFound=false;
                Corso corso = new Corso(info[0],info[1]);
                for(Corso c : corsi){
                    if(c.getNumero_Corso().equals(corso.getNumero_Corso()))
                        corsoFound=true;
                }
                if(!corsoFound){
                    db.addCorso(corso);
                    //aggingaiamo alla lista corsi così da mantenerla aggiornata
                    corsi.add(corso);
                }
               }
            }
        }
        //estraiamo tutti i corsi e li salviamo in una lista
        String[] corsi_di_laurea = new String[corsi.size()];
        int k=0;
        for (Corso corso : corsi){
            corsi_di_laurea[k]=corso.getNome_Corso();
            k++;
        }
        myList = (ListView)findViewById(R.id.corsi);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, corsi_di_laurea);
        myList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        myList.setAdapter(adapter);

        //BUTTON PER ELIMINARE UN CORSO E VISULIZZARE IL RIEPILOGO
        Button getDelete=(Button) findViewById(R.id.getdelete);

        getDelete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                int cntChoice = myList.getCount();
                SparseBooleanArray sparseBooleanArray = myList.getCheckedItemPositions();
                //Controllo che abbia selezionato almeno un corso
                if(sparseBooleanArray.size()==0){
                    Toast.makeText(getApplicationContext(),"Seleziona un corso da cancellare",Toast.LENGTH_SHORT).show();
                    return;
                }
                for(int i = 0; i < cntChoice; i++){
                    if(sparseBooleanArray.get(i)) {
                        db.deleteCorso(myList.getItemAtPosition(i).toString());
                    }
                }
                //resetta la pagina
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }});
    }
}