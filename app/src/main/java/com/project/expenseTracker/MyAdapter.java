package com.project.expenseTracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.expenseTracker.model.IncomeAndExpense;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<IncomeAndExpense> list;

    public MyAdapter(Context context, ArrayList<IncomeAndExpense> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.addedexpenses_recycler, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        IncomeAndExpense incomeAndExpense = list.get(position);
        holder.amount.setText(incomeAndExpense.getAmount());
        holder.remark.setText(incomeAndExpense.getRemark());
        holder.category.setText(incomeAndExpense.getCategory());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView amount, remark, category;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            amount = itemView.findViewById(R.id.tvFirst);
            remark = itemView.findViewById(R.id.tvSecond);
            category = itemView.findViewById(R.id.tvThird);
        }
    }
}
