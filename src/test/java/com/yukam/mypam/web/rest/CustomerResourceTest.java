package com.yukam.mypam.web.rest;

import com.yukam.mypam.Application;
import com.yukam.mypam.domain.Contact;
import com.yukam.mypam.domain.Customer;
import com.yukam.mypam.domain.IndustrySector;
import com.yukam.mypam.repository.CustomerRepository;
import com.yukam.mypam.repository.IndustrySectorRepository;
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
 * Test class for the CustomerResource REST controller.
 *
 * @see com.yukam.mypam.web.rest.CustomerResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class })
@ActiveProfiles("test")
public class CustomerResourceTest {
    
    private static final Long DEFAULT_ID = new Long(1L);

    @Inject
    private CustomerRepository customerRepository;

    @Inject
    private IndustrySectorRepository industrySectorRepository;

    private MockMvc restCustomerMockMvc;
    
    private Customer customer;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CustomerResource customerResource = new CustomerResource();
        ReflectionTestUtils.setField(customerResource, "customerRepository", customerRepository);

        this.restCustomerMockMvc = MockMvcBuilders.standaloneSetup(customerResource).build();

        customer = new Customer();
        customer.setId(DEFAULT_ID);
        customer.setCustomerName("customerName");
        Contact contact = new Contact();
        contact.setFirstName("firstName");
        contact.setLastName("lastName");
        contact.setEmail("email");
       // customer.setContact(contact);
        IndustrySector is = new IndustrySector();
        is.setId(1L);
        is.setName("industrySector");
        industrySectorRepository.save(is);
        customer.setIndustrySector(is);
    }

    @Test
    public void testCRUDCustomer() throws Exception {

//    	// Create Customer
//    	restCustomerMockMvc.perform(post("/app/rest/customers")
//    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(customer)))
//                .andExpect(status().isOk());
//
//    	// Read Customer
//    	restCustomerMockMvc.perform(get("/app/rest/customers/{id}", DEFAULT_ID))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
//    			.andExpect(jsonPath("$.industrySector.name").value("industrySector"));
//
//    	// Update Customer
//        IndustrySector industrySectorUpd = new IndustrySector();
//        industrySectorUpd.setId(2L);
//        industrySectorUpd.setName("industrySectorUpd");
//        industrySectorRepository.save(industrySectorUpd);
//    	customer.setIndustrySector(industrySectorUpd);
//
//    	restCustomerMockMvc.perform(post("/app/rest/customers")
//    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(customer)))
//                .andExpect(status().isOk());
//
//    	// Read updated Customer
//    	restCustomerMockMvc.perform(get("/app/rest/customers/{id}", DEFAULT_ID))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
//    			.andExpect(jsonPath("$.industrySector.name").value("industrySectorUpd"));
//
//    	// Delete Customer
//    	restCustomerMockMvc.perform(delete("/app/rest/customers/{id}", DEFAULT_ID)
//                .accept(TestUtil.APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk());
//
//    	// Read nonexisting Customer
//    	restCustomerMockMvc.perform(get("/app/rest/customers/{id}", DEFAULT_ID)
//                .accept(TestUtil.APPLICATION_JSON_UTF8))
//                .andExpect(status().isNotFound());

    }
}
