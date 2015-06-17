package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.PreOrder;
import com.yukam.mypam.repository.PreOrderRepository;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * REST controller for managing PreOrder.
 */
@RestController
@RequestMapping("/app")
public class PreOrderResource {

    private final Logger log = LoggerFactory.getLogger(PreOrderResource.class);

    @Inject
    private PreOrderRepository preorderRepository;

    /**
     * POST  /rest/preorders -> Create a new preorder.
     */
    @RequestMapping(value = "/rest/preorders",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody PreOrder preorder) {
        log.debug("REST request to save PreOrder : {}", preorder);
        if (preorder.getCreatedByUser() == null) {
            preorder.setCreatedByUser("system");
        }
        if (preorder.getCreatedDate() == null) {
            preorder.setCreatedDate(new LocalDate());
        }
        preorderRepository.save(preorder);
    }

    /**
     * GET  /rest/preorders -> get all the preorders.
     */
    @RequestMapping(value = "/rest/preorders",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<PreOrder> getAll() {
        log.debug("REST request to get all PreOrders");
        return preorderRepository.findAll();
    }

    /**
     * GET  /rest/preorders/:id -> get the "id" preorder.
     */
    @RequestMapping(value = "/rest/preorders/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PreOrder> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get PreOrder : {}", id);
        PreOrder preorder = preorderRepository.findOne(id);
        if (preorder == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(preorder, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/preorders/:id -> delete the "id" preorder.
     */
    @RequestMapping(value = "/rest/preorders/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete PreOrder : {}", id);
        preorderRepository.delete(id);
    }
}
