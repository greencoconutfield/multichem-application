package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.DefaultTaxDetail;
import com.yukam.mypam.repository.DefaultTaxDetailRepository;
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
 * REST controller for managing defaulttaxdetails.
 */
@RestController
@RequestMapping("/app")
public class DefaultTaxDetailResource {

    private final Logger log = LoggerFactory.getLogger(DefaultTaxDetailResource.class);

    @Inject
    private DefaultTaxDetailRepository defaultTaxDetailRepository;

    /**
     * POST  /rest/units -> Create a new employee.
     */
    @RequestMapping(value = "/rest/defaulttaxdetails",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public void create(@RequestBody DefaultTaxDetail defaultTaxDetail) {
        log.debug("REST request to save defaultTaxDetail : {}", defaultTaxDetail);
        defaultTaxDetailRepository.save(defaultTaxDetail);
    }

    /**
     * GET  /rest/units -> get all the employee.
     */
    @RequestMapping(value = "/rest/defaulttaxdetails",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public List<DefaultTaxDetail> getAll() {
        log.debug("REST request to get all defaulttaxdetails");
        return defaultTaxDetailRepository.findAll();
    }

    /**
     * GET  /rest/units/:id -> get the "id" employee.
     */
    @RequestMapping(value = "/rest/defaulttaxdetails/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public ResponseEntity<DefaultTaxDetail> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get defaultTaxDetail : {}", id);
        DefaultTaxDetail defaultTaxDetail = defaultTaxDetailRepository.findOne(id);
        if (defaultTaxDetail == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(defaultTaxDetail, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/units/:id -> delete the "id" defaulttaxdetails.
     */
    @RequestMapping(value = "/rest/defaulttaxdetails/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete defaulttaxdetails : {}", id);
        defaultTaxDetailRepository.delete(id);
    }
}
