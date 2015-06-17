package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.Unit;
import com.yukam.mypam.repository.UnitRepository;
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
public class UnitResource {

    private final Logger log = LoggerFactory.getLogger(UnitResource.class);

    @Inject
    private UnitRepository unitRepository;

    /**
     * POST  /rest/units -> Create a new unit.
     */
    @RequestMapping(value = "/rest/units",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void create(@RequestBody Unit unit) {
        log.debug("REST request to save unit : {}", unit);
        unitRepository.save(unit);
    }

    /**
     * GET  /rest/units -> get all the units.
     */
    @RequestMapping(value = "/rest/units",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public List<Unit> getAll() {
        log.debug("REST request to get all units");
        return unitRepository.findAll();
    }

    /**
     * GET  /rest/units/:id -> get the "id" units.
     */
    @RequestMapping(value = "/rest/units/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public ResponseEntity<Unit> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Unit : {}", id);
        Unit unit = unitRepository.findOne(id);
        if (unit == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(unit, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/units/:id -> delete the "id" unit.
     */
    @RequestMapping(value = "/rest/units/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete unit : {}", id);
        unitRepository.delete(id);
    }
}
