package com.project.rm.unifreeclassrooms;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class NoticeBoardFragment extends Fragment {
    //GESTIONE RECYCLERVIEW
    List<Corso> corsoList =new ArrayList<>();
    CorsoAdapter mAdapter;

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

        corsoList.clear();
        //GESTIONE RecycleView
        RecyclerView recyclerView=(RecyclerView)context.findViewById(R.id.recycler_view);
        mAdapter=new CorsoAdapter(corsoList);
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        //final Corsi_DBHandler db_corsi = new Corsi_DBHandler(context);

        CoursesBoardCreator();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(context, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Corso corso = corsoList.get(position);
                Intent intent=new Intent(context, NoticeBoardActivity.class);
                intent.putExtra("corso_selected", corso.getNome_Corso());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }//fine OnStart

    public void prepareMessageData(String corsoText, String msgText) {

    }

    public void CoursesBoardCreator(){
        Corsi_DBHandler db_corsi = new Corsi_DBHandler(context);
        List<Corso> corsi = db_corsi.getAllCorsi();
        for(Corso c : corsi){
            corsoList.add(c);
            mAdapter.notifyDataSetChanged();
        }
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
