package com.example.appproject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class SendEmployee extends Fragment {
    EditText searchbusiness;
    EditText reasonjob;
    Button send;
    View view;
    FirebaseDatabase database;
    JobApplication jobApplication;
    ArrayList<JobApplication> jobApplications;
    ArrayList<Employer> employers;
    DatabaseReference myRefApplications;
    DatabaseReference  myRefisexsit;
    boolean issent;
    boolean isexsit;
    boolean donotreapet;
    private Context mcontext;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mcontext = context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_send_employee, container, false);
        searchbusiness = view.findViewById(R.id.SearchBuiness);
        reasonjob = view.findViewById(R.id.reasonjob);
        send = view.findViewById(R.id.send);
        database = FirebaseDatabase.getInstance("https://projec-7ed90-default-rtdb.firebaseio.com/");
        MainActivity mainActivity = (MainActivity)getActivity();
        Employee employee =  mainActivity.getEmployee();
        jobApplications = new ArrayList<>();
        employers = new ArrayList<>();
        issent = false;
        isexsit = false;
        donotreapet = false;
        myRefApplications = database.getReference("applications");
        myRefisexsit = database.getReference("employers");
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRefApplications.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        jobApplications.clear();
                        for(DataSnapshot jobApplicationSnapshot : snapshot.getChildren())
                        {
                            JobApplication currentjobapplications= jobApplicationSnapshot.getValue(JobApplication.class);
                            jobApplications.add(currentjobapplications);
                        }
                        for(int i =0;i<jobApplications.toArray().length;i++)
                        {
                            if(employee.getEmail().equals(jobApplications.get(i).getEmployee().getEmail())&&employee.getAge().equals(jobApplications.get(i).getEmployee().getAge())&&employee.getCity().equals(jobApplications.get(i).getEmployee().getCity())&&employee.getFullName().equals(jobApplications.get(i).getEmployee().getFullName())&&employee.getGender().equals(jobApplications.get(i).getEmployee().getGender())&&employee.getPassword().equals(jobApplications.get(i).getEmployee().getPassword())&&searchbusiness.getText().toString().equals(jobApplications.get(i).getBusinessname()))
                            {
                                issent = true;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                myRefisexsit.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        employers.clear();
                        for(DataSnapshot employerSnapshot : snapshot.getChildren())
                        {
                            Employer currentemployer= employerSnapshot.getValue(Employer.class);
                            employers.add(currentemployer);
                        }
                        for(int i =0;i<employers.toArray().length;i++)
                        {

                            if(searchbusiness.getText().toString().equals(employers.get(i).getBusinessName()))
                            {
                                isexsit = true;
                            }
                        }
                        if(issent==false&&isexsit==true)
                        {
                            DatabaseReference myRef1 = database.getReference("applications").push();
                            String business = searchbusiness.getText().toString();
                            String reasonj = reasonjob.getText().toString();
                            jobApplication = new JobApplication(employee,business,reasonj);
                            myRef1.setValue(jobApplication);
                            Toast.makeText(mcontext,"The application was sent to the workplace",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            if(issent==true)
                            {
                                Toast.makeText(mcontext,"This application was already sent to the workplace",Toast.LENGTH_LONG).show();
                            }
                            if(isexsit==false&&donotreapet == false)
                            {
                                Toast.makeText(mcontext,"This business do not exist in the system",Toast.LENGTH_LONG).show();
                                donotreapet = true;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                issent = false;
                isexsit = false;
                donotreapet = false;
            }
        });
        return view;

    }
}