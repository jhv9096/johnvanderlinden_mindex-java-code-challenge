package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
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
public class CompensationServiceImplTest {
    private String employeeIDURL;
    private String compensationURL;
    private String compensationEmployeeIDURL;

    @Autowired
    private CompensationService compensationService;

    @LocalServerPort
    private int port;


    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compensationURL = "http://localhost:" + port + "/compensation";
        compensationEmployeeIDURL = "http://localhost:" + port + "/compensation/{id}";

        //Employee URL to get our initial employee for the compensation
        employeeIDURL = "http://localhost:" + port + "/employee/{id}";
    }

    @Test
    public void testCreateRead() {
        Compensation testCompensation = new Compensation();
        String testEmployeeID = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        //Employee testEmployee = restTemplate.getForEntity(employeeIDURL, Employee.class, testEmployeeID).getBody();

        //testCompensation.setEmployee(testEmployee);
        testCompensation.setSalary(22.73); //Arbitrary test value.
        testCompensation.setEffectiveDate("2025-12-31"); //Arbitrary test value.

        //Create checks
       // Compensation createdCompensation = restTemplate.postForEntity(compensationURL, testCompensation, Compensation.class).getBody();
//        assertNotNull(createdCompensation.getEmployee());
//        assertCompensationEquivalence(testCompensation, createdCompensation);

        //Read checks
//        Compensation readCompensation = restTemplate.getForEntity(compensationEmployeeIDURL, Compensation.class, testEmployeeID).getBody();
//        assertNotNull(readCompensation);
//        assertCompensationEquivalence(createdCompensation, readCompensation);

    }

    private static void assertCompensationEquivalence(Compensation expected, Compensation actual) {
        assertEquals(expected.getSalary(), actual.getSalary(), 0.01);
        assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());

        assertEquals(expected.getEmployeeId(), actual.getEmployeeId());
        assertEquals(expected.getEmployee().getFirstName(), actual.getEmployee().getFirstName());
        assertEquals(expected.getEmployee().getLastName(), actual.getEmployee().getLastName());
        assertEquals(expected.getEmployee().getPosition(), actual.getEmployee().getPosition());
        assertEquals(expected.getEmployee().getDepartment(), actual.getEmployee().getDepartment());
    }
}
