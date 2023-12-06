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

    @Override
    public ReportingStructure create(ReportingStructure reportingStructure, Employee employee) {
        LOG.debug("Creating reporting structure [{}] for employee [{}]", reportingStructure, employee);

        reportingStructure.setEmployee(employee);
        reportingStructureRepository.insert(reportingStructure);

        return reportingStructure;
    }

    @Override
    public ReportingStructure read(String employeeId) {
        LOG.debug("Reading reporting structure whose employee has id [{}]", employeeId);

        ReportingStructure reportingStructure = reportingStructureRepository.findByEmployeeId(employeeId);
        if (reportingStructure == null) {
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }

        return reportingStructure;
    }

    @Override
    public ReportingStructure update(ReportingStructure reportingStructure, Employee employee) {
        LOG.debug("Updating reporting structure [{}] for employee [{}]", reportingStructure, employee);

        return reportingStructureRepository.save(reportingStructure);
    }


}
