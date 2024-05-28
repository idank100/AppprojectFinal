package com.example.appproject;

public class Employer {
    private String Email;
    private String  FullName;
    private String  City;
    private String  BusinessName;
    private String Role;
   private String Password;
   private int Phonenumber;

   public Employer(String Email,String FullName,String City,String BusinessName, String Role,String Password,int Phonenumber)
   {
       this.Email = Email;
       this.FullName = FullName;
       this.City = City;
       this.BusinessName = BusinessName;
       this.Role = Role;
       this.Password = Password;
       this.Phonenumber = Phonenumber;
   }
    public Employer() {

    }
    public String getEmail() {
        return this.Email;
    }
    public String getFullName() {
        return this.FullName;
    }
    public String getCity() {
        return this.City;
    }
    public String getBusinessName() {
        return this.BusinessName;
    }
    public String getRole() {
        return this.Role;
    }
    public String getPassword() {
        return this.Password;
    }

    public int getPhonenumber() {
        return this.Phonenumber;
    }
    public void setEmail(String email) {
        this.Email = email;
    }
    public void setFullName(String fullName) {
        this.FullName = fullName;
    }
    public void setCity(String city) {
        this.City = city;
    }
    public void setBusinessName(String businessName) {
        this.BusinessName = businessName;
    }
    public void setRole(String role) {
        this.Role = role;
    }
    public void setPassword(String password) {
        this.Password = password;
    }
    public void setPhonenumber(int phonenumber) {
        this.Phonenumber = phonenumber;
    }
}
