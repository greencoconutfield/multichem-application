package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.ActionDetail;
import com.yukam.mypam.domain.VisitReport;
import com.yukam.mypam.domain.VisitReportProductItem;
import com.yukam.mypam.repository.VisitReportProductItemRepository;
import com.yukam.mypam.repository.VisitReportRepository;
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
import java.util.Set;

/**
 * REST controller for managing VisitReport.
 */
@RestController
@RequestMapping("/app")
public class VisitReportResource {

    private final Logger log = LoggerFactory.getLogger(VisitReportResource.class);

    @Inject
    private VisitReportRepository visitReportRepository;

    @Inject
    private VisitReportProductItemRepository visitReportProductItemRepository;

    /**
     * POST  /rest/units -> Create a new visitReport.
     */
    @RequestMapping(value = "/rest/visitreports",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void create(@RequestBody VisitReport visitReport) {
        log.debug("REST request to save visitReport : {}", visitReport);
        Set<VisitReportProductItem> visitReportProductItems = visitReport.getVisitReportProductItems();
        Set<ActionDetail> actions = visitReport.getActions();

        visitReport.setVisitReportProductItems(null);
        visitReport.setActions(null);
        visitReportRepository.saveAndFlush(visitReport);

        visitReport.setVisitReportProductItems(visitReportProductItems);
        visitReport.setActions(actions);
        visitReportRepository.saveAndFlush(visitReport);
    }

    /**
     * GET  /rest/units -> get all the units.
     */
    @RequestMapping(value = "/rest/visitreports",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public List<VisitReport> getAll() {
        log.debug("REST request to get all visitReports");
        return visitReportRepository.findAll();
    }

    /**
     * GET  /rest/units/:id -> get the "id" units.
     */
    @RequestMapping(value = "/rest/visitreports/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public ResponseEntity<VisitReport> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get visitReport : {}", id);
        VisitReport visitReport = visitReportRepository.findOne(id);
        if (visitReport == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(visitReport, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/units/:id -> delete the "id" unit.
     */
    @RequestMapping(value = "/rest/visitreports/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete visitReport : {}", id);
        visitReportRepository.delete(id);
    }
}
