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

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private ReportingStructureRepository reportingStructureRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure read(String employeeId) {
        LOG.debug("Reading reporting structure whose employee has id [{}]", employeeId);

        Employee requestedEmployee = employeeRepository.findByEmployeeId(employeeId); //Gets us the employee we need.
        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(requestedEmployee);

        //We now have our requested employee, with all their info filled out.
        //We also have the employee IDs of everyone who reports to them.
        //We need to fill out the missing info for the aforementioned employees,
        //as well as the info for whoever reports to them, if anyone.
        fillInMissingDirectReportingData(requestedEmployee);

        return reportingStructure;
    }

    private void fillInMissingDirectReportingData(Employee employee) {
        for(int i = 0; i < employee.getDirectReports().size(); i++) {
            //Employee reporteeIncomplete = employee.getDirectReports().get(i);
            String reporteeID = employee.getDirectReports().get(i).getEmployeeId();
            employee.getDirectReports().set(i, employeeRepository.findByEmployeeId(reporteeID));

            if(employee.getDirectReports().get(i).getDirectReports() != null) { //Does our newly added employee have reports they receive?
                fillInMissingDirectReportingData(employee.getDirectReports().get(i));
            }
        }
    }


}
