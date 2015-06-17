package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.QuotationItemDetail;
import com.yukam.mypam.repository.QuotationItemDetailRepository;
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
 * REST controller for managing Quotation.
 */
@RestController
@RequestMapping("/app")
public class QuotationItemDetailResource {

    private final Logger log = LoggerFactory.getLogger(QuotationItemDetailResource.class);

    @Inject
    private QuotationItemDetailRepository quotationItemDetailRepository;

    /**
     * POST  /rest/quotationitemdetails -> Create a new quotationitemdetail.
     */
    @RequestMapping(value = "/rest/quotationitemdetails",
            method = RequestMethod.POST,
            produces = "application/json")
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public void create(@RequestBody QuotationItemDetail quotationItemDetail) {
        log.debug("REST request to save QuotationItemDetail : {}", quotationItemDetail);
        quotationItemDetailRepository.save(quotationItemDetail);
    }

    /**
     * GET  /rest/quotationitemdetails -> get all the quotationitemdetails.
     */
    @RequestMapping(value = "/rest/quotationitemdetails",
            method = RequestMethod.GET,
            produces = "application/json")
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public List<QuotationItemDetail> getAll() {
        log.debug("REST request to get all QuotationItemDetails");
        return quotationItemDetailRepository.findAll();
    }

    /**
     * GET  /rest/quotationitemdetails/:id -> get the "id" quotationitemdetails.
     */
    @RequestMapping(value = "/rest/quotationitemdetails/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public ResponseEntity<QuotationItemDetail> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get QuotaionItemDetail : {}", id);
        QuotationItemDetail quotationItemDetail = quotationItemDetailRepository.findOne(id);
        if (quotationItemDetail == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(quotationItemDetail, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/quotationitemdetails/:id -> delete the "id" quotationitemdetails.
     */
    @RequestMapping(value = "/rest/quotationitemdetails/{id}",
            method = RequestMethod.DELETE,
            produces = "application/json")
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete QuotationItemDetail : {}", id);
        quotationItemDetailRepository.delete(id);
    }
}
