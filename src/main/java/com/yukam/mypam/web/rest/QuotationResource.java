package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.ProductPriceHistory;
import com.yukam.mypam.domain.Quotation;
import com.yukam.mypam.domain.QuotationItemDetail;
import com.yukam.mypam.repository.ProductPriceHistoryRepository;
import com.yukam.mypam.repository.QuotationRepository;
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
public class QuotationResource {

    private final Logger log = LoggerFactory.getLogger(QuotationResource.class);

    @Inject
    private QuotationRepository quotationRepository;

    @Inject
    private ProductPriceHistoryRepository productPriceHistoryRepository;

    /**
     * POST  /rest/quotations -> Create a new quotation.
     */
    @RequestMapping(value = "/rest/quotations",
            method = RequestMethod.POST,
            produces = "application/json")
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public void create(@RequestBody Quotation quotation) {
        log.debug("REST request to save Quotation : {}", quotation);
        quotationRepository.save(quotation);

        for(QuotationItemDetail quotationItemDetail: quotation.getQuotationItemDetails()){
            ProductPriceHistory productPriceHistory = new ProductPriceHistory();
            productPriceHistory.setProduct_id(quotationItemDetail.getProduct().getId());
            productPriceHistory.setCurrency(quotationItemDetail.getProduct().getPrice().getCurrency());
            productPriceHistory.setValue(quotationItemDetail.getUnitPrice());
            productPriceHistory.setExpiredDate(quotationItemDetail.getProduct().getPrice().getExpiredDate());
            productPriceHistory.setLastUpdate(quotationItemDetail.getProduct().getPrice().getLastUpdate());
            productPriceHistory.setPricingType(quotationItemDetail.getProduct().getPrice().getPricingType());
            productPriceHistory.setCustomer_id(quotation.getCustomer().getId());
            productPriceHistoryRepository.save(productPriceHistory);
        }
    }

    /**
     * GET  /rest/quotations -> get all the quotations.
     */
    @RequestMapping(value = "/rest/quotations",
            method = RequestMethod.GET,
            produces = "application/json")
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public List<Quotation> getAll() {
        log.debug("REST request to get all Quotations");
        return quotationRepository.findAll();
    }

    /**
     * GET  /rest/quotations/:id -> get the "id" quotation.
     */
    @RequestMapping(value = "/rest/quotations/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public ResponseEntity<Quotation> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Quotation : {}", id);
        Quotation quotation = quotationRepository.findOne(id);
        if (quotation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(quotation, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/quotations/:id -> delete the "id" quotation.
     */
    @RequestMapping(value = "/rest/quotations/{id}",
            method = RequestMethod.DELETE,
            produces = "application/json")
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Quotation : {}", id);
        quotationRepository.delete(id);
    }
}
