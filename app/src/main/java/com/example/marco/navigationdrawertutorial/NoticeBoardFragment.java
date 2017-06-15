package com.example.marco.navigationdrawertutorial;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NoticeBoardFragment extends Fragment {

//    String corsoNotifica="";

    //PROVA REALTIME DB
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

//    DatabaseReference mNomeCorsoRef = mRootRef.child("NomeCorso");
//    DatabaseReference mCorsoRef = mRootRef.child(corsoNotifica);
//    DatabaseReference mMessaggioRef = mCorsoRef.child("Messaggio");


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

        final Corsi_DBHandler db_corsi = new Corsi_DBHandler(context);

        //prepareMessageData();

//        mRootRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                corsoNotifica = dataSnapshot.child("NomeCorso").getValue(String.class);
//                //mCorsoRef = mRootRef.child(corsoNotifica);
//                Toast.makeText(context,corsoNotifica,Toast.LENGTH_LONG).show();
//                String text = dataSnapshot.child(corsoNotifica).child("Messaggio").getValue(String.class);
//                Toast.makeText(context, corsoNotifica+" - "+text, Toast.LENGTH_LONG).show();
//                prepareMessageData(corsoNotifica, text);
//                //sendNotification(corsoNotifica, text);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


        mRootRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                List<Corso> iscrizione_corsi = db_corsi.getAllCorsi();
                for (Corso c : iscrizione_corsi) {
                    if(dataSnapshot.getKey().toString()==c.getNome_Corso())
                        Log.i("_", "add " + dataSnapshot);
                    else
                        Log.i("_", "Non iscritto al corso");
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                List<Corso> iscrizione_corsi = db_corsi.getAllCorsi();
//                Log.i("_", "changed " + dataSnapshot);
//                corsoNotifica=dataSnapshot.getValue(String.class);
//                corsoNotifica = dataSnapshot.child("NomeCorso").getValue(String.class);
//                Toast.makeText(context,corsoNotifica,Toast.LENGTH_LONG).show();
                String title =dataSnapshot.getKey().toString();
                String text = dataSnapshot.child("Messaggio").getValue(String.class);
                Boolean flag = false;
                for (Corso c : iscrizione_corsi) {
                    if(title.equals(c.getNome_Corso())){
//                      Toast.makeText(context, title+" - "+text, Toast.LENGTH_LONG).show();
                        Toast.makeText(context, " - "+text, Toast.LENGTH_LONG).show();
                        prepareMessageData(title, text);
                        flag=true;
                        break;
                    }
                }
                if(!flag)
                    Toast.makeText(context,"Non iscritto al corso",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.i("_", "removed " + dataSnapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.i("_", "moved " + dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("_", "error " + databaseError);
            }
        });


        //PRENDO IL NOME DEL CORSO
       /* mNomeCorsoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                corsoNotifica = dataSnapshot.getValue(String.class);
                mCorsoRef = mRootRef.child(corsoNotifica);
                Toast.makeText(context,corsoNotifica,Toast.LENGTH_LONG).show();
//                mConditionTextView.setText(text);

*//*

                String text = mMessaggioRef.getRef().getKey();
                Toast.makeText(context, corsoNotifica+" - "+text, Toast.LENGTH_LONG).show();
                prepareMessageData(corsoNotifica, text);
                sendNotification(corsoNotifica, text);
*//*

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
*/

       /* mMessaggioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                Toast.makeText(context, corsoNotifica+" - "+text, Toast.LENGTH_LONG).show();
                prepareMessageData(corsoNotifica, text);
                sendNotification(corsoNotifica, text);
//                mConditionTextView.setText(text);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/


/*

        //PASSO IL NOME DEL CORSO E CONTROLLO SE E' UNO DEI CORSI SEGUITI
        final Corsi_DBHandler db = new Corsi_DBHandler(context);
        List<Corso> corsi = db.getAllCorsi();
        for(Corso c: corsi){
            String c1=c.getNome_Corso().toUpperCase();
            Toast.makeText(context, c1, Toast.LENGTH_LONG).show();
            String c2=corsoNotifica.toUpperCase();
            Toast.makeText(context, c2, Toast.LENGTH_LONG).show();
            if(c1.equals(c2)){

            }
        }
*/


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

    //METODO CALENDAR PER LA DATA
    public String getDate(){
        Calendar rightNow = Calendar.getInstance();
        int day_of_the_month = rightNow.get(Calendar.DAY_OF_MONTH);
        //AGGIUNGO UN UNO PERCHE' L'INDICIZZAZIONE DEI MESI PARTE DA 0 -.-
        int month = rightNow.get(Calendar.MONTH)+1;
        int year = rightNow.get(Calendar.YEAR);
        int hours = rightNow.get(Calendar.HOUR_OF_DAY);
        int minuts = rightNow.get(Calendar.MINUTE);
        String DATA = "" + day_of_the_month+"/"+month+"/"+ year+" "+ hours+":"+minuts;
        return DATA;
    }

    public void prepareMessageData(String corsoText, String msgText) {
        Messaggio message = new Messaggio(corsoText, msgText, getDate());
        messaggeList.add(message);
        mAdapter.notifyDataSetChanged();

    }

    public void sendNotification(String title, String text){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.drawable.ic_menu_send);
        mBuilder.setContentTitle(title);
        mBuilder.setContentText(text);

        Intent resultIntent = new Intent(context, NoticeBoardFragment.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

// notificationID allows you to update the notification later on.
        mNotificationManager.notify(1, mBuilder.build());

    }
}
