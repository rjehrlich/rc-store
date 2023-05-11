package definitions;

import com.project.rcstore.RcStoreApplication;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Map;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RcStoreApplication.class)
public class SpringBootCucumberTestDefinitions {
    private static final String BASE_URL = "http://localhost:";

    @LocalServerPort
    String port;

    private static Response response;

    public String getToken() throws Exception {
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "levi@yahoo.com");
        requestBody.put("password", "34jeans");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/auth/users/login/");
        System.out.println(response.jsonPath().getString("message"));
        return response.jsonPath().getString("message");
    }

    @Given("a list of products are available")
    public void aListOfProductsAreAvailable() {
        System.out.println("calling list of product");
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + getToken());
            HttpEntity<String> request = new HttpEntity<String>("", headers);
            ResponseEntity<String> response = new RestTemplate().exchange(BASE_URL + port + "/api/products/", HttpMethod.GET, request, String.class);
            List<Map<String, String>> products = JsonPath.from(String.valueOf(response.getBody())).getList("$");
            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
            System.out.println(products + "my products");
            System.out.println("------------------------------------");// status 200
            Assert.assertTrue(products.size() > 0);
        } catch (HttpClientErrorException e) {
            System.out.println("calling catch");
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @When("i add a product to my productList")
    public void iAddAProductToMyProductList() throws Exception {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given().header("Authorization", "Bearer " + getToken());
        // payload
        JSONObject requestBody = new JSONObject();
        requestBody.put("price", "14.00");
        requestBody.put("description", "socks");
        requestBody.put("brandName", "Hanes");
        requestBody.put("URL", "https://www.hanes.com/wp907.html");
        request.header("Content-Type", "application/json");
        // attach the payload to the request body and send to url
        response = request.body(requestBody.toString())
                .post(BASE_URL + port + "/api/products/");
    }

    @Then("the product is added")
    public void theProductIsAdded() {
        Assert.assertEquals(201, response.getStatusCode());
    }

    @When("i remove product from my productList")
    public void iRemoveProductFromMyProductList() throws Exception {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given().header("Authorization", "Bearer " + getToken());
        request.header("Content-Type", "application/json");
        response = request.delete(BASE_URL + port + "/api/products/1/");
    }

    @Then("the product is removed")
    public void theProductIsRemoved() {
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
    }

    @When("i find a product by id")
    public void iFindAProductById() throws Exception {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given().header("Authorization", "Bearer " + getToken());
        // states that Request body is a JSON
        request.header("Content-Type", "application/json");
        response = request.get(BASE_URL + port + "/api/products/1");
    }


    @Then("the product is displayed")
    public void theProductIsDisplayed() {
        Assert.assertNotNull(response.getBody());
    }
}

