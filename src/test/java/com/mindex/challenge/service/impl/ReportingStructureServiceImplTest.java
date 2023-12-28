package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {
    private String reportingStructureURL;
    private String reportingStructureEmployeeIDURL;
    private String employeeIDURL;

    private final String testEmployeeID = "16a596ae-edd3-4847-99fe-c4518e82c86f"; //The employee ID for John Lennon

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        reportingStructureURL = "http://localhost:" + port + "/reportingstructure";
        reportingStructureEmployeeIDURL = "http://localhost:" + port + "/reportingstructure/{id}";

        //Employee URL to get our initial employee for the report.
        employeeIDURL = "http://localhost:" + port + "/employee/{id}";
    }

    @Test
    public void testRead() {
        //Reporting structure only required a read REST endpoint.
        ReportingStructure testReportingStructure = new ReportingStructure();
        Employee testEmployee = restTemplate.getForEntity(employeeIDURL, Employee.class, testEmployeeID).getBody();

        testReportingStructure.setEmployee(testEmployee);

        // Read checks
        ReportingStructure readReportingStructure = restTemplate.getForEntity(reportingStructureEmployeeIDURL, ReportingStructure.class, testEmployeeID).getBody();
        assertNotNull(readReportingStructure);
        assertEquals(4, readReportingStructure.getNumberOfReports());
    }

}
