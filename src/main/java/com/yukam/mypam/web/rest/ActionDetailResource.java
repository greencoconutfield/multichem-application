package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.ActionDetail;
import com.yukam.mypam.repository.ActionDetailRepository;
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
 * REST controller for managing ActionDetail.
 */
@RestController
@RequestMapping("/app")
public class ActionDetailResource {

    private final Logger log = LoggerFactory.getLogger(ActionDetailResource.class);

    @Inject
    private ActionDetailRepository actionDetailRepository;

    /**
     * POST  /rest/units -> Create a new actionDetail.
     */
    @RequestMapping(value = "/rest/actiondetails",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void create(@RequestBody ActionDetail actionDetail) {
        log.debug("REST request to save actionDetail : {}", actionDetail);
        actionDetailRepository.save(actionDetail);
    }

    /**
     * GET  /rest/units -> get all the units.
     */
    @RequestMapping(value = "/rest/actiondetails",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public List<ActionDetail> getAll() {
        log.debug("REST request to get all actionDetails");
        return actionDetailRepository.findAll();
    }

    /**
     * GET  /rest/units/:id -> get the "id" actionDetails.
     */
    @RequestMapping(value = "/rest/actiondetails/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public ResponseEntity<ActionDetail> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get actionDetail : {}", id);
        ActionDetail actionDetail = actionDetailRepository.findOne(id);
        if (actionDetail == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(actionDetail, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/units/:id -> delete the "id" actionDetail.
     */
    @RequestMapping(value = "/rest/actiondetails/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete actionDetail : {}", id);
        actionDetailRepository.delete(id);
    }
}
