package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.SubIndustrySector;
import com.yukam.mypam.repository.SubIndustrySectorRepository;
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
 * REST controller for managing SubIndustrySector.
 */
@RestController
@RequestMapping("/app")
public class SubIndustrySectorResource {

    private final Logger log = LoggerFactory.getLogger(SubIndustrySectorResource.class);

    @Inject
    private SubIndustrySectorRepository subIndustrysectorRepository;

    /**
     * POST  /rest/subindustrysectors -> Create a new subindustrysector.
     */
    @RequestMapping(value = "/rest/subindustrysectors",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public void create(@RequestBody SubIndustrySector subIndustrySectorector) {
        log.debug("REST request to save SubIndustrySector : {}", subIndustrySectorector);
//        if(industrysector.getParent() == null){
//            IndustrySector parentIndustrySector = industrysectorRepository.findOne(new Long(0));
//            industrysector.setParent(parentIndustrySector);
//        }
        subIndustrysectorRepository.save(subIndustrySectorector);
    }

    /**
     * GET  /rest/industrysectors -> get all the industrysectors.
     */
    @RequestMapping(value = "/rest/subindustrysectors",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public List<SubIndustrySector> getAll(boolean exceptRoot) {
        log.debug("REST request to get all SubIndustrySectors");
        List<SubIndustrySector> displayList = null;
        if(exceptRoot){
            displayList = subIndustrysectorRepository.getAllExceptRoot();
        }
        else{
            displayList = subIndustrysectorRepository.findAll();
        }

       // displayList.remove(0);
        return displayList;
    }

    /**
     * GET  /rest/industrysectors/:id -> get the "id" industrysector.
     */
    @RequestMapping(value = "/rest/subindustrysectors/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public ResponseEntity<SubIndustrySector> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get SubIndustrySector : {}", id);
        SubIndustrySector subIndustrySector = subIndustrysectorRepository.findOne(id);
        if (subIndustrySector == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(subIndustrySector, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/industrysectors/:id -> delete the "id" industrysector.
     */
    @RequestMapping(value = "/rest/subindustrysectors/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete SubIndustrySector : {}", id);
        subIndustrysectorRepository.delete(id);
    }
}
