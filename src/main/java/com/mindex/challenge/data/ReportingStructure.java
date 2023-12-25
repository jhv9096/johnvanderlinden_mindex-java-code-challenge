package com.mindex.challenge.data;

import com.mindex.challenge.data.Employee;

public class ReportingStructure {

    //An employee who we need the number of reports for.
    private Employee employee;

    /*
    The total number of reports this employee receives.
    This will include reports sent to those whom this employee receives reports from.
     */
    private int numberOfReports;

    public ReportingStructure() {

    }

    /*
    Returns the employee as an Employee object.
     */
    public Employee getEmployee() {
        return employee;
    }

    /*
    Sets the employee for the Reporting Structure
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getNumberOfReports() {
        updateNumberOfReports(); //We run this everytime to ensure we return an up-to-date value.
        return numberOfReports;
    }

    public String getEmployeeID() {
        return employee.getEmployeeId();
    }

    public void setEmployeeID(String id) {
        employee.setEmployeeId(id);
    }

    protected void updateNumberOfReports() {
        numberOfReports = 0;

        //If an employee has no direct reports, this for loop doesn't run
        for(Employee reportee : employee.getDirectReports()) {
            //Add 1 report for the current reportee, as well as however many reports
            //the reportee receives as well.
            numberOfReports += (1 + reportee.getDirectReports().size());
        }
    }
}
