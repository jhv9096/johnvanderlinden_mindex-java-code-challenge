package com.mindex.challenge.service.impl;

import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

public class CompensationServiceImpl implements CompensationService {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);
    @Autowired
    private CompensationRepository compensationRepository;

    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating Compensation [{}]", compensation);

        compensationRepository.insert(compensation);

        return compensation;
    }

    @Override
    public Compensation read(String employeeID) {
        LOG.debug("Reading compensation for employee with ID [{}]", employeeID);

        Compensation compensation = compensationRepository.findCompensationWithEmployeeID(employeeID);
        if(compensation == null) {
            throw new RuntimeException("Unable to find compensation for the employee with ID: " + employeeID + ". Please ensure a valid ID was entered.");
        }

        return compensation;
    }
}
