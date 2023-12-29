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

        testCompensation.setEmployeeId(testEmployeeID);
        testCompensation.setSalary(22.73); //Arbitrary test value.
        testCompensation.setEffectiveDate("2025-12-31"); //Arbitrary test value.

        System.out.println("\n\n\n");
        System.out.println("testCompensation salary check: " + testCompensation.getSalary());
        System.out.println("testCompensation date check: " + testCompensation.getEffectiveDate());
        System.out.println("readCompensation employeeID: " + testCompensation.getEmployeeId());
        System.out.println("\n\n\n");

        //Create checks
        Compensation createdCompensation = restTemplate.postForEntity(compensationURL, testCompensation, Compensation.class).getBody();

        System.out.println("\n\n\n");
        System.out.println("createdCompensation salary check: " + createdCompensation.getSalary());
        System.out.println("createdCompensation date check: " + createdCompensation.getEffectiveDate());
        System.out.println("readCompensation employeeID: " + createdCompensation.getEmployeeId());
        System.out.println("\n\n\n");

        assertNotNull(createdCompensation);
        assertCompensationEquivalence(testCompensation, createdCompensation);

        //Read checks
        Compensation readCompensation = restTemplate.getForEntity(compensationEmployeeIDURL, Compensation.class, testEmployeeID).getBody();

        System.out.println("\n\n\n");
        System.out.println("readCompensation salary check: " + readCompensation.getSalary());
        System.out.println("readCompensation date check: " + readCompensation.getEffectiveDate());
        System.out.println("readCompensation employeeID: " + readCompensation.getEmployeeId());
        System.out.println("\n\n\n");

        assertNotNull(readCompensation);
        assertCompensationEquivalence(createdCompensation, readCompensation);

    }

    private static void assertCompensationEquivalence(Compensation expected, Compensation actual) {
        assertEquals(expected.getSalary(), actual.getSalary(), 0.001); //should be less than one cent on the delta.
        assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
        assertEquals(expected.getEmployeeId(), actual.getEmployeeId());
    }
}
