package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.IndustrySector;
import com.yukam.mypam.domain.SubIndustrySector;
import com.yukam.mypam.repository.IndustrySectorRepository;
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
 * REST controller for managing IndustrySector.
 */
@RestController
@RequestMapping("/app")
public class IndustrySectorResource {

    private final Logger log = LoggerFactory.getLogger(IndustrySectorResource.class);

    @Inject
    private IndustrySectorRepository industrysectorRepository;

    @Inject
    private SubIndustrySectorRepository subIndustrysectorRepository;

    /**
     * POST  /rest/industrysectors -> Create a new industrysector.
     */
    @RequestMapping(value = "/rest/industrysectors",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public void create(@RequestBody IndustrySector industrysector) {
        log.debug("REST request to save IndustrySector : {}", industrysector);
//        if(industrysector.getParent() == null){
//            IndustrySector parentIndustrySector = industrysectorRepository.findOne(new Long(0));
//            industrysector.setParent(parentIndustrySector);
//        }
        industrysectorRepository.save(industrysector);
        SubIndustrySector subIndustrySector = new SubIndustrySector();
        subIndustrySector.setParent(industrysector);
        subIndustrySector.setName("****");
        subIndustrySector.setDescription(industrysector.getName());

        List<SubIndustrySector> subIndustrySectors = subIndustrysectorRepository.findRootSubIndustrySectorByParentId(industrysector.getId());
        if(subIndustrySectors.size()>0){
            subIndustrySector.setDescription(industrysector.getName());
        }
        subIndustrysectorRepository.save(subIndustrySector);
    }

    /**
     * GET  /rest/industrysectors -> get all the industrysectors.
     */
    @RequestMapping(value = "/rest/industrysectors",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public List<IndustrySector> getAll() {
        log.debug("REST request to get all IndustrySectors");
        List<IndustrySector> displayList = industrysectorRepository.findAll();
       // displayList.remove(0);
        return displayList;
    }

    /**
     * GET  /rest/industrysectors/:id -> get the "id" industrysector.
     */
    @RequestMapping(value = "/rest/industrysectors/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public ResponseEntity<IndustrySector> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get IndustrySector : {}", id);
        IndustrySector industrysector = industrysectorRepository.findOne(id);
        if (industrysector == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(industrysector, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/industrysectors/:id -> delete the "id" industrysector.
     */
    @RequestMapping(value = "/rest/industrysectors/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete IndustrySector : {}", id);
        List<SubIndustrySector> subIndustrySectors = subIndustrysectorRepository.findRootSubIndustrySectorByParentId(id);
        if(subIndustrySectors.size()>0){
            for(SubIndustrySector subIndustrySector: subIndustrySectors){
                subIndustrysectorRepository.delete(subIndustrySector.getId());
            }

        }

        industrysectorRepository.delete(id);
    }
}
