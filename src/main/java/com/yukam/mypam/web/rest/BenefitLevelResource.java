package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.BenefitLevel;
import com.yukam.mypam.repository.BenefitLevelRepository;
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
 * REST controller for managing BenefitLevel.
 */
@RestController
@RequestMapping("/app")
public class BenefitLevelResource {

    private final Logger log = LoggerFactory.getLogger(BenefitLevelResource.class);

    @Inject
    private BenefitLevelRepository benefitLevelRepository;

    /**
     * POST  /rest/benefitlevels -> Create a new benefitlevel.
     */
    @RequestMapping(value = "/rest/benefitlevels",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public void create(@RequestBody BenefitLevel benefitLevel) {
        log.debug("REST request to save benefitLevel : {}", benefitLevel);
        benefitLevelRepository.save(benefitLevel);
    }

    /**
     * GET  /rest/benefitlevels -> get all the benefitlevels.
     */
    @RequestMapping(value = "/rest/benefitlevels",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public List<BenefitLevel> getAll() {
        log.debug("REST request to get all benefitlevels");
        return benefitLevelRepository.findAll();
    }

    /**
     * GET  /rest/benefitlevels/:id -> get the "id" benefitlevels.
     */
    @RequestMapping(value = "/rest/benefitlevels/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public ResponseEntity<BenefitLevel> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get IndustrySector : {}", id);
        BenefitLevel benefitlevel = benefitLevelRepository.findOne(id);
        if (benefitlevel == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(benefitlevel, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/benefitlevels/:id -> delete the "id" benefitlevel.
     */
    @RequestMapping(value = "/rest/benefitlevels/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete benefitlevel : {}", id);
        benefitLevelRepository.delete(id);
    }
}
