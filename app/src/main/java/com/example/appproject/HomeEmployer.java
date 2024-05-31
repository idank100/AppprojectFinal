package com.example.appproject;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeEmployer extends Fragment {
    View view;
    TextView emailtextview;
    TextView fullnametextview;
    TextView citytextview;
    TextView gendertextview;
    TextView agetextview;
    TextView reasonjobtextview;
    TextView remembertextview;
    Button move;
    TextView nojoboffer;
    int counter = 0;
   ArrayList<JobApplication> jobApplications;
    ArrayList<JobApplication> jobApplicationsfinal;
    String businessname;
    FirebaseDatabase database;
    private Context mcontext;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mcontext = context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_employer, container, false);
        emailtextview = view.findViewById(R.id.EmailtextView);
        fullnametextview = view.findViewById(R.id.FullNametextView);
        citytextview = view.findViewById(R.id.CitytextView);
        gendertextview = view.findViewById(R.id.Gendertextview);
        agetextview = view.findViewById(R.id.Agetextview);
        reasonjobtextview = view.findViewById(R.id.ResonJobtextview);
        remembertextview = view.findViewById(R.id.Remembertextview);
        move = view.findViewById(R.id.move);
        nojoboffer = view.findViewById(R.id.NoJobOffers);
        counter = 0;
        jobApplications = new ArrayList<>();
        jobApplicationsfinal = new ArrayList<>();
        MainActivity activity = (MainActivity) getActivity();
        businessname = activity.getBusinessname();
        database = FirebaseDatabase.getInstance("https://projec-7ed90-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = database.getReference("applications");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                jobApplications.clear();
                //get all of the application type variables into an array
                for(DataSnapshot employeeSnapshot : snapshot.getChildren())
                {
                    JobApplication currentjobapplications= employeeSnapshot.getValue(JobApplication.class);
                    jobApplications.add(currentjobapplications);
                }
                //transfer every applications with the same business name as this user to a different array
                for(int i=0;i<jobApplications.toArray().length;i++)
                {
                    if(jobApplications.get(i).getBusinessname().equals(businessname))
                    {
                        jobApplicationsfinal.add(jobApplications.get(i));
                    }
                }
                // if the new array is not empty show the application
                if(!jobApplicationsfinal.isEmpty())
                {
                    emailtextview.setText("Email: "+jobApplicationsfinal.get(counter).getEmployee().getEmail());
                    fullnametextview.setText("Full Name: "+jobApplicationsfinal.get(counter).getEmployee().getFullName());
                    citytextview.setText("City: "+jobApplicationsfinal.get(counter).getEmployee().getCity());
                    gendertextview.setText("Gender: "+jobApplicationsfinal.get(counter).getEmployee().getGender());
                    agetextview.setText("Age: "+jobApplicationsfinal.get(counter).getEmployee().getAge());
                    reasonjobtextview.setText("Reason for getting this job: "+jobApplicationsfinal.get(counter).getReasonjob());
                    fullnametextview.setVisibility(View.VISIBLE);
                    citytextview.setVisibility(View.VISIBLE);
                    agetextview.setVisibility(View.VISIBLE);
                    reasonjobtextview.setVisibility(View.VISIBLE);
                    emailtextview.setVisibility(View.VISIBLE);
                    gendertextview.setVisibility(View.VISIBLE);
                    remembertextview.setVisibility(View.VISIBLE);

                }
                else{
                    nojoboffer.setVisibility(View.VISIBLE);
                }
                if(jobApplicationsfinal.toArray().length>1)
                {
                    move.setVisibility(View.VISIBLE);
                }
                //moves to the next application every time you pressed the button until there are no more application
                move.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        counter++;
                        if(counter<jobApplicationsfinal.toArray().length)
                        {
                            emailtextview.setText("Email: "+jobApplicationsfinal.get(counter).getEmployee().getEmail());
                            fullnametextview.setText("Full Name: "+jobApplicationsfinal.get(counter).getEmployee().getFullName());
                            citytextview.setText("City: "+jobApplicationsfinal.get(counter).getEmployee().getCity());
                            gendertextview.setText("Gender: "+jobApplicationsfinal.get(counter).getEmployee().getGender());
                            agetextview.setText("Age: "+jobApplicationsfinal.get(counter).getEmployee().getAge());
                            reasonjobtextview.setText("Reason for getting this job: "+jobApplicationsfinal.get(counter).getReasonjob());
                        }
                        else
                            Toast.makeText(mcontext,"There are no more candidate",Toast.LENGTH_LONG).show();
                    }

                });

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}