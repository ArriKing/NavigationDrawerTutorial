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

        /*//RECYCLERVIEW
        recyclerView=(RecyclerView)context.findViewById(R.id.recycler_view);
        mAdapter=new MessageAdapter(messaggeList);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareMessageData();*/
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
        Messaggio msg=new Messaggio("ANALISI 1", "Sospensione Lezione", "15.00");
        messaggeList.add(msg);

        msg=new Messaggio("FISICA 1", "Sospensione Lezione", "15.00");
        messaggeList.add(msg);

        msg=new Messaggio("FISICA 2", "Sospensione Lezione", "15.00");
        messaggeList.add(msg);

        msg=new Messaggio("CHIMICA", "Sospensione Lezione", "15.00");
        messaggeList.add(msg);

        mAdapter.notifyDataSetChanged();
    }

}
