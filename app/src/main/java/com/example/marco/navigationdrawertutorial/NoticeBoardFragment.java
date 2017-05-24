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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NoticeBoardFragment extends Fragment {

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
        //context.setContentView(R.layout.fragment_notice_board);
//        //RECYCLERVIEW
//        RecyclerView recyclerView=(RecyclerView)context.findViewById(R.id.recycler_view);
//        mAdapter=new MessageAdapter(messaggeList);
//        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(context);
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(mAdapter);
//        prepareMessageData();
    }

    public void onStart(){
        super.onStart();

        //GESTIONE RecycleView
        RecyclerView recyclerView=(RecyclerView)context.findViewById(R.id.recycler_view);
        mAdapter=new MessageAdapter(messaggeList);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareMessageData();
    }

    //RIEMPIE LA RECYLCERVIEW
    private void prepareMessageData(){
        final Corsi_DBHandler db = new Corsi_DBHandler(this.getActivity());
        //METODO CALENDAR PER LA DATA
        Calendar rightNow = Calendar.getInstance();
        int day_of_the_month = rightNow.get(Calendar.DAY_OF_MONTH);
        int month = rightNow.get(Calendar.MONTH);
        int year = rightNow.get(Calendar.YEAR);
        int hours = rightNow.get(Calendar.HOUR);
        int minuts = rightNow.get(Calendar.MINUTE);

        String DATA = "" + day_of_the_month+"/"+month+"/"+ year+" "+ hours+":"+minuts;
        List<Corso> corsi = db.getAllCorsi();
        for (Corso corso : corsi) {
            Messaggio msg=new Messaggio(corso.getNome_Corso(), " ", DATA);
            messaggeList.add(msg);
        }
        mAdapter.notifyDataSetChanged();
    }

}
