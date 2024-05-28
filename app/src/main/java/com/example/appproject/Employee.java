package com.example.appproject;

public class Employee {
    private String Email;
    private String FullName;
    private String Gender;
    private String Age;
    private String City;
    private String Password;
    public Employee(String Email,String FullName,String Gender,String Age,String City,String Password)
    {
        this.Email = Email;
        this.FullName = FullName;
        this.Gender = Gender;
        this.Age = Age;
        this.City = City;
        this.Password = Password;
    }
    public Employee() {

    }
    public String getEmail() {
        return this.Email;
    }
    public String getFullName() {
        return this.FullName;
    }
    public String getGender() {
        return this.Gender;
    }
    public String getAge() {
        return this.Age;
    }
    public String getCity() {
        return this.City;
    }
    public String getPassword() {
        return this.Password;
    }
    public void setEmail(String email) {
        this.Email = email;
    }
    public void setFullName(String fullName) {
        this.FullName = fullName;
    }
    public void setGender(String gender) {
        this.Gender = gender;
    }
    public void setAge(String age) {
        this.Age = age;
    }
    public void setCity(String city) {
        this.City = city;
    }
    public void setPassword(String password) {
        this.Password = password;
    }
}
