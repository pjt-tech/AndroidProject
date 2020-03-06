package com.kye.recyclerview_ex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    int layoutId;
    ArrayList<Recycler_Model> list;
    RecyclerView recyclerView;

    public MyAdapter(Context context, int layoutId, ArrayList<Recycler_Model> list,RecyclerView recyclerView) {
        this.context = context;
        this.layoutId = layoutId;
        this.list = list;
        this.recyclerView = recyclerView;

    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titleText;
        TextView authorText;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.titleText);
            authorText = itemView.findViewById(R.id.authorText);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layoutId,parent,false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int position =  recyclerView.getChildAdapterPosition(v);
              Recycler_Model recycler_model =  list.get(position);
              Toast.makeText(context.getApplicationContext(),recycler_model.getTitle(),Toast.LENGTH_LONG).show();
            }
        });


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Recycler_Model recycler_model = list.get(position);
        holder.titleText.setText(recycler_model.getTitle());
        holder.authorText.setText(recycler_model.getAuthor());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
