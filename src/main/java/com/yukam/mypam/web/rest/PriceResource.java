package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.Price;
import com.yukam.mypam.repository.PriceRepository;
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
public class PriceResource {

    private final Logger log = LoggerFactory.getLogger(PriceResource.class);

    @Inject
    private PriceRepository priceRepository;

    /**
     * POST  /rest/prices -> Create a new price.
     */
    @RequestMapping(value = "/rest/prices",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public void create(@RequestBody Price price) {
        log.debug("REST request to save price : {}", price);
        priceRepository.save(price);
    }

    /**
     * GET  /rest/units -> get all the units.
     */
    @RequestMapping(value = "/rest/prices",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public List<Price> getAll() {
        log.debug("REST request to get all prices");
        return priceRepository.findAll();
    }

    /**
     * GET  /rest/units/:id -> get the "id" units.
     */
    @RequestMapping(value = "/rest/prices/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public ResponseEntity<Price> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Price : {}", id);
        Price price = priceRepository.findOne(id);
        if (price == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(price, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/prices/:id -> delete the "id" price.
     */
    @RequestMapping(value = "/rest/prices/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete price : {}", id);
        priceRepository.delete(id);
    }
}
