package com.yukam.mypam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yukam.mypam.domain.ContactDetail;
import com.yukam.mypam.repository.ContactDetailRepository;
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
public class ContactDetailResource {

    private final Logger log = LoggerFactory.getLogger(ContactDetailResource.class);

    @Inject
    private ContactDetailRepository contactDetailRepository;

    /**
     * POST  /rest/contactdetails -> Create a new contact.
     */
    @RequestMapping(value = "/rest/contactdetails",
            method = RequestMethod.POST,
            produces = "application/json")
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void create(@RequestBody ContactDetail contactDetail) {
        log.debug("REST request to save ContactDetail : {}", contactDetail);
//        for (Address address : contactDetail.getAddresses()) {
//            address.setContact(contactDetail);
//        }
        contactDetailRepository.save(contactDetail);
    }

    /**
     * GET  /rest/contactdetails -> get all the contactdetails.
     */
    @RequestMapping(value = "/rest/contactdetails",
            method = RequestMethod.GET,
            produces = "application/json")
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public List<ContactDetail> getAll() {
        log.debug("REST request to get all Contacts");
        return contactDetailRepository.findAll();
    }

    /**
     * GET  /rest/contactdetails/:id -> get the "id" contact.
     */
    @RequestMapping(value = "/rest/contactdetails/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public ResponseEntity<ContactDetail> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get ContactDetail : {}", id);
        ContactDetail contactDetail = contactDetailRepository.findOne(id);
        if (contactDetail == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contactDetail, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/contactdetails/:id -> delete the "id" contactdetail.
     */
    @RequestMapping(value = "/rest/contactdetails/{id}",
            method = RequestMethod.DELETE,
            produces = "application/json")
    @RolesAllowed({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete ContactDetail : {}", id);
        contactDetailRepository.delete(id);
    }
}
