package com.iaschowrai.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iaschowrai.productservice.dto.ProductRequest;
import com.iaschowrai.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	/*You are using TestContainers to spin up a MongoDB container for integration testing.
	The @Container annotation marks a static field as a container that should be started before any test methods are executed.*/
	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongodb:4.4.2");

//	You are using MockMvc to perform HTTP requests and validate the responses.
	@Autowired
	private MockMvc mockMvc;

//	The ObjectMapper is used for converting objects to JSON strings.
	@Autowired
	private ObjectMapper objectMapper;



//	ProductRepository: Autowired to interact with the MongoDB database.
	@Autowired
	private ProductRepository productRepository;


	/*The @DynamicPropertySource annotation is used to dynamically set properties for your Spring application.
	In this case, it sets the MongoDB connection URI dynamically based on the running MongoDB container.*/
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);

	}

	/*The actual test method where you create a ProductRequest, convert it to a JSON string,
	and perform a POST request to your /api/product endpoint. It then asserts that the HTTP response status is "Created"
	 and checks if one product is present in the MongoDB database.*/
	@Test
	void shouldCreateProduct() throws Exception {

		ProductRequest productRequest =  getProductRequest();
		String productRequestString = objectMapper.writeValueAsString(productRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
					.contentType(MediaType.APPLICATION_JSON)
					.content(productRequestString))
				.andExpect(status().isCreated());
		Assertions.assertEquals(1, productRepository.findAll().size());
	}

	private ProductRequest getProductRequest(){
		return ProductRequest.builder()
				.name("iphone 13")
				.description("apple")
				.price(BigDecimal.valueOf(1200))
				.build();
	}

}
