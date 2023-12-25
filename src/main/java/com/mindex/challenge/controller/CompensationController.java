package com.mindex.challenge.controller;

import com.mindex.challenge.service.CompensationService;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mindex.challenge.data.Compensation;

@RestController
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compensationService;

    @PostMapping("/compensation")
    public Compensation create(@RequestBody Compensation compensation) {
        LOG.debug("Received compensation create for [{}]", compensation);

        return compensationService.create(compensation);
    }

    @GetMapping("/compensation/{employeeID}")
    public Compensation read(@PathVariable String employeeID) {
        LOG.debug("Received compensation read request for employee with ID [{}]", employeeID);

        return compensationService.read(employeeID);
    }
}
