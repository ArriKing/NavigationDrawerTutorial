package com.example.marco.navigationdrawertutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Marco on 17/05/2017.
 */

public class BachecaFragment extends Fragment {

    public BachecaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bacheca, container, false);

       // Button newPage = (Button)v.findViewById(R.id.button1);
       /* newPage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProvaActivity.class);
                // intent.putExtra("message", message);
                //getActivity().startActivity(intent);
                startActivity(intent);
            }
        });*/
        return v;
    }
}
