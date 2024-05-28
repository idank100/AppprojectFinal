package com.example.appproject;

public class EmployerInfo {

    private String  FullName;
    private String  City;
    private String  BusinessName;
    private String Role;
    private int Phonenumber;
    public EmployerInfo(String FullName,String City,String BusinessName, String Role,int Phonenumber)
    {

        this.FullName = FullName;
        this.City = City;
        this.BusinessName = BusinessName;
        this.Role = Role;
        this.Phonenumber = Phonenumber;
    }
    public EmployerInfo()
    {

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
    public int getPhonenumber() {
        return this.Phonenumber;
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
    public void setPhonenumber(int phonenumber) {
        this.Phonenumber = phonenumber;
    }
}
