package com.example.appproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class HomeEmployee extends Fragment {

    Button businesslist;
    TextView messagehome;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_employee, container, false);
        businesslist = view.findViewById(R.id.BusinessList);
        messagehome = view.findViewById(R.id.messagehome);
        businesslist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),employerslist.class);
                startActivity(intent);
            }
        });
        return view;
    }
}