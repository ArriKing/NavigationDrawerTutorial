package com.example.marco.navigationdrawertutorial;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NoticeBoardFragment extends Fragment {

    //PROVA REALTIME DB
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mConditionRef = mRootRef.child("condition");




    //GESTIONE RECYCLERVIEW
    List<Messaggio> messaggeList=new ArrayList<>();
    MessageAdapter mAdapter;

    Activity context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context=getActivity();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notice_board, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onStart(){
        super.onStart();
        messaggeList.clear();
        //GESTIONE RecycleView
        RecyclerView recyclerView=(RecyclerView)context.findViewById(R.id.recycler_view);
        mAdapter=new MessageAdapter(messaggeList);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        //prepareMessageData();



        //PROVA REALTIME DB
        mConditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                prepareMessageData(text);
//                mConditionTextView.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //RIEMPIE LA RECYLCERVIEW
    /*private void prepareMessageData(){
        final Corsi_DBHandler db = new Corsi_DBHandler(this.getActivity());
        final Messaggi_DBHandler db_msg = new Messaggi_DBHandler(this.getActivity());


        //METODO CALENDAR PER LA DATA
        Calendar rightNow = Calendar.getInstance();
        int day_of_the_month = rightNow.get(Calendar.DAY_OF_MONTH);
        int month = rightNow.get(Calendar.MONTH);
        int year = rightNow.get(Calendar.YEAR);
        int hours = rightNow.get(Calendar.HOUR_OF_DAY);
        int minuts = rightNow.get(Calendar.MINUTE);

        //LISTA PER I CORSI, PER I MESSAGGI  E LA STRINGA CONTENETE LA DATA E L'ORA
        List<Corso> corsi = db.getAllCorsi();
        List<Messaggio> msgList = db_msg.getAllMsg();
        String DATA = "" + day_of_the_month+"/"+month+"/"+ year+" "+ hours+":"+minuts;


//QUESTO PEZZO FUNZIONA, VA ADATTATO AL SISTEMA CHE USEREMO PER RICEVERE I MESSAGGI DAL DOCENTE
        //DELETE PER PULIRE IL DB
         //db_msg.DeletAllMsg();

//        for (Corso corso : corsi) {
//            Messaggio msg_new=new Messaggio(corso.getNome_Corso(), "Sospensione lezione ", DATA);
//            db_msg.addMsg(msg_new);
        // inserisce il nuovo messaggio nella lista dei messaggi da visualizzare
//            msgList.add(msg_new);
//        }

        for (Messaggio msg : msgList) {
            messaggeList.add(msg);
        }
        //pulisco la lista dei messsaggi
        msgList.clear();
        mAdapter.notifyDataSetChanged();
    }*/

    private void prepareMessageData(String msgText) {
        Messaggio message = new Messaggio("Corso a caso", msgText, "ora");
        messaggeList.add(message);
        mAdapter.notifyDataSetChanged();
    }
}
