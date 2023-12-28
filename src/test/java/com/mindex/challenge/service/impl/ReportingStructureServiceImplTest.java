package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
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

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ReportingStructureService reportingStructureService;

    @LocalServerPort
    private int port; //= 8888; //Test set to 8888 (port we are using for localhost)

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        reportingStructureURL = "http://localhost:" + port + "/reportingstructure";
        reportingStructureEmployeeIDURL = "http://localhost:" + port + "/reportingstructure/{id}";

        //Employee URL to get our initial employee for the report.
        employeeIDURL = "http://localhost:" + port + "/employee/{id}";

        System.out.println("\n\n\n\nServer Port: " + port + "\n\n\n\n");
    }

    @Test
    public void testRead() {
        //Reporting structure only required a read REST endpoint.
        ReportingStructure testReportingStructure = new ReportingStructure();
        Employee testEmployee = restTemplate.getForEntity(employeeIDURL, Employee.class, testEmployeeID).getBody();

        testReportingStructure.setEmployee(testEmployee);

        //Create checks
//        ReportingStructure createdReportingStructure = restTemplate.postForEntity(reportingStructureURL, testReportingStructure, ReportingStructure.class).getBody();
//        assertNotNull(createdReportingStructure);
//        assertReportingStructureEquivalence(testReportingStructure, createdReportingStructure);
        //int numReports = testReportingStructure.getNumberOfReports();

        // Read checks
        ReportingStructure readReportingStructure = restTemplate.getForEntity(reportingStructureEmployeeIDURL, ReportingStructure.class, testEmployeeID).getBody();
        //assertEquals(createdReportingStructure.getNumberOfReports(), readReportingStructure.getNumberOfReports());
        //assertReportingStructureEquivalence(createdReportingStructure, readReportingStructure);
        assertNotNull(readReportingStructure);
        assertEquals(4, readReportingStructure.getNumberOfReports());
    }

    private static void assertReportingStructureEquivalence(ReportingStructure expected, ReportingStructure actual) {
        assertEquals(expected.getNumberOfReports(), actual.getNumberOfReports());
        assertEquals(expected.getEmployee().getEmployeeId(), actual.getEmployee().getEmployeeId());
        assertEquals(expected.getEmployee().getFirstName(), actual.getEmployee().getFirstName());
        assertEquals(expected.getEmployee().getLastName(), actual.getEmployee().getLastName());
        assertEquals(expected.getEmployee().getPosition(), actual.getEmployee().getPosition());
        assertEquals(expected.getEmployee().getDepartment(), actual.getEmployee().getDepartment());

    }

}
