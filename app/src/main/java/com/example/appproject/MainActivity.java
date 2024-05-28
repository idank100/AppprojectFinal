package com.example.appproject;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    Button employeechoice;
    Button employerchocie;
    Button signupemployee;
    Button loginemployee;
    Button signupemployer;
    Button loginemployer;
    Button Submitemployersignup;
    Button Submitemployerlogin;
    Button Submitemployeesignup;
    Button Submitemployeelogin;
    EditText Email;
    EditText FullName;
    EditText City;
    EditText Businessemployer;
    EditText Roleemployer;
    EditText Password;
    EditText PhoneNumber;
    EditText Gender;
    EditText Age;
    FirebaseDatabase database;
    BottomNavigationView bottomNavigationViewemployee;
    BottomNavigationView bottomNavigationViewemployer;
    Employee employeeuser;
    Employer employeruser;
    boolean isexsitsignup = false;
    boolean isexsitlogin = false;
    int phonenumber=0;
    boolean iswritten = false;
    boolean donotreapet = false;
    String businessname;
    Fragment homeemployer = new HomeEmployer();
    Fragment homeemployee = new HomeEmployee();
    Fragment profileemployee = new ProfileEmployee();
    Fragment profileemployer = new ProfileEmployer();
    Fragment sendemployee = new SendEmployee();
    ImageButton returnfirst;
    ImageButton returnemployer;
    ImageButton returnemployee;
    private void showNotification(String message) {
        // Notification ID
        int notificationId = 1;
        // Notification Channel ID (if using Android Oreo or newer)
        String channelId = "channel_id";
        // Notification Channel Name (if using Android Oreo or newer)
        CharSequence channelName = "Channel Name";

        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("You logged in")
                .setContentText(message)
                .setAutoCancel(true);

        Notification notification;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            // For API 26 and higher, create a NotificationChannel
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
            notification = builder.build();
        } else {
            // For lower API versions, create a Notification without a channel
            notification = builder.getNotification();
        }
        // Display the notification
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notification);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        employeechoice = findViewById(R.id.employee1);
        employerchocie = findViewById(R.id.employer1);
        signupemployee = findViewById(R.id.sign_up_employee);
        loginemployee = findViewById(R.id.log_in_employee);
        signupemployer = findViewById(R.id.sign_up_employer);
        loginemployer = findViewById(R.id.log_in_employer);
        Submitemployersignup = findViewById(R.id.Semployersignup);
        Submitemployerlogin = findViewById(R.id.Semployerlogin);
        Submitemployeesignup = findViewById(R.id.Semployeesignup);
        Submitemployeelogin = findViewById(R.id.Semployeelogin);
        Email = findViewById(R.id.EmailAddress);
        FullName = findViewById(R.id.FullNamesignup);
        City = findViewById(R.id.Citysignup);
        Businessemployer = findViewById(R.id.BusinessNamesignupemployer);
        Roleemployer = findViewById(R.id.Rolesignupemployer);
        Password = findViewById(R.id.Password);
        PhoneNumber = findViewById(R.id.Phone);
        Age = findViewById(R.id.Age);
        Gender = findViewById(R.id.Gender);
        database = FirebaseDatabase.getInstance("https://projec-7ed90-default-rtdb.firebaseio.com/");
        bottomNavigationViewemployee = findViewById(R.id.bottom_navigation_employee);
        bottomNavigationViewemployer = findViewById(R.id.bottom_navigation_employer);
        returnfirst = findViewById(R.id.returnfirstscreen);
        returnemployee = findViewById(R.id.returnemployee);
        returnemployer = findViewById(R.id.returnemployer);

        returnfirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employeechoice.setVisibility(View.VISIBLE);
                employerchocie.setVisibility(View.VISIBLE);
                signupemployee.setVisibility(View.GONE);
                loginemployee.setVisibility(View.GONE);
                signupemployer.setVisibility(View.GONE);
                loginemployer.setVisibility(View.GONE);
                returnfirst.setVisibility(View.GONE);
            }
        });
        returnemployer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Email.setVisibility(View.GONE);
                FullName.setVisibility(View.GONE);
                City.setVisibility(View.GONE);
                Businessemployer.setVisibility(View.GONE);
                Roleemployer.setVisibility(View.GONE);
                Password.setVisibility(View.GONE);
                PhoneNumber.setVisibility(View.GONE);
                Submitemployersignup.setVisibility(View.GONE);
                Submitemployerlogin.setVisibility(View.GONE);
                returnemployer.setVisibility(View.GONE);
                signupemployer.setVisibility(View.VISIBLE);
                loginemployer.setVisibility(View.VISIBLE);
                returnfirst.setVisibility(View.VISIBLE);
                clearEditTextFields();
            }
        });
        returnemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Email.setVisibility(View.GONE);
                FullName.setVisibility(View.GONE);
                City.setVisibility(View.GONE);
                Password.setVisibility(View.GONE);
                Age.setVisibility(View.GONE);
                Gender.setVisibility(View.GONE);
                Submitemployeesignup.setVisibility(View.GONE);
                Submitemployeelogin.setVisibility(View.GONE);
                returnemployee.setVisibility(View.GONE);
                signupemployee.setVisibility(View.VISIBLE);
                loginemployee.setVisibility(View.VISIBLE);
                returnfirst.setVisibility(View.VISIBLE);
                clearEditTextFields();
            }
        });
        employeechoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                employeechoice.setVisibility(View.GONE);
                employerchocie.setVisibility(View.GONE);
                signupemployee.setVisibility(View.VISIBLE);
                loginemployee.setVisibility(View.VISIBLE);
                returnfirst.setVisibility(View.VISIBLE);
            }
        });
        signupemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Email.setVisibility(View.VISIBLE);
                FullName.setVisibility(View.VISIBLE);
                City.setVisibility(View.VISIBLE);
                Password.setVisibility(View.VISIBLE);
                Age.setVisibility(View.VISIBLE);
                Gender.setVisibility(View.VISIBLE);
                Submitemployeesignup.setVisibility(View.VISIBLE);
                signupemployee.setVisibility(View.GONE);
                loginemployee.setVisibility(View.GONE);
                returnfirst.setVisibility(View.GONE);
                returnemployee.setVisibility(View.VISIBLE);
            }
        });
        Submitemployeesignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isexsitsignup = false;
                iswritten = false;
                donotreapet = false;
                String email = Email.getText().toString(),fullname = FullName.getText().toString(),city = City.getText().toString(),password = Password.getText().toString(),age = Age.getText().toString(),gender = Gender.getText().toString();
                employeeuser = new Employee(email,fullname,gender,age,city,password);
                ArrayList<Employee> employees = new ArrayList<>();
                DatabaseReference myRef = database.getReference("employees");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        employees.clear();
                        for(DataSnapshot employeeSnapshot : snapshot.getChildren())
                        {
                            Employee currentemployee = employeeSnapshot.getValue(Employee.class);
                            employees.add(currentemployee);
                        }
                        for(int i = 0;i<employees.toArray().length;i++)
                        {
                            if(email.equals(employees.get(i).getEmail())&&fullname.equals(employees.get(i).getFullName())&&city.equals(employees.get(i).getCity())&&password.equals(employees.get(i).getPassword())&&age.equals(employees.get(i).getAge())&&gender.equals(employees.get(i).getGender()))
                            {
                                isexsitsignup = true;

                            }
                        }
                        if(email.length()>1&&fullname.length()>1&&city.length()>1&gender.length()>1&&age.length()>1&&password.length()>1)
                        {
                            iswritten = true;
                        }
                        if(isexsitsignup==false&&iswritten == true&&donotreapet == false)
                        {
                            donotreapet = true;
                            addEmployee();
                            Email.setVisibility(View.GONE);
                            FullName.setVisibility(View.GONE);
                            City.setVisibility(View.GONE);
                            Password.setVisibility(View.GONE);
                            Age.setVisibility(View.GONE);
                            Gender.setVisibility(View.GONE);
                            Submitemployeesignup.setVisibility(View.GONE);
                            bottomNavigationViewemployee.setVisibility(View.VISIBLE);
                            returnemployee.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this,"User created successfully",Toast.LENGTH_LONG).show();
                            loadFragment(R.id.framedisplay,homeemployee);
                            clearEditTextFields();
                        }
                        else
                        {
                            if(isexsitsignup==true&&donotreapet == false)
                            {
                                Toast.makeText(MainActivity.this,"this user already exists",Toast.LENGTH_LONG).show();
                            }
                            if(iswritten==false&&donotreapet == false)
                            {
                                Toast.makeText(MainActivity.this,"to sign up you must write in every category",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this,"failed",Toast.LENGTH_LONG).show();
                    }

                });
            }
        });
        loginemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Email.setVisibility(View.VISIBLE);
                Password.setVisibility(View.VISIBLE);
                Submitemployeelogin.setVisibility(View.VISIBLE);
                loginemployee.setVisibility(View.GONE);
                signupemployee.setVisibility(View.GONE);
                returnfirst.setVisibility(View.GONE);
                returnemployee.setVisibility(View.VISIBLE);
            }
        });
        Submitemployeelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isexsitlogin = false;
                ArrayList<Employee> employees = new ArrayList<>();
                DatabaseReference myRef = database.getReference("employees");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        employees.clear();
                        for(DataSnapshot employeeSnapshot : snapshot.getChildren())
                        {
                            Employee currentemployee = employeeSnapshot.getValue(Employee.class);
                            employees.add(currentemployee);
                        }
                        for(int i = 0;i<employees.toArray().length;i++)
                        {
                            if((Email.getText().toString()).equals(employees.get(i).getEmail())&&(Password.getText().toString()).equals(employees.get(i).getPassword()))
                            {
                                isexsitlogin = true;
                                employeeuser = employees.get(i);
                            }
                        }
                        if(isexsitlogin==true)
                        {
                            Email.setVisibility(View.GONE);
                            Password.setVisibility(View.GONE);
                            Submitemployeelogin.setVisibility(View.GONE);
                            bottomNavigationViewemployee.setVisibility(View.VISIBLE);
                            returnemployee.setVisibility(View.GONE);
                            loadFragment(R.id.framedisplay,homeemployee);
                            clearEditTextFields();
                            showNotification("please check the business list");

                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"this user is not exists",Toast.LENGTH_LONG).show();
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this,"failed",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        bottomNavigationViewemployee.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_home:
                        loadFragment(R.id.framedisplay,homeemployee);
                        return true;
                    case R.id.menu_profile:
                        loadFragment(R.id.framedisplay,profileemployee);
                        return true;
                    case R.id.menu_send:
                        loadFragment(R.id.framedisplay,sendemployee);
                        return true;
                    case R.id.menu_logout:
                        removeFragment(homeemployee);
                        removeFragment(profileemployee);
                        removeFragment(sendemployee);
                        bottomNavigationViewemployee.setVisibility(View.GONE);
                        loginemployee.setVisibility(View.VISIBLE);
                        signupemployee.setVisibility(View.VISIBLE);
                        returnfirst.setVisibility(View.VISIBLE);
                        sendemployee = new SendEmployee();
                        profileemployee = new ProfileEmployee();
                        homeemployee = new HomeEmployee();
                        return true;
                }
                return false;
            }
        });
        employerchocie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                employeechoice.setVisibility(View.GONE);
                employerchocie.setVisibility(View.GONE);
                signupemployer.setVisibility(View.VISIBLE);
                loginemployer.setVisibility(View.VISIBLE);
                returnfirst.setVisibility(View.VISIBLE);
            }
        });
        signupemployer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Email.setVisibility(View.VISIBLE);
                FullName.setVisibility(View.VISIBLE);
                City.setVisibility(View.VISIBLE);
                Businessemployer.setVisibility(View.VISIBLE);
                Roleemployer.setVisibility(View.VISIBLE);
                Password.setVisibility(View.VISIBLE);
                PhoneNumber.setVisibility(View.VISIBLE);
                Submitemployersignup.setVisibility(View.VISIBLE);
                signupemployer.setVisibility(View.GONE);
                loginemployer.setVisibility(View.GONE);
                returnfirst.setVisibility(View.GONE);
                returnemployer.setVisibility(View.VISIBLE);
            }
        });
        Submitemployersignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isexsitsignup = false;
                iswritten = false;
                donotreapet = false;
                String email = Email.getText().toString(),fullname = FullName.getText().toString(),city = City.getText().toString(),businessemployer = Businessemployer.getText().toString(),roleemployer = Roleemployer.getText().toString(),password = Password.getText().toString(),phonenumberstr= PhoneNumber.getText().toString();
                businessname = businessemployer;
                if(phonenumberstr.length()>=9&&phonenumberstr.length()<=10&&phonenumberstr.charAt(0)=='0')
                {
                    phonenumber = Integer.parseInt(phonenumberstr);
                    employeruser = new Employer(email,fullname,city,businessemployer,roleemployer,password,phonenumber);
                    ArrayList<Employer> employers = new ArrayList<>();
                    DatabaseReference myRef = database.getReference("employers");
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            employers.clear();
                            for(DataSnapshot employerSnapshot : snapshot.getChildren())
                            {
                                Employer currentemployer = employerSnapshot.getValue(Employer.class);
                                employers.add(currentemployer);
                            }
                            for(int i = 0;i<employers.toArray().length;i++)
                            {
                                if(email.equals(employers.get(i).getEmail())&&fullname.equals(employers.get(i).getFullName())&&city.equals(employers.get(i).getCity())&&password.equals(employers.get(i).getPassword())&&businessemployer.equals(employers.get(i).getBusinessName())&&roleemployer.equals(employers.get(i).getRole())&&phonenumber==employers.get(i).getPhonenumber())
                                {
                                    isexsitsignup = true;
                                }
                            }
                            if(email.length()>1&&fullname.length()>1&&city.length()>1&&businessemployer.length()>1&&roleemployer.length()>1&&password.length()>1)
                            {
                                iswritten = true;
                            }
                            if(isexsitsignup==false&&iswritten == true&&donotreapet == false)
                            {
                                donotreapet = true;
                                addEmployerInfo();
                                addEmployer();
                                Submitemployersignup.setVisibility(View.GONE);
                                Email.setVisibility(View.GONE);
                                FullName.setVisibility(View.GONE);
                                City.setVisibility(View.GONE);
                                Businessemployer.setVisibility(View.GONE);
                                Roleemployer.setVisibility(View.GONE);
                                Password.setVisibility(View.GONE);
                                PhoneNumber.setVisibility(View.GONE);
                                Submitemployeesignup.setVisibility(View.GONE);
                                bottomNavigationViewemployer.setVisibility(View.VISIBLE);
                                returnemployer.setVisibility(View.GONE);
                                clearEditTextFields();
                                Toast.makeText(MainActivity.this,"User created successfully",Toast.LENGTH_LONG).show();
                                loadFragment(R.id.framedisplay,homeemployer);

                            }
                            else
                            {
                                if(isexsitsignup==true&&donotreapet == false)
                                {
                                    Toast.makeText(MainActivity.this,"this user is already exists",Toast.LENGTH_LONG).show();
                                }
                                if(iswritten==false&&donotreapet == false)
                                {
                                    Toast.makeText(MainActivity.this,"to sign up you must write in every category",Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else
                {
                    Toast.makeText(MainActivity.this,"phone must have between 9-10 numbers",Toast.LENGTH_LONG).show();
                    Toast.makeText(MainActivity.this,"first number must be 0",Toast.LENGTH_LONG).show();
                }

            }
        });
        loginemployer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Email.setVisibility(View.VISIBLE);
                Password.setVisibility(View.VISIBLE);
                Submitemployerlogin.setVisibility(View.VISIBLE);
                loginemployer.setVisibility(View.GONE);
                signupemployer.setVisibility(View.GONE);
                returnfirst.setVisibility(View.GONE);
                returnemployer.setVisibility(View.VISIBLE);
            }
        });
        Submitemployerlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isexsitlogin = false;
                ArrayList<Employer> employers = new ArrayList<>();
                DatabaseReference myRef = database.getReference("employers");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        employers.clear();
                        for(DataSnapshot employerSnapshot : snapshot.getChildren())
                        {
                            Employer currentemployer = employerSnapshot.getValue(Employer.class);
                            employers.add(currentemployer);
                        }
                        for(int i = 0;i<employers.toArray().length;i++)
                        {
                            if(Email.getText().toString().equals(employers.get(i).getEmail())&&Password.getText().toString().equals(employers.get(i).getPassword()))
                            {
                                isexsitlogin = true;
                                employeruser = employers.get(i);
                            }
                        }
                        if(isexsitlogin==true)
                        {
                            Email.setVisibility(View.GONE);
                            Password.setVisibility(View.GONE);
                            Submitemployerlogin.setVisibility(View.GONE);
                            bottomNavigationViewemployer.setVisibility(View.VISIBLE);
                            businessname = employeruser.getBusinessName();
                            returnemployer.setVisibility(View.GONE);
                            loadFragment(R.id.framedisplay,homeemployer);
                            clearEditTextFields();
                            showNotification("Please check your applications");
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"this user is not exists",Toast.LENGTH_LONG).show();
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this,"failed",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
        bottomNavigationViewemployer.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_home:
                       loadFragment(R.id.framedisplay,homeemployer);
                       return true;
                    case R.id.menu_profile:
                        loadFragment(R.id.framedisplay,profileemployer);
                        return true;
                    case R.id.menu_logout:
                        removeFragment(homeemployer);
                        removeFragment(profileemployer);
                        bottomNavigationViewemployer.setVisibility(View.GONE);
                        loginemployer.setVisibility(View.VISIBLE);
                        signupemployer.setVisibility(View.VISIBLE);
                        returnfirst.setVisibility(View.VISIBLE);
                        homeemployer = new HomeEmployer();
                        profileemployer = new ProfileEmployer();
                        return true;


                }

                return false;
            }
        });
    }
     public Void addEmployee() {
        DatabaseReference myRef = database.getReference("employees").push();
        String email = Email.getText().toString(),fullname = FullName.getText().toString(),city = City.getText().toString(),password = Password.getText().toString(),age = Age.getText().toString(),gender = Gender.getText().toString();
        Employee employee = new Employee(email,fullname,gender,age,city,password);  
        myRef.setValue(employee);
        return null;
    }
    public Void addEmployer() {
        DatabaseReference myRef = database.getReference("employers").push();
        String email = Email.getText().toString(),fullname = FullName.getText().toString(),city = City.getText().toString(),businessemployer = Businessemployer.getText().toString(),roleemployer = Roleemployer.getText().toString(),password = Password.getText().toString(),phonenumberstr= PhoneNumber.getText().toString();
        int phonenumber = Integer.parseInt(phonenumberstr);
        Employer employer = new Employer(email,fullname,city,businessemployer,roleemployer,password,phonenumber);
        myRef.setValue(employer);
        return null;
    }
    public Void addEmployerInfo() {
        DatabaseReference myRef = database.getReference("employersinfo").push();
         String fullname = FullName.getText().toString(),city = City.getText().toString(),businessemployer = Businessemployer.getText().toString(),roleemployer = Roleemployer.getText().toString(),phonenumberstr= PhoneNumber.getText().toString();
        int phonenumber = Integer.parseInt(phonenumberstr);
        EmployerInfo employerinfo = new EmployerInfo(fullname,city,businessemployer,roleemployer,phonenumber);
        myRef.setValue(employerinfo);
        return null;
    }
    public Employee getEmployee(){
        return employeeuser;
    }
    public Employer getEmployer(){
        return employeruser;
    }
    public String getBusinessname(){
        return businessname;
    }
    private void loadFragment(int sourceId, Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(sourceId,fragment);
        fragmentTransaction.commit();
    }
    private void removeFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }
    private void clearEditTextFields() {
        Email.getText().clear();
        FullName.getText().clear();
        City.getText().clear();
        Password.getText().clear();
        Businessemployer.getText().clear();
        Roleemployer.getText().clear();
        PhoneNumber.getText().clear();
        Age.getText().clear();
        Gender.getText().clear();
    }
}