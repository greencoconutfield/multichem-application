package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.Currency;
import com.yukam.mypam.repository.CurrencyRepository;
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
 * REST controller for managing Currency.
 */
@RestController
@RequestMapping("/app")
public class CurrencyResource {

    private final Logger log = LoggerFactory.getLogger(CurrencyResource.class);

    @Inject
    private CurrencyRepository currencyRepository;

    /**
     * POST  /rest/currencies -> Create a new currency.
     */
    @RequestMapping(value = "/rest/currencies",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void create(@RequestBody Currency currency) {
        log.debug("REST request to save Currency : {}", currency);
        currencyRepository.save(currency);
    }

    /**
     * GET  /rest/currencies -> get all the currency.
     */
    @RequestMapping(value = "/rest/currencies",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public List<Currency> getAll() {
        log.debug("REST request to get all Currencies");
        return currencyRepository.findAll();
    }

    /**
     * GET  /rest/currencies/:id -> get the "id" currency.
     */
    @RequestMapping(value = "/rest/currencies/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public ResponseEntity<Currency> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get IndustrySector : {}", id);
        Currency currency = currencyRepository.findOne(id);
        if (currency == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(currency, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/currencies/:id -> delete the "id" currency.
     */
    @RequestMapping(value = "/rest/currencies/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Currency : {}", id);
        currencyRepository.delete(id);
    }
}
