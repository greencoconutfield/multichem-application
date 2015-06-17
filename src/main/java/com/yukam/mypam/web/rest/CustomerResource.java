package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.Customer;
import com.yukam.mypam.repository.CustomerRepository;
import com.yukam.mypam.security.AuthoritiesConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * REST controller for managing Customer.
 */
@RestController
@RequestMapping("/app")
public class CustomerResource {

    private final Logger log = LoggerFactory.getLogger(CustomerResource.class);

    @Inject
    private CustomerRepository customerRepository;

    /**
     * POST  /rest/customers -> Create a new customer.
     */
    @RequestMapping(value = "/rest/customers",
            method = RequestMethod.POST,
            produces = "application/json")
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void create(@RequestBody Customer customer) {
        log.debug("REST request to save Customer : {}", customer);
//        if (customer.getContact() != null && customer.getContact().getAddresses() != null) {
//            for (Address address : customer.getContact().getAddresses()) {
//                address.setContact(customer.getContact());
//            }
//        }
        customerRepository.save(customer);
    }

    /**
     * GET  /rest/customers -> get all the customers.
     */
    @RequestMapping(value = "/rest/customers",
            method = RequestMethod.GET,
            produces = "application/json")
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public List<Customer> getAll() {
        log.debug("REST request to get all Customers");
        return customerRepository.findAll();
    }

    /**
     * GET  /rest/customers/:id -> get the "id" customer.
     */
    @RequestMapping(value = "/rest/customers/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public ResponseEntity<Customer> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Customer : {}", id);
        Customer customer = customerRepository.findOne(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/customers/:id -> delete the "id" customer.
     */
    @RequestMapping(value = "/rest/customers/{id}",
            method = RequestMethod.DELETE,
            produces = "application/json")
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Customer : {}", id);
        customerRepository.delete(id);
    }
}
