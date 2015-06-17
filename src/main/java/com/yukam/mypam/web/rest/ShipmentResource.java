package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.Shipment;
import com.yukam.mypam.repository.ShipmentRepository;
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
 * REST controller for managing Shipment.
 */
@RestController
@RequestMapping("/app")
public class ShipmentResource {

    private final Logger log = LoggerFactory.getLogger(ShipmentResource.class);

    @Inject
    private ShipmentRepository shipmentRepository;

    /**
     * POST  /rest/shipments -> Create a new shipment.
     */
    @RequestMapping(value = "/rest/shipments",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Shipment shipment) {
        log.debug("REST request to save Shipment : {}", shipment);
        shipmentRepository.save(shipment);
    }

    /**
     * GET  /rest/shipments -> get all the shipments.
     */
    @RequestMapping(value = "/rest/shipments",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Shipment> getAll() {
        log.debug("REST request to get all Shipments");
        return shipmentRepository.findAll();
    }

    /**
     * GET  /rest/shipments/:id -> get the "id" shipment.
     */
    @RequestMapping(value = "/rest/shipments/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Shipment> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Shipment : {}", id);
        Shipment shipment = shipmentRepository.findOne(id);
        if (shipment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(shipment, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/shipments/:id -> delete the "id" shipment.
     */
    @RequestMapping(value = "/rest/shipments/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Shipment : {}", id);
        shipmentRepository.delete(id);
    }
}
