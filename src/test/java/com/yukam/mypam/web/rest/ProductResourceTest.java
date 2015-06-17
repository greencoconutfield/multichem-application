package com.yukam.mypam.web.rest;

import com.yukam.mypam.Application;
import com.yukam.mypam.domain.Product;
import com.yukam.mypam.repository.ProductRepository;
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
 * Test class for the ProductResource REST controller.
 *
 * @see com.yukam.mypam.web.rest.ProductResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class })
@ActiveProfiles("test")
public class ProductResourceTest {
    
    private static final Long DEFAULT_ID = new Long(1L);

    private static final String DEFAULT_PRODUCT_TYPE = "productType";

    private static final String UPD_PRODUCT_TYPE = "productTypeUpt";

    private static final String DEFAULT_PRODUCT_NAME = "productName";

    private static final String UPD_PRODUCT_NAME = "productNameUpt";

    @Inject
    private ProductRepository productRepository;

    private MockMvc restProductMockMvc;
    
    private Product product;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProductResource productResource = new ProductResource();
        ReflectionTestUtils.setField(productResource, "productRepository", productRepository);

        this.restProductMockMvc = MockMvcBuilders.standaloneSetup(productResource).build();

        product = new Product();
        product.setId(DEFAULT_ID);
    	product.setProductName(DEFAULT_PRODUCT_NAME);
        product.setProductType(DEFAULT_PRODUCT_TYPE);
    }

    @Test
    public void testCRUDProduct() throws Exception {

//    	// Create Product
//    	restProductMockMvc.perform(post("/app/rest/products")
//    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(product)))
//                .andExpect(status().isOk());
//
//    	// Read Product
//    	restProductMockMvc.perform(get("/app/rest/products/{id}", DEFAULT_ID))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
//    			.andExpect(jsonPath("$.productType").value(DEFAULT_PRODUCT_TYPE.toString()))
//    			.andExpect(jsonPath("$.productName").value(DEFAULT_PRODUCT_NAME));
//
//    	// Update Product
//    	product.setProductType(UPD_PRODUCT_TYPE);
//    	product.setProductName(UPD_PRODUCT_NAME);
//
//    	restProductMockMvc.perform(post("/app/rest/products")
//    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(product)))
//                .andExpect(status().isOk());
//
//    	// Read updated Product
//    	restProductMockMvc.perform(get("/app/rest/products/{id}", DEFAULT_ID))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
//    			.andExpect(jsonPath("$.productType").value(UPD_PRODUCT_TYPE.toString()))
//    			.andExpect(jsonPath("$.productName").value(UPD_PRODUCT_NAME));
//
//    	// Delete Product
//    	restProductMockMvc.perform(delete("/app/rest/products/{id}", DEFAULT_ID)
//                .accept(TestUtil.APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk());
//
//    	// Read nonexisting Product
//    	restProductMockMvc.perform(get("/app/rest/products/{id}", DEFAULT_ID)
//                .accept(TestUtil.APPLICATION_JSON_UTF8))
//                .andExpect(status().isNotFound());

    }
}
