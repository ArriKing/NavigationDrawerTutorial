package com.example.marco.navigationdrawertutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Marco on 17/05/2017.
 */

public class BachecaFragment extends DialogFragment {

    ListView myList;
    Button getChoice;
    Activity context;
    //String[] Corsi_di_laurea;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context=getActivity();
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bacheca, container, false);
    }


    public void onStart(){
        super.onStart();

//        final List<Corso> corso = new ArrayList<>();
//        corso.add(new Corso("38021","Analisi I",false));
//        corso.add(new Corso("38022","Analisi II",false));
//        corso.add(new Corso("38023","Fisica I",false));
//        corso.add(new Corso("38024","Fisica II",false));
//        corso.add(new Corso("38025","Chimica",false));
//        corso.add(new Corso("38026","Informatica I",false));
//        corso.add(new Corso("38027","Basi di dati",false));
//
//
//
//        Toast.makeText(getActivity(), Corsi_di_laurea[4], Toast.LENGTH_LONG).show();

        String[] Corsi_di_laurea = {
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

        myList = (ListView)context.findViewById(R.id.list);



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_multiple_choice, Corsi_di_laurea);

        myList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        myList.setAdapter(adapter);

        Button getChoice=(Button)context.findViewById(R.id.getchoice);
        getChoice.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                // TODO Auto-generated method stub

                //String[] selected = new String[0];
                String selected="";
                int cntChoice = myList.getCount();

                SparseBooleanArray sparseBooleanArray = myList.getCheckedItemPositions();
                //Controllo che abbia selezionato almeno un corso
                if(sparseBooleanArray.size()==0){
                    Toast.makeText(context,"Seleziona un corso",Toast.LENGTH_SHORT).show();
                    return;
                }
                int k=0;
                for(int i = 0; i < cntChoice; i++){

                    if(sparseBooleanArray.get(i)) {

                        selected+= myList.getItemAtPosition(i).toString() + ",";

                    }
                }

                Intent intent=new Intent(context, BachecaActivity.class);
                intent.putExtra("c_list", selected);
                startActivity(intent);

                //Toast.makeText(getActivity(),selected, Toast.LENGTH_LONG).show();
            }});


    }

}


