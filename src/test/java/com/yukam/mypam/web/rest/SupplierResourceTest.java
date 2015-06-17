package com.yukam.mypam.web.rest;

import com.yukam.mypam.Application;
import com.yukam.mypam.domain.Contact;
import com.yukam.mypam.domain.Supplier;
import com.yukam.mypam.repository.SupplierRepository;
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
 * Test class for the SupplierResource REST controller.
 *
 * @see com.yukam.mypam.web.rest.SupplierResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class })
@ActiveProfiles("test")
public class SupplierResourceTest {
    
    private static final Long DEFAULT_ID = new Long(1L);

    @Inject
    private SupplierRepository supplierRepository;

    private MockMvc restSupplierMockMvc;
    
    private Supplier supplier;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SupplierResource supplierResource = new SupplierResource();
        ReflectionTestUtils.setField(supplierResource, "supplierRepository", supplierRepository);

        this.restSupplierMockMvc = MockMvcBuilders.standaloneSetup(supplierResource).build();

        supplier = new Supplier();
        supplier.setId(DEFAULT_ID);

        Contact contact = new Contact();
        contact.setFirstName("firstName");
        contact.setLastName("lastName");
        contact.setEmail("email");
        //supplier.setContact(contact);
        supplier.setSupplierName("SupplierName");
    }

    @Test
    public void testCRUDSupplier() throws Exception {

//    	// Create Supplier
//    	restSupplierMockMvc.perform(post("/app/rest/suppliers")
//    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(supplier)))
//                .andExpect(status().isOk());
//
//    	// Read Supplier
//    	restSupplierMockMvc.perform(get("/app/rest/suppliers/{id}", DEFAULT_ID))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
//    			.andExpect(jsonPath("$.contact.email").value("email"));
//
//    	// Update Supplier
//    	supplier.setSupplierName("SupplierNameUpd");
//
//    	restSupplierMockMvc.perform(post("/app/rest/suppliers")
//    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(supplier)))
//                .andExpect(status().isOk());
//
//    	// Read updated Supplier
//    	restSupplierMockMvc.perform(get("/app/rest/suppliers/{id}", DEFAULT_ID))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
//    			.andExpect(jsonPath("$.supplierName").value("SupplierNameUpd"));
//
//    	// Delete Supplier
//    	restSupplierMockMvc.perform(delete("/app/rest/suppliers/{id}", DEFAULT_ID)
//                .accept(TestUtil.APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk());
//
//    	// Read nonexisting Supplier
//    	restSupplierMockMvc.perform(get("/app/rest/suppliers/{id}", DEFAULT_ID)
//                .accept(TestUtil.APPLICATION_JSON_UTF8))
//                .andExpect(status().isNotFound());

    }
}
