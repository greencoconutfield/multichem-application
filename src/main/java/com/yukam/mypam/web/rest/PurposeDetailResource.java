package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.PurposeDetail;
import com.yukam.mypam.repository.PurposeDetailRepository;
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
 * REST controller for managing PurposeDetail.
 */
@RestController
@RequestMapping("/app")
public class PurposeDetailResource {

    private final Logger log = LoggerFactory.getLogger(PurposeDetailResource.class);

    @Inject
    private PurposeDetailRepository purposeDetailRepository;

    /**
     * POST  /rest/units -> Create a new purposeDetail.
     */
    @RequestMapping(value = "/rest/purposedetails",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public void create(@RequestBody PurposeDetail purposeDetail) {
        log.debug("REST request to save purposeDetail : {}", purposeDetail);
        purposeDetailRepository.save(purposeDetail);
    }

    /**
     * GET  /rest/units -> get all the units.
     */
    @RequestMapping(value = "/rest/purposedetails",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public List<PurposeDetail> getAll() {
        log.debug("REST request to get all purposeDetails");
        return purposeDetailRepository.findAll();
    }

    /**
     * GET  /rest/units/:id -> get the "id" units.
     */
    @RequestMapping(value = "/rest/purposedetails/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public ResponseEntity<PurposeDetail> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get purposeDetail : {}", id);
        PurposeDetail unit = purposeDetailRepository.findOne(id);
        if (unit == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(unit, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/units/:id -> delete the "id" unit.
     */
    @RequestMapping(value = "/rest/purposedetails/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete purposeDetail : {}", id);
        purposeDetailRepository.delete(id);
    }
}
