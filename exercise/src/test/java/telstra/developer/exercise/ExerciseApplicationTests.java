package telstra.developer.exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import telstra.developer.exercise.dao.ExerciseDAO;
import telstra.developer.exercise.entity.CustomerResource;
import telstra.developer.exercise.entity.Discount;
import telstra.developer.exercise.entity.PurchasedProduct;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;

@AutoConfigureMockMvc
@SpringBootTest
@EnableAutoConfiguration(exclude=DataSourceAutoConfiguration.class)
class ExerciseApplicationTests {
	
	@MockBean
	ExerciseDAO exerciseDAO;
	
	@Autowired
	private MockMvc mockMvc;
		
	
	ResultActions getCustomerHttpRequest(String uuid) throws Exception{
		return mockMvc.perform(MockMvcRequestBuilders.get("/rest/v1/customers/"+uuid));
		
	}
	
	
	ResultActions getDiscountHttpRequest(String uuid,String productId) throws Exception{
		if(productId!=null) {
			return mockMvc.perform(MockMvcRequestBuilders.get("/rest/v1/users/"+uuid+"/discounts?productId="+productId));
		}
		return mockMvc.perform(MockMvcRequestBuilders.get("/rest/v1/users/"+uuid+"/discounts"));
		
	}
	
	@BeforeEach
	void setUp() {
		CustomerResource customer = new CustomerResource("qa-dev-emma","Emma","BPR Street");
		Discount d1 = new Discount("d-15","PERCENT","15% Discount",new BigDecimal(15));
		Discount d2 = new Discount("df-15","AMOUNT","15$ flat discount on purchases over 250$",new BigDecimal(15));
		PurchasedProduct product = new PurchasedProduct("sku-300","subscription of 300",new BigDecimal(300),new BigDecimal(255));
		product.setDiscountInformation(d1);
		d1.setProduct(product);
		List<Discount> customerDiscounts = new ArrayList<Discount>();
		List<PurchasedProduct> products = new ArrayList<PurchasedProduct>();
		customerDiscounts.add(d2);
		products.add(product);
		customer.setProducts(products);
		customer.setCustomerDiscounts(customerDiscounts);
		Mockito.when(exerciseDAO.findById("qa-dev-emma")).thenReturn(Optional.ofNullable(customer));
	}
	
	@DisplayName("Customer API test with proper input")
	@Test
	void testCustomerApiWithProperInput() throws Exception{
		
		ResultActions result = getCustomerHttpRequest("qa-dev-emma");
		result.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.eligibleDiscounts",hasSize(2)))
		.andExpect(jsonPath("$.products",hasSize(1)));
		
	}
	
	@DisplayName("Customer API test with wrong input")
	@Test
	void testCustomerWithWrongInput() throws Exception{
		ResultActions result = getCustomerHttpRequest("qa-dev-emma2");
		result.andExpect(status().is(404))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.code").value("ER001"));
		
	}
	
	@DisplayName("Discount API test without productId")
	@Test
	void tesDiscountApiWithOutProductId() throws Exception {
		ResultActions result = getDiscountHttpRequest("qa-dev-emma",null);
		result.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$",hasSize(2)));
	}
	
	@DisplayName("Discount API test with productId")
	@Test
	void tesDiscountApiWithProductId() throws Exception {
		ResultActions result = getDiscountHttpRequest("qa-dev-emma","sku-300");
		result.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$",hasSize(1)));
	}

}
