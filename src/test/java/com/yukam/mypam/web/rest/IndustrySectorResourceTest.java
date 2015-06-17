package com.yukam.mypam.web.rest;

import com.yukam.mypam.Application;
import com.yukam.mypam.domain.IndustrySector;
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
 * TODO KKS: Update for include description
 * Test class for the IndustrySectorResource REST controller.
 *
 * @see com.yukam.mypam.web.rest.IndustrySectorResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class })
@ActiveProfiles("test")
public class IndustrySectorResourceTest {
    
    private static final Long DEFAULT_ID = new Long(1L);

    private static final String DEFAULT_NAME = "industrySectorName";

    private static final String UPD_NAME = "industrySectorNameUpdated";

    @Inject
    private IndustrySectorRepository industrysectorRepository;

    private MockMvc restIndustrySectorMockMvc;
    
    private IndustrySector industrysector;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        IndustrySectorResource industrysectorResource = new IndustrySectorResource();
        ReflectionTestUtils.setField(industrysectorResource, "industrysectorRepository", industrysectorRepository);

        this.restIndustrySectorMockMvc = MockMvcBuilders.standaloneSetup(industrysectorResource).build();

        industrysector = new IndustrySector();
        industrysector.setId(DEFAULT_ID);
    	industrysector.setName(DEFAULT_NAME);
    }

    @Test
    public void testCRUDIndustrySector() throws Exception {

//    	// Create IndustrySector
//    	restIndustrySectorMockMvc.perform(post("/app/rest/industrysectors")
//    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(industrysector)))
//                .andExpect(status().isOk());
//
//    	// Read IndustrySector
//    	restIndustrySectorMockMvc.perform(get("/app/rest/industrysectors/{id}", DEFAULT_ID))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
//    			.andExpect(jsonPath("$.name").value(DEFAULT_NAME));
//
//    	// Update IndustrySector
//    	industrysector.setName(UPD_NAME);
//
//    	restIndustrySectorMockMvc.perform(post("/app/rest/industrysectors")
//    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(industrysector)))
//                .andExpect(status().isOk());
//
//    	// Read updated IndustrySector
//    	restIndustrySectorMockMvc.perform(get("/app/rest/industrysectors/{id}", DEFAULT_ID))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
//    			.andExpect(jsonPath("$.name").value(UPD_NAME.toString()));
//
//    	// Delete IndustrySector
//    	restIndustrySectorMockMvc.perform(delete("/app/rest/industrysectors/{id}", DEFAULT_ID)
//                .accept(TestUtil.APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk());
//
//    	// Read nonexisting IndustrySector
//    	restIndustrySectorMockMvc.perform(get("/app/rest/industrysectors/{id}", DEFAULT_ID)
//                .accept(TestUtil.APPLICATION_JSON_UTF8))
//                .andExpect(status().isNotFound());

    }
}
