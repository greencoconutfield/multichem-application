package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.Contact;
import com.yukam.mypam.repository.ContactRepository;
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
public class ContactResource {

    private final Logger log = LoggerFactory.getLogger(ContactResource.class);

    @Inject
    private ContactRepository contactRepository;

    /**
     * POST  /rest/contacts -> Create a new contact.
     */
    @RequestMapping(value = "/rest/contacts",
            method = RequestMethod.POST,
            produces = "application/json")
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void create(@RequestBody Contact contact) {
        log.debug("REST request to save Contact : {}", contact);
//        for (Address address : contact.getAddresses()) {
//            address.setContact(contact);
//        }
        contactRepository.save(contact);
    }

    /**
     * GET  /rest/contacts -> get all the contacts.
     */
    @RequestMapping(value = "/rest/contacts",
            method = RequestMethod.GET,
            produces = "application/json")
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public List<Contact> getAll() {
        log.debug("REST request to get all Contacts");
        return contactRepository.findAll();
    }

    /**
     * GET  /rest/contacts/:id -> get the "id" contact.
     */
    @RequestMapping(value = "/rest/contacts/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public ResponseEntity<Contact> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Contact : {}", id);
        Contact contact = contactRepository.findOne(id);
        if (contact == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/contacts/:id -> delete the "id" contact.
     */
    @RequestMapping(value = "/rest/contacts/{id}",
            method = RequestMethod.DELETE,
            produces = "application/json")
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Contact : {}", id);
        contactRepository.delete(id);
    }
}
