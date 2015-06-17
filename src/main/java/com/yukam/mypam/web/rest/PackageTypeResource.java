package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.PackageType;
import com.yukam.mypam.repository.PackageTypeRepository;
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
 * REST controller for managing PackageType.
 */
@RestController
@RequestMapping("/app")
public class PackageTypeResource {

    private final Logger log = LoggerFactory.getLogger(PackageTypeResource.class);

    @Inject
    private PackageTypeRepository packageTypeRepository;

    /**
     * POST  /rest/packagetypes -> Create a new packageType.
     */
    @RequestMapping(value = "/rest/packagetypes",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void create(@RequestBody PackageType packageType) {
        log.debug("REST request to save packageType : {}", packageType);
        packageTypeRepository.save(packageType);
    }

    /**
     * GET  /rest/packagetypes -> get all the packagetypes.
     */
    @RequestMapping(value = "/rest/packagetypes",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public List<PackageType> getAll() {
        log.debug("REST request to get all packageTypes");
        return packageTypeRepository.findAll();
    }

    /**
     * GET  /rest/packagetypes/:id -> get the "id" packagetypes.
     */
    @RequestMapping(value = "/rest/packagetypes/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public ResponseEntity<PackageType> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get PackageType : {}", id);
        PackageType packageType = packageTypeRepository.findOne(id);
        if (packageType == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(packageType, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/benefitlevels/:id -> delete the "id" benefitlevel.
     */
    @RequestMapping(value = "/rest/packagetypes/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete packageType : {}", id);
        packageTypeRepository.delete(id);
    }
}
