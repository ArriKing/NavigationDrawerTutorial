package com.project.rm.unifreeclassrooms;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rm on 19/05/2017.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder>  {

    private List<Messaggio> messageList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, msg_time, description;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            description = (TextView) view.findViewById(R.id.description);
            //msg_time = (TextView) view.findViewById(R.id.msg_time);
        }
    }

    public MessageAdapter(List<Messaggio> messageList){
        this.messageList=messageList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Messaggio msg = messageList.get(position);
        holder.title.setText(msg.getTestoMessaggio());
        holder.description.setText(msg.getTimeStamp());
        //holder.msg_time.setText(msg.getMsg_time());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

}

