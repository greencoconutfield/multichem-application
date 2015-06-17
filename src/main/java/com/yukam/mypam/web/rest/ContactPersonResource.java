package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.ContactPerson;
import com.yukam.mypam.repository.ContactPersonRepository;
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
 * REST controller for managing Contact.
 */
@RestController
@RequestMapping("/app")
public class ContactPersonResource {

    private final Logger log = LoggerFactory.getLogger(ContactPersonResource.class);

    @Inject
    private ContactPersonRepository contactPersonRepository;

    /**
     * POST  /rest/contactpersons -> Create a new contactperson.
     */
    @RequestMapping(value = "/rest/contactpersons",
            method = RequestMethod.POST,
            produces = "application/json")
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void create(@RequestBody ContactPerson contactPerson) {
        log.debug("REST request to save contactperson : {}", contactPerson);
//        for (Address address : contactDetail.getAddresses()) {
//            address.setContact(contactDetail);
//        }
        contactPersonRepository.save(contactPerson);
    }

    /**
     * GET  /rest/contactpersons -> get all the contactperson.
     */
    @RequestMapping(value = "/rest/contactpersons",
            method = RequestMethod.GET,
            produces = "application/json")
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public List<ContactPerson> getAll() {
        log.debug("REST request to get all ContactPersons");
        return contactPersonRepository.findAll();
    }

    /**
     * GET  /rest/contactpersons/:id -> get the "id" contactperson.
     */
    @RequestMapping(value = "/rest/contactpersons/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public ResponseEntity<ContactPerson> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get ContactPerson : {}", id);
        ContactPerson contactPerson = contactPersonRepository.findOne(id);
        if (contactPerson == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contactPerson, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/contactpersons/:id -> delete the "id" contactperson.
     */
    @RequestMapping(value = "/rest/contactpersons/{id}",
            method = RequestMethod.DELETE,
            produces = "application/json")
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete ContactPerson : {}", id);
        contactPersonRepository.delete(id);
    }
}
