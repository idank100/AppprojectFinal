package com.example.appproject;

public class JobApplication {
    private Employee employee;
    private String businessname ;
    private String reasonjob;

    public JobApplication(Employee employee,String businessname,String reasonjob)
    {
        this.employee = employee;
        this.businessname = businessname;
        this.reasonjob = reasonjob;

    }
    public JobApplication()
    {
    }
    public Employee getEmployee() {
        return this.employee;
    }
    public String getBusinessname() {
        return this.businessname;
    }
    public String getReasonjob() {
        return this.reasonjob;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public void setBusinessname(String businessname) {
        this.businessname = businessname;
    }
    public void setReasonjob(String reasonjob) {
        this.reasonjob = reasonjob;
    }
}
