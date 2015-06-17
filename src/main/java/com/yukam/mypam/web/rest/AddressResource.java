package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.Address;
import com.yukam.mypam.repository.AddressRepository;
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
 * REST controller for managing Address.
 */
@RestController
@RequestMapping("/app")
public class AddressResource {

    private final Logger log = LoggerFactory.getLogger(AddressResource.class);

    @Inject
    private AddressRepository addressRepository;

    /**
     * POST  /rest/addresss -> Create a new address.
     */
    @RequestMapping(value = "/rest/address",
            method = RequestMethod.POST,
            produces = "application/json")
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void create(@RequestBody Address address) {
        log.debug("REST request to save Address : {}", address);
        addressRepository.save(address);
    }

    /**
     * GET  /rest/addresss -> get all the addresss.
     */
    @RequestMapping(value = "/rest/address",
            method = RequestMethod.GET,
            produces = "application/json")
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public List<Address> getAll() {
        log.debug("REST request to get all Address");
        return addressRepository.findAll();
    }

    /**
     * GET  /rest/addresss/:id -> get the "id" address.
     */
    @RequestMapping(value = "/rest/address/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public ResponseEntity<Address> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Address : {}", id);
        Address address = addressRepository.findOne(id);
        if (address == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/addresss/:id -> delete the "id" address.
     */
    @RequestMapping(value = "/rest/address/{id}",
            method = RequestMethod.DELETE,
            produces = "application/json")
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Address : {}", id);
        addressRepository.delete(id);
    }
}
