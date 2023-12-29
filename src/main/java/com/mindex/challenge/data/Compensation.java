package com.mindex.challenge.data;

import com.mindex.challenge.data.Employee;
public class Compensation {
    private String employeeId;
    double salary;
    String effectiveDate;

    public Compensation() {

    }

//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }

    public String getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(String id) {
        employeeId = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
