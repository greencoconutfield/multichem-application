package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.VisitReportProduct;
import com.yukam.mypam.repository.ProductPriceHistoryRepository;
import com.yukam.mypam.repository.VisitReportProductRepository;
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
 * REST controller for managing Product.
 */
@RestController
@RequestMapping("/app")
public class VisitReportProductResource {

    private final Logger log = LoggerFactory.getLogger(VisitReportProductResource.class);

    @Inject
    private VisitReportProductRepository visitReportProductRepository;

    @Inject
    private ProductPriceHistoryRepository productPriceHistoryRepository;

    /**
     * POST  /rest/products -> Create a new product.
     */
    @RequestMapping(value = "/rest/visitreportproducts",
            method = RequestMethod.POST,
            produces = "application/json")
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void create(@RequestBody VisitReportProduct visitReportProduct) {
        log.debug("REST request to save Product : {}", visitReportProduct);
        visitReportProductRepository.save(visitReportProduct);
    }

    /**
     * GET  /rest/products -> get all the products.
     */
    @RequestMapping(value = "/rest/visitreportproducts",
            method = RequestMethod.GET,
            produces = "application/json")
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public List<VisitReportProduct> getAll() {
        log.debug("REST request to get all Products");
        return visitReportProductRepository.findAll();
    }

    /**
     * GET  /rest/products/:id -> get the "id" product.
     */
    @RequestMapping(value = "/rest/visitreportproducts/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public ResponseEntity<VisitReportProduct> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Product : {}", id);
        VisitReportProduct visitReportProduct = visitReportProductRepository.findOne(id);
        if (visitReportProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(visitReportProduct, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/products/:id -> delete the "id" product.
     */
    @RequestMapping(value = "/rest/visitreportproducts/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Product : {}", id);
        visitReportProductRepository.delete(id);
    }
}
