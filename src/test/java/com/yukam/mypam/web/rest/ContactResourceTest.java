package com.yukam.mypam.web.rest;

import com.yukam.mypam.Application;
import com.yukam.mypam.domain.Address;
import com.yukam.mypam.domain.Contact;
import com.yukam.mypam.repository.AddressRepository;
import com.yukam.mypam.repository.ContactRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.inject.Inject;

/**
 * Test class for the ContactResource REST controller.
 *
 * @see com.yukam.mypam.web.rest.ContactResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class })
@ActiveProfiles("test")
public class ContactResourceTest {
    
    private static final Long DEFAULT_ID = new Long(1L);

    private static final String DEFAULT_FIRST_NAME = "sampleFirstName";
    private static final String UPD_FIRST_NAME = "sampleFirstNameUpt";

    private static final String DEFAULT_ADDRESS_TYPE = "sampleAddressType";
    private static final String UPD_ADDRESS_TYPE = "sampleAddressTypeUpt";

    @Inject
    private ContactRepository contactRepository;

    @Inject
    private AddressRepository addressRepository;

    private MockMvc restContactMockMvc;
    private MockMvc restAddressMockMvc;

    private Contact contact;
    private Address address;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ContactResource contactResource = new ContactResource();
        ReflectionTestUtils.setField(contactResource, "contactRepository", contactRepository);

        this.restContactMockMvc = MockMvcBuilders.standaloneSetup(contactResource).build();

        AddressResource addressResource = new AddressResource();
        ReflectionTestUtils.setField(addressResource, "addressRepository", addressRepository);

        this.restAddressMockMvc = MockMvcBuilders.standaloneSetup(addressResource).build();


        contact = new Contact();
        contact.setId(DEFAULT_ID);
    	contact.setFirstName(DEFAULT_FIRST_NAME);
//        if (contact.getAddresses() == null) {
//            contact.setAddresses(new HashSet<Address>());
//        }
//        address = new Address();
//        address.setId(1);
//        address.setAddressType(DEFAULT_ADDRESS_TYPE);
//        contact.getAddresses().add(address);
    }

    @Test
    public void testCRUDContact() throws Exception {

//    	// Create Contact
//    	restContactMockMvc.perform(post("/app/rest/contacts")
//    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(contact)))
//                .andExpect(status().isOk());
//
//    	// Read Contact
//    	restContactMockMvc.perform(get("/app/rest/contacts/{id}", DEFAULT_ID))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
//    			.andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
//                .andExpect(jsonPath("$.addresses[0].id").value(1));
//
//        // Read Address
//        restAddressMockMvc.perform(get("/app/rest/address/{id}", 1))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
//                .andExpect(jsonPath("$.addressType").value(DEFAULT_ADDRESS_TYPE.toString()));
//
//        // Update Contact
//    	contact.setFirstName(UPD_FIRST_NAME);
//
//    	restContactMockMvc.perform(post("/app/rest/contacts")
//    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(contact)))
//                .andExpect(status().isOk());
//
//    	// Read updated Contact
//    	restContactMockMvc.perform(get("/app/rest/contacts/{id}", DEFAULT_ID))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
//    			.andExpect(jsonPath("$.firstName").value(UPD_FIRST_NAME));
//
//        // Update Address
////        contact.getAddresses().clear();
////        address.setId(2);
////        contact.getAddresses().add(address);
//
//        restContactMockMvc.perform(post("/app/rest/contacts")
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(contact)))
//                .andExpect(status().isOk());
//
//        // Read updated Contact
//        restContactMockMvc.perform(get("/app/rest/contacts/{id}", DEFAULT_ID))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.addresses[0].id").value(2));
//
//    	// Delete Contact
//    	restContactMockMvc.perform(delete("/app/rest/contacts/{id}", DEFAULT_ID)
//                .accept(TestUtil.APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk());
//
//    	// Read nonexisting Contact
//    	restContactMockMvc.perform(get("/app/rest/contacts/{id}", DEFAULT_ID)
//                .accept(TestUtil.APPLICATION_JSON_UTF8))
//                .andExpect(status().isNotFound());
//
//        // Check all-delete-orphan removal of the address..
//        restContactMockMvc.perform(get("/app/rest/address/{id}", 1)
//                .accept(TestUtil.APPLICATION_JSON_UTF8))
//                .andExpect(status().isNotFound());

    }
}
