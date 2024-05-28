package com.example.appproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileEmployer extends Fragment {
    TextView email;
    TextView fullname;
    TextView businessname;
    TextView role;
    TextView city;
    TextView phonenumber;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile_employer, container, false);
        email = view.findViewById(R.id.EmailAddressemployer);
        fullname = view.findViewById(R.id.FullNameemployer);
        businessname = view.findViewById(R.id.BusinessNameemployer);
        role = view.findViewById(R.id.Roleemployer);
        city = view.findViewById(R.id.Cityemployer);
        phonenumber = view.findViewById(R.id.Phonenumberemployer);
        MainActivity activity = (MainActivity) getActivity();
        Employer employer = activity.getEmployer();
        email.setText("Email: "+employer.getEmail());
        fullname.setText("Full Name: "+employer.getFullName());
        businessname.setText("Business name: "+employer.getBusinessName());
        role.setText("Role: "+employer.getRole());
        phonenumber.setText("Phone Number: "+String.valueOf(employer.getPhonenumber()));
        city.setText("City: "+employer.getCity());
        return view;
}
}