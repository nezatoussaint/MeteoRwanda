package com.example.rugajupascy.meteorwanda;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
   private List <ListItem> listitems;
   private Context context;

    public MyAdapter(List<ListItem> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item ,viewGroup,false);
        return  new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ListItem listitem = listitems.get(i);
        viewHolder.titles.setText(listitem.getTitle());
        viewHolder.locations.setText(listitem.getLocationSensor());
        viewHolder.descriptions.setText(listitem.getDescriptionSensor());

    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{
        public TextView titles,locations,descriptions;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titles =(TextView) itemView.findViewById(R.id.title);
            locations=(TextView) itemView.findViewById(R.id.address);
            descriptions=(TextView)itemView.findViewById(R.id.description);
        }
    }
}
