package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.ProductPriceHistory;
import com.yukam.mypam.repository.ProductPriceHistoryRepository;
import com.yukam.mypam.security.AuthoritiesConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import java.util.List;

/**
 * REST controller for managing Unit.
 */
@RestController
@RequestMapping("/app")
public class ProductPriceHistoryResource {

    private final Logger log = LoggerFactory.getLogger(ProductPriceHistoryResource.class);

    @Inject
    private ProductPriceHistoryRepository productPriceHistoryRepository;

//    /**
//     * POST  /rest/prices -> Create a new price.
//     */
//    @RequestMapping(value = "/rest/productpricehistories",
//            method = RequestMethod.POST,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    @RolesAllowed(AuthoritiesConstants.ADMIN)
//    @Timed
//    public void create(@RequestBody Price price) {
//        log.debug("REST request to save price : {}", price);
//        priceRepository.save(price);
//    }

    /**
     * GET  /rest/units -> get all the units.
     */
    @RequestMapping(value = "/rest/productpricehistories",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    @Timed
    public List<ProductPriceHistory> getAllPerProduct(Long customerId, Long productId) {
        log.debug("REST request to get all prices");
        List<ProductPriceHistory> productPriceHistories = null;
        if(customerId!=null) {
            productPriceHistories = productPriceHistoryRepository.getCustomerProductPriceHistory(customerId, productId);

        }
        else{
            productPriceHistories = productPriceHistoryRepository.getProductPriceHistory(productId);
        }
        return productPriceHistories;

    }

//    /**
//     * GET  /rest/units/:id -> get the "id" units.
//     */
//    @RequestMapping(value = "/rest/productpricehistories/{id}",
//            method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    @RolesAllowed(AuthoritiesConstants.ADMIN)
//    @Timed
//    public ResponseEntity<Price> get(@PathVariable Long id, HttpServletResponse response) {
//        log.debug("REST request to get Price : {}", id);
//        Price price = priceRepository.findOne(id);
//        if (price == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(price, HttpStatus.OK);
//    }

//    /**
//     * DELETE  /rest/prices/:id -> delete the "id" price.
//     */
//    @RequestMapping(value = "/rest/prices/{id}",
//            method = RequestMethod.DELETE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    @RolesAllowed(AuthoritiesConstants.ADMIN)
//    @Timed
//    public void delete(@PathVariable Long id) {
//        log.debug("REST request to delete price : {}", id);
//        priceRepository.delete(id);
//    }
}
