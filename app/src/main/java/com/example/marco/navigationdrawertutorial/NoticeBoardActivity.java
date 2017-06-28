package com.example.marco.navigationdrawertutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoticeBoardActivity extends AppCompatActivity {
    List<Messaggio> messageList =new ArrayList<>();
    MessageAdapter mAdapter;
    private String corso_selezionato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_board);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public void onStart(){
        super.onStart();
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycler_view_message);
        mAdapter=new MessageAdapter(messageList);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        corso_selezionato = getIntent().getStringExtra("corso_selected");
        MessageDataBoardCreator();
    }

    private void MessageDataBoardCreator() {
        Messaggi_DBHandler db_msg = new Messaggi_DBHandler(this);
        List<Messaggio> all_msg = db_msg.getAllMsg();
        for(Messaggio m : all_msg){
            //mostro solo i messaggi che mi interessano
            if(m.getTitle().equals(corso_selezionato)){
                messageList.add(m);
                mAdapter.notifyDataSetChanged();
            }
        }
    }


}
