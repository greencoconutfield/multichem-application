package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.PricingType;
import com.yukam.mypam.repository.PricingTypeRepository;
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
 * REST controller for managing Unit.
 */
@RestController
@RequestMapping("/app")
public class PricingTypeResource {

    private final Logger log = LoggerFactory.getLogger(PricingTypeResource.class);

    @Inject
    private PricingTypeRepository pricingTypeRepository;

    /**
     * POST  /rest/pricingTypes -> Create a new unit.
     */
    @RequestMapping(value = "/rest/pricingtypes",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public void create(@RequestBody PricingType pricingType) {
        log.debug("REST request to save unit : {}", pricingType);
        pricingTypeRepository.save(pricingType);
    }

    /**
     * GET  /rest/units -> get all the units.
     */
    @RequestMapping(value = "/rest/pricingtypes",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public List<PricingType> getAll() {
        log.debug("REST request to get all units");
        return pricingTypeRepository.findAll();
    }

    /**
     * GET  /rest/units/:id -> get the "id" units.
     */
    @RequestMapping(value = "/rest/pricingtypes/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public ResponseEntity<PricingType> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Unit : {}", id);
        PricingType pricingType = pricingTypeRepository.findOne(id);
        if (pricingType == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pricingType, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/units/:id -> delete the "id" unit.
     */
    @RequestMapping(value = "/rest/pricingtypes/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete unit : {}", id);
        pricingTypeRepository.delete(id);
    }
}
