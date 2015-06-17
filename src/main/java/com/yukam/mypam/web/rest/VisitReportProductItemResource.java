package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.VisitReportProductItem;
import com.yukam.mypam.repository.VisitReportProductItemRepository;
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
 * REST controller for managing VisitReportProductItem.
 */
@RestController
@RequestMapping("/app")
public class VisitReportProductItemResource {

    private final Logger log = LoggerFactory.getLogger(VisitReportProductItemResource.class);

    @Inject
    private VisitReportProductItemRepository visitReportProductItemRepository;

    /**
     * POST  /rest/units -> Create a new visitReportProductItem.
     */
    @RequestMapping(value = "/rest/visitreportproductitems",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void create(@RequestBody VisitReportProductItem visitReportProductItem) {
        log.debug("REST request to save visitReportProductItem : {}", visitReportProductItem);
        visitReportProductItemRepository.save(visitReportProductItem);
    }

    /**
     * GET  /rest/units -> get all the visitReportProductItems.
     */
    @RequestMapping(value = "/rest/visitreportproductitems",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public List<VisitReportProductItem> getAll() {
        log.debug("REST request to get all visitReportProductItems");
        return visitReportProductItemRepository.findAll();
    }

    /**
     * GET  /rest/units/:id -> get the "id" visitReportProductItems.
     */
    @RequestMapping(value = "/rest/visitreportproductitems/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public ResponseEntity<VisitReportProductItem> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get visitReportProductItem : {}", id);
        VisitReportProductItem unit = visitReportProductItemRepository.findOne(id);
        if (unit == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(unit, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/units/:id -> delete the "id" visitReportProductItem.
     */
    @RequestMapping(value = "/rest/visitreportproductitems/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete visitReportProductItem : {}", id);
        visitReportProductItemRepository.delete(id);
    }
}
