package definitions;

import com.project.rcstore.RcStoreApplication;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.response.Response;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RcStoreApplication.class)
public class SpringBootCucumberTestDefinitions {
    private static final String BASE_URL = "http://localhost:";

    @LocalServerPort
   String port;

    private static Response response;

    @Given("a list of products are available")
    public void aListOfProductsAreAvailable() {
        
    }

    @When("i add a product to my productList")
    public void iAddAProductToMyProductList() {
        
    }

    @Then("the product is added")
    public void theProductIsAdded() {
        
    }

    @When("i remove product from my productList")
    public void iRemoveProductFromMyProductList() {
        
    }

    @Then("the product is removed")
    public void theProductIsRemoved() {

    }
}
