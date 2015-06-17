package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.ProductSector;
import com.yukam.mypam.repository.ProductSectorRepository;
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
 * REST controller for managing ProductSector.
 */
@RestController
@RequestMapping("/app")
public class ProductSectorResource {

    private final Logger log = LoggerFactory.getLogger(ProductSectorResource.class);

    @Inject
    private ProductSectorRepository productSectorRepository;

    /**
     * POST  /rest/productsectors -> Create a new productSector.
     */
    @RequestMapping(value = "/rest/productsectors",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void create(@RequestBody ProductSector productSector) {
        log.debug("REST request to save SubIndustrySector : {}", productSector);
        productSectorRepository.save(productSector);
    }

    /**
     * GET  /rest/productsectors -> get all the productsectors.
     */
    @RequestMapping(value = "/rest/productsectors",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public List<ProductSector> getAll(boolean exceptRoot) {
        log.debug("REST request to get all productSectors");
        return productSectorRepository.findAll();
    }

    /**
     * GET  /rest/industrysectors/:id -> get the "id" industrysector.
     */
    @RequestMapping(value = "/rest/productsectors/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public ResponseEntity<ProductSector> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get SubIndustrySector : {}", id);
        ProductSector productSector = productSectorRepository.findOne(id);
        if (productSector == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productSector, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/productsectors/:id -> delete the "id" productsectors.
     */
    @RequestMapping(value = "/rest/productsectors/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete productSectors : {}", id);
        productSectorRepository.delete(id);
    }
}
