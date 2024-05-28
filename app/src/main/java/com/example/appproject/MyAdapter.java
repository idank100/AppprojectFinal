package com.example.appproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

      Context context;

      ArrayList<EmployerInfo> employerinfos;

    public MyAdapter(Context context, ArrayList<EmployerInfo> employerinfos) {
        this.context = context;
        this.employerinfos = employerinfos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        EmployerInfo employerinfo = employerinfos.get(position);
        holder.FullName.setText(employerinfo .getFullName());
        holder.City.setText(employerinfo .getCity());
        holder.BusinessName.setText(employerinfo .getBusinessName());
        holder.Role.setText(employerinfo .getRole());
        holder.PhoneNumber.setText(String.valueOf((employerinfos.get(position).getPhonenumber())));
    }

    @Override
    public int getItemCount() {
        return employerinfos.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView FullName,City,BusinessName,Role,PhoneNumber;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            FullName = itemView.findViewById(R.id.tvFullName);
            City = itemView.findViewById(R.id.tvCity);
            BusinessName = itemView.findViewById(R.id.tvBusinessName);
            Role = itemView.findViewById(R.id.tvRole);
            PhoneNumber = itemView.findViewById(R.id.tvPhoneNumber);


        }
    }
}
