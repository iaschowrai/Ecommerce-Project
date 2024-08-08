package com.iaschowrai.productservice;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureMockMvc
public class ProductServiceApplicationTests {

	@LocalServerPort
	private Integer port;

	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	static {
		mongoDBContainer.start();
	}

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@Test
	void shouldCreateProduct() throws Exception {
		String requestBody = """
            {
                "name": "iphone15",
                "description": "iphone 15 is new mobile phone",
                "price": 1000
            }""";

		given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/product/add")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name", Matchers.equalTo("iphone15"))
				.body("description", Matchers.equalTo("iphone 15 is new mobile phone"))
				.body("price", Matchers.equalTo(1000));
	}
}
