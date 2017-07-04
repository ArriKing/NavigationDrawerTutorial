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

public class CorsoAdapter extends RecyclerView.Adapter<CorsoAdapter.MyViewHolder>  {

    private List<Corso> corsoList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
        }
    }

    public CorsoAdapter(List<Corso> corsoList){
        this.corsoList =corsoList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Corso c = corsoList.get(position);
        holder.title.setText(c.getNome_Corso());
    }

    @Override
    public int getItemCount() {
        return corsoList.size();
    }

}
