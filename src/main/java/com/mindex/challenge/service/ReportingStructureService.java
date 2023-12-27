package com.mindex.challenge.service;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.data.Employee;

public interface ReportingStructureService {

    ReportingStructure create(ReportingStructure reportingStructure);

    ReportingStructure read(String employeeId);

    ReportingStructure update(ReportingStructure reportingStructure, Employee employee);
}
