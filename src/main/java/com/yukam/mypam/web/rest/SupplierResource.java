package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.Product;
import com.yukam.mypam.domain.Supplier;
import com.yukam.mypam.repository.SupplierRepository;
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
 * REST controller for managing Supplier.
 */
@RestController
@RequestMapping("/app")
public class SupplierResource {

    private final Logger log = LoggerFactory.getLogger(SupplierResource.class);

    @Inject
    private SupplierRepository supplierRepository;

    /**
     * POST  /rest/suppliers -> Create a new supplier.
     */
    @RequestMapping(value = "/rest/suppliers",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void create(@RequestBody Supplier supplier) {
        log.debug("REST request to save Supplier : {}", supplier);
//        if (supplier.getContact() != null && supplier.getContact().getAddresses() != null) {
//            for (Address address : supplier.getContact().getAddresses()) {
//                address.setContact(supplier.getContact());
//            }
//        }
        supplierRepository.save(supplier);
    }

    /**
     * GET  /rest/suppliers -> get all the suppliers.
     */
    @RequestMapping(value = "/rest/suppliers",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public List<Supplier> getAll() {
        log.debug("REST request to get all Suppliers");
        return supplierRepository.findAll();
    }

    /**
     * GET  /rest/suppliers/:id -> get the "id" supplier.
     */
    @RequestMapping(value = "/rest/suppliers/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public ResponseEntity<Supplier> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Supplier : {}", id);
        Supplier supplier = supplierRepository.findOne(id);
        if (supplier == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(supplier, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/suppliers/:id -> delete the "id" supplier.
     */
    @RequestMapping(value = "/rest/suppliers/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Supplier : {}", id);
        if(supplierRepository.getAssociatedProducts(id).size() <1){
            supplierRepository.delete(id);
        }

    }

    /**
     *   /rest/suppliers/:id -> delete the "id" supplier.
     */
    @RequestMapping(value = "/rest/supplierutil/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public List<Product> getAssociatedProducts(@PathVariable Long id) {
        log.debug("REST request to get associated products : {}", id);
        List<Product> listProducts = null;
        listProducts = supplierRepository.getAssociatedProducts(id);
        return listProducts;
    }
}
