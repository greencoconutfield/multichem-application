package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.Employee;
import com.yukam.mypam.repository.EmployeeRepository;
import com.yukam.mypam.security.AuthoritiesConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * REST controller for managing Employee.
 */
@RestController
@RequestMapping("/app")
public class EmployeeResource {

    private final Logger log = LoggerFactory.getLogger(EmployeeResource.class);

    @Inject
    private EmployeeRepository employeeRepository;

    /**
     * POST  /rest/units -> Create a new employee.
     */
    @RequestMapping(value = "/rest/employees",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public void create(@RequestBody Employee employee) {
        log.debug("REST request to save employee : {}", employee);
        employeeRepository.save(employee);
    }

    /**
     * GET  /rest/units -> get all the employee.
     */
    @RequestMapping(value = "/rest/employees",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public List<Employee> getAll() {
        log.debug("REST request to get all employees");
        return employeeRepository.findAll();
    }

    /**
     * GET  /rest/units/:id -> get the "id" employee.
     */
    @RequestMapping(value = "/rest/employees/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public ResponseEntity<Employee> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get employee : {}", id);
        Employee employee = employeeRepository.findOne(id);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/units/:id -> delete the "id" employee.
     */
    @RequestMapping(value = "/rest/employees/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete employee : {}", id);
        employeeRepository.delete(id);
    }
}
