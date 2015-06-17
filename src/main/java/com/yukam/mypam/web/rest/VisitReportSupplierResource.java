package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.VisitReportSupplier;
import com.yukam.mypam.repository.VisitReportSupplierRepository;
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
 * REST controller for managing VisitReportSupplier
 */
@RestController
@RequestMapping("/app")
public class VisitReportSupplierResource {

    private final Logger log = LoggerFactory.getLogger(VisitReportSupplierResource.class);

    @Inject
    private VisitReportSupplierRepository visitReportSupplierRepository;

    /**
     * POST  /rest/visitreportsuppliers -> Create a new supplier.
     */
    @RequestMapping(value = "/rest/visitreportsuppliers",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void create(@RequestBody VisitReportSupplier supplier) {
        log.debug("REST request to save VisitReportSupplier : {}", supplier);
//        if (supplier.getContact() != null && supplier.getContact().getAddresses() != null) {
//            for (Address address : supplier.getContact().getAddresses()) {
//                address.setContact(supplier.getContact());
//            }
//        }
        visitReportSupplierRepository.save(supplier);
    }

    /**
     * GET  /rest/visitreportsuppliers -> get all the visitreportsuppliers.
     */
    @RequestMapping(value = "/rest/visitreportsuppliers",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public List<VisitReportSupplier> getAll() {
        log.debug("REST request to get all VisitReportSuppliers");
        return visitReportSupplierRepository.findAll();
    }

    /**
     * GET  /rest/visitreportsuppliers/:id -> get the "id" supplier.
     */
    @RequestMapping(value = "/rest/visitreportsuppliers/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public ResponseEntity<VisitReportSupplier> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get VisitReportSupplier : {}", id);
        VisitReportSupplier supplier = visitReportSupplierRepository.findOne(id);
        if (supplier == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(supplier, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/visitreportsuppliers/:id -> delete the "id" supplier.
     */
    @RequestMapping(value = "/rest/visitreportsuppliers/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete VisitReportSupplier : {}", id);
            visitReportSupplierRepository.delete(id);

    }
}
