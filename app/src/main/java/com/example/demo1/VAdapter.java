package com.example.demo1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VAdapter extends RecyclerView.Adapter<VAdapter.myViewHolder> {

    private Context context;
    private ArrayList id, name, des, date, risk, description;

    VAdapter(Context context, ArrayList id, ArrayList name, ArrayList des, ArrayList date, ArrayList risk, ArrayList description){
        this.context = context;
        this.id = id;
        this.name = name;
        this.des = des;
        this.date = date;
        this.risk = risk;
        this.description = description;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.data_row, parent, false);
        return  new myViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position) {



        holder.expense_id.setText(String.valueOf(id.get(position)));
        holder.expense_name.setText(String.valueOf(name.get(position)));
        holder.expense_des.setText(String.valueOf(des.get(position)));
        holder.expense_date.setText(String.valueOf(date.get(position)));
        holder.expense_risk.setText(String.valueOf(risk.get(position)));
        holder.expense_desc.setText(String.valueOf(description.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( context, UpdateAct.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("name", String.valueOf(name.get(position)));
                intent.putExtra("des", String.valueOf(des.get(position)));
                intent.putExtra("date", String.valueOf(date.get(position)));
                intent.putExtra("risk", String.valueOf(risk.get(position)));
                intent.putExtra("description", String.valueOf(description.get(position)));
                context.startActivity(intent);
            }
            /*@Override
            public void onClick(View view) {
                Intent intent = new Intent( context, UpdateAct.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("name", String.valueOf(name.get(position)));
                intent.putExtra("des", String.valueOf(des.get(position)));
                intent.putExtra("date", String.valueOf(date.get(position)));
                intent.putExtra("risk", String.valueOf(des.get(position)));
                intent.putExtra("description", String.valueOf(description.get(position)));
                context.startActivity(intent);
            }*/
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView expense_id, expense_name, expense_des, expense_date, expense_risk, expense_desc;
        LinearLayout mainLayout;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            expense_id = itemView.findViewById(R.id.expense_id);
            expense_name = itemView.findViewById(R.id.expense_name);
            expense_des = itemView.findViewById(R.id.expense_des);
            expense_date = itemView.findViewById(R.id.expense_date);
            expense_risk = itemView.findViewById(R.id.expense_risk);
            expense_desc = itemView.findViewById(R.id.expense_desc);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
