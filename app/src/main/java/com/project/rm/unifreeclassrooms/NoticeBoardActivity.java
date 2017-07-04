package com.project.rm.unifreeclassrooms;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NoticeBoardActivity extends AppCompatActivity {
    List<Messaggio> messageList =new ArrayList<>();
    MessageAdapter mAdapter;
    private String corso_selezionato;

    ListView lvMessaggi;

    DatabaseReference databaseMessages;

    List<Messaggio> messaggeList;
    List<Corso> corsiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_board);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lvMessaggi=(ListView)findViewById(R.id.lvMessaggi);
        messaggeList =new ArrayList<>();

        Corsi_DBHandler db_corsi = new Corsi_DBHandler(NoticeBoardActivity.this);
        corsiList = db_corsi.getAllCorsi();

        databaseMessages = FirebaseDatabase.getInstance().getReference("messages");

    }


//    Gestione back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart(){
        super.onStart();

        checkEmptyList();

        databaseMessages.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                messaggeList.clear();
                for(DataSnapshot messageSnapshot : dataSnapshot.getChildren()){
                    Messaggio messaggio = messageSnapshot.getValue(Messaggio.class);
                    for(Corso c : corsiList){
                        if(c.getNome_Corso().equals(messaggio.getCorso()))
                            messaggeList.add(messaggio);
                    }
                }

                MessageListAdapter adapter = new MessageListAdapter(NoticeBoardActivity.this, messaggeList);
                lvMessaggi.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void checkEmptyList(){
        if(corsiList.isEmpty()){
            TextView tvEmptyList = (TextView)findViewById(R.id.tvEmptyList);
            tvEmptyList.setVisibility(View.VISIBLE);
        }
    }
}
