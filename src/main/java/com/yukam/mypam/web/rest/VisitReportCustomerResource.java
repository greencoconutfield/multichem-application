package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.VisitReportCustomer;
import com.yukam.mypam.repository.VisitReportCustomerRepository;
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
 * REST controller for managing VisitReportCustomer.
 */
@RestController
@RequestMapping("/app")
public class VisitReportCustomerResource {

    private final Logger log = LoggerFactory.getLogger(VisitReportCustomerResource.class);

    @Inject
    private VisitReportCustomerRepository visitReportCustomerRepository;

    /**
     * POST  /rest/VisitReportCustomers -> Create a new visit report VisitReportCustomer.
     */
    @RequestMapping(value = "/rest/visitreportcustomers",
            method = RequestMethod.POST,
            produces = "application/json")
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void create(@RequestBody VisitReportCustomer visitReportCustomer) {
        log.debug("REST request to save VisitReportCustomer : {}", visitReportCustomer);
//        if (VisitReportCustomer.getContact() != null && VisitReportCustomer.getContact().getAddresses() != null) {
//            for (Address address : VisitReportCustomer.getContact().getAddresses()) {
//                address.setContact(VisitReportCustomer.getContact());
//            }
//        }
        visitReportCustomerRepository.save(visitReportCustomer);
    }

    /**
     * GET  /rest/VisitReportCustomers -> get all the VisitReportCustomers.
     */
    @RequestMapping(value = "/rest/visitreportcustomers",
            method = RequestMethod.GET,
            produces = "application/json")
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public List<VisitReportCustomer> getAll() {
        log.debug("REST request to get all VisitReportCustomers");
        return visitReportCustomerRepository.findAll();
    }

    /**
     * GET  /rest/VisitReportCustomers/:id -> get the "id" VisitReportCustomer.
     */
    @RequestMapping(value = "/rest/visitreportcustomers/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public ResponseEntity<VisitReportCustomer> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get VisitReportCustomer : {}", id);
        VisitReportCustomer VisitReportCustomer = visitReportCustomerRepository.findOne(id);
        if (VisitReportCustomer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(VisitReportCustomer, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/VisitReportCustomers/:id -> delete the "id" VisitReportCustomer.
     */
    @RequestMapping(value = "/rest/visitreportcustomers/{id}",
            method = RequestMethod.DELETE,
            produces = "application/json")
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete VisitReportCustomer : {}", id);
        visitReportCustomerRepository.delete(id);
    }
}
