package com.example.appproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ProfileEmployee extends Fragment {
    TextView email;
    TextView fullname;
    TextView gender;
    TextView age;
    TextView city;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_profile_employee, container, false);
         email = view.findViewById(R.id.EmailAddress);
         fullname = view.findViewById(R.id.FullNameF);
         gender = view.findViewById(R.id.GenderF);
         age = view.findViewById(R.id.AgeF);
         city = view.findViewById(R.id.CityF);
         MainActivity activity = (MainActivity) getActivity();
         Employee employee = activity.getEmployee();
         email.setText("Email: "+employee.getEmail());
         fullname.setText("Full Name: "+employee.getFullName());
         gender.setText("Gender: "+employee.getGender());
         age.setText("Age: "+employee.getAge());
         city.setText("City: "+employee.getCity());
         return view;
    }
}