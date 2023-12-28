package com.mindex.challenge.service.impl;


import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.dao.ReportingStructureRepository;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private ReportingStructureRepository reportingStructureRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

//    @Override
//    public ReportingStructure create(ReportingStructure reportingStructure) {
//        LOG.debug("Creating reporting structure [{}]", reportingStructure);
//
//        //reportingStructure.setEmployee(employee);
//        reportingStructureRepository.insert(reportingStructure);
//
//        return reportingStructure;
//    }

    @Override
    public ReportingStructure read(String employeeId) {
        LOG.debug("Reading reporting structure whose employee has id [{}]", employeeId);

//        ReportingStructure reportingStructure = reportingStructureRepository.findByEmployeeId(employeeId);
//        if (reportingStructure == null) {
//            throw new RuntimeException("Invalid employeeId: " + employeeId);
//        }
//
//        return reportingStructure;

        Employee requestedEmployee = employeeRepository.findByEmployeeId(employeeId); //Gets us the employee we need.
        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(requestedEmployee);

        //We now have our requested employee, with all their info filled out.
        //We also have the employee IDs of everyone who reports to them.
        //We need to fill out the missing info for the aforementioned employees,
        //as well as the info for whoever reports to them, if anyone.
        fillInMissingDirectReportingData(requestedEmployee);

        System.out.println("\n\n\n\nDirect Report being \"read\":\n" + reportingStructure.getEmployeeId() + "\n" + reportingStructure.getNumberOfReports() + "\n\n\n\n");

        return reportingStructure;
    }

//    @Override
//    public ReportingStructure update(ReportingStructure reportingStructure, Employee employee) {
//        LOG.debug("Updating reporting structure [{}] for employee [{}]", reportingStructure, employee);
//
//        return reportingStructureRepository.save(reportingStructure);
//    }

    private void fillInMissingDirectReportingData(Employee employee) {
        for(int i = 0; i < employee.getDirectReports().size(); i++) {
            //Employee reporteeIncomplete = employee.getDirectReports().get(i);
            String reporteeID = employee.getDirectReports().get(i).getEmployeeId();
            System.out.println("\n\n\n\nReporteeID#" + i + ": " + reporteeID + "\n\n\n\n");
            employee.getDirectReports().set(i, employeeRepository.findByEmployeeId(reporteeID));

            System.out.println("\n\n\n\nDirect Report Check First Name: " + employee.getDirectReports().get(i).getFirstName());
            System.out.println("Direct Report Check Last Name: " + employee.getDirectReports().get(i).getLastName());
            System.out.println("Direct Report Check Department: " + employee.getDirectReports().get(i).getDepartment());
            System.out.println("Direct Report Check Position: " + employee.getDirectReports().get(i).getPosition());
            System.out.println("Direct Report Check Direct Reports: " + employee.getDirectReports().get(i).getDirectReports());

            if(employee.getDirectReports().get(i).getDirectReports() != null) { //Does our newly added employee have reports they receive?
                fillInMissingDirectReportingData(employee.getDirectReports().get(i));
            }
        }
    }


}
