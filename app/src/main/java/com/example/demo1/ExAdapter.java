package com.example.demo1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExAdapter extends RecyclerView.Adapter<ExAdapter.ViewHolder> {

    private Context context;
    private ArrayList _id, trId, type, amount, time;

    public ExAdapter(Context context, ArrayList _id,ArrayList trId,
                     ArrayList type, ArrayList amount,
                     ArrayList time) {

        this.context = context;
        this._id = _id;
        this.trId = trId;
        this.type = type;
        this.amount = amount;
        this.time = time;

    }


    @NonNull
    @Override
    public ExAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View v = inflater.inflate(R.layout.data_row_expense, parent, false);
         View v = LayoutInflater.from(context).inflate(R.layout.data_row_expense, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExAdapter.ViewHolder holder, int position) {
        holder.expense_id.setText(String.valueOf(_id.get(position)));
        holder.expense_type.setText(String.valueOf(type.get(position)));
        holder.expense_amount.setText(String.valueOf(amount.get(position)));
        holder.expense_time.setText(String.valueOf(time.get(position)));

    }

    @Override
    public int getItemCount() {
        return _id.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView expense_id, expense_type, expense_amount, expense_time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            expense_id = itemView.findViewById(R.id.expense_id);
            expense_type = itemView.findViewById(R.id.expense_type);
            expense_amount = itemView.findViewById(R.id.expense_amount);
            expense_time = itemView.findViewById(R.id.expense_time);
        }
    }
}
