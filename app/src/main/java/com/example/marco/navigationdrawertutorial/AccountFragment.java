package com.example.marco.navigationdrawertutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

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
        Button manageBtn=(Button)context.findViewById(R.id.bt_gestione_corsi);
        manageBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent=new Intent(context, AccountActivity.class);
                startActivity(intent);
            }
        });

    }//end onStart()

}//end AccountFragment
