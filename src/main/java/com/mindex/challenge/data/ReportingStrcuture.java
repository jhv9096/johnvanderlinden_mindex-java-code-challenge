package com.mindex.challenge.data;

import com.mindex.challenge.data.Employee;

public class ReportingStrcuture {

    //An employee who we need to number of reports for.
    private Employee employee;

    /*
    The total number of reports this employee receives.
    This will include reports sent to those whom this employee receives reports from.
     */
    private int numberOfReports;

    /*
    Returns the employee as an Employee object.
     */
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getNumberOfReports() {
        updateNumberOfReports(); //We run this everytime to ensure we return an up-to-date-value.
        return numberOfReports;
    }

    protected void updateNumberOfReports() {
        numberOfReports = 0;
        //If the employee we are looking at does not have any direct reports.
        if(employee.getDirectReports() == null || employee.getDirectReports().size() == 0) {
            numberOfReports = 0; //We don't change teh value at all
        }
        else {
            for(Employee reportee : employee.getDirectReports()) {
                //Add 1 report for the current employee, as well as however many reports
                //they receive as well.
                numberOfReports += (1 + reportee.getDirectReports().size());
            }
        }

    }
}
