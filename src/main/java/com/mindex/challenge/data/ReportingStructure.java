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

    /*
    Returns the number of reports the employee is receiving.
     */
    public int getNumberOfReports() {
        numberOfReports = tallyReports(employee); //We run this everytime to ensure we return an up-to-date value
        return numberOfReports;
    }

    /*
    Returns the employee ID of the employee.
     */
    public String getEmployeeId() {
        return employee.getEmployeeId();
    }

    /*
    Sets the employee ID of the employee
     */
    public void setEmployeeID(String employeeId) {
        employee.setEmployeeId(employeeId);
    }

    /*
    Helper method to update the number of reports the employee receives.
    The number of reports is not persisted, so we have to update the value
    every time we request the number of reports.
     */
//    protected int updateNumberOfReports() {
//        numberOfReports = 0;
//
//        if(employee.getDirectReports() != null) {
//            for(Employee reportee : employee.getDirectReports()) {
//                //Add 1 report for the current reportee, as well as however many reports
//                //the reportee receives as well.
//                numberOfReports += (1 + reportee.getDirectReports().size());
//            }
//        }
//
//        return numberOfReports;
//    }

    private int tallyReports(Employee employee) {
        int talliedReports = 0;
        if(employee.getDirectReports() == null)
            return talliedReports; //This employee doesn't receive any reports.
        for (Employee reportee: employee.getDirectReports()) {
            talliedReports += 1 + tallyReports(reportee);
        }
        return talliedReports;
    }
}
