package com.example.appproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class employerslist extends AppCompatActivity {

RecyclerView recyclerView;
DatabaseReference database;
MyAdapter myAdapter;
ArrayList<EmployerInfo> employerinfos;
Button ret;



    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(employerslist.this,MainActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employeeslist);
        ret = findViewById(R.id.button);
        recyclerView = findViewById(R.id.employersList);
        database = FirebaseDatabase.getInstance("https://projec-7ed90-default-rtdb.firebaseio.com/").getReference("employersinfo");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        employerinfos = new ArrayList<>();
        myAdapter = new MyAdapter(this,employerinfos);
        recyclerView.setAdapter(myAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    EmployerInfo employerInfo = dataSnapshot.getValue(EmployerInfo.class);
                    employerinfos.add(employerInfo);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); 
            }
        });

    }
}