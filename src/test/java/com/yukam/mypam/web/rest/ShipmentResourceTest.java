package com.yukam.mypam.web.rest;

import com.yukam.mypam.Application;
import com.yukam.mypam.domain.Shipment;
import com.yukam.mypam.repository.ShipmentRepository;
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
 * Test class for the ShipmentResource REST controller.
 *
 * @see com.yukam.mypam.web.rest.ShipmentResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class })
@ActiveProfiles("test")
public class ShipmentResourceTest {
    
    private static final Long DEFAULT_ID = new Long(1L);

    private static final String DEFAULT_DESCRIPTION = "sampleDescription";

    private static final String UPD_DESCRIPTION = "sampleDescriptionUpt";

    @Inject
    private ShipmentRepository shipmentRepository;

    private MockMvc restShipmentMockMvc;
    
    private Shipment shipment;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ShipmentResource shipmentResource = new ShipmentResource();
        ReflectionTestUtils.setField(shipmentResource, "shipmentRepository", shipmentRepository);

        this.restShipmentMockMvc = MockMvcBuilders.standaloneSetup(shipmentResource).build();

        shipment = new Shipment();
        shipment.setId(DEFAULT_ID);
    	shipment.setDescription(DEFAULT_DESCRIPTION);
    }

    @Test
    public void testCRUDShipment() throws Exception {

//    	// Create Shipment
//    	restShipmentMockMvc.perform(post("/app/rest/shipments")
//    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(shipment)))
//                .andExpect(status().isOk());
//
//    	// Read Shipment
//    	restShipmentMockMvc.perform(get("/app/rest/shipments/{id}", DEFAULT_ID))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
//    			.andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
//
//    	// Update Shipment
//    	shipment.setDescription(UPD_DESCRIPTION);
//
//    	restShipmentMockMvc.perform(post("/app/rest/shipments")
//    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(shipment)))
//                .andExpect(status().isOk());
//
//    	// Read updated Shipment
//    	restShipmentMockMvc.perform(get("/app/rest/shipments/{id}", DEFAULT_ID))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
//    			.andExpect(jsonPath("$.description").value(UPD_DESCRIPTION));
//
//    	// Delete Shipment
//    	restShipmentMockMvc.perform(delete("/app/rest/shipments/{id}", DEFAULT_ID)
//                .accept(TestUtil.APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk());
//
//    	// Read nonexisting Shipment
//    	restShipmentMockMvc.perform(get("/app/rest/shipments/{id}", DEFAULT_ID)
//                .accept(TestUtil.APPLICATION_JSON_UTF8))
//                .andExpect(status().isNotFound());

    }
}
