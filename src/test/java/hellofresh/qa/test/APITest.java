package hellofresh.qa.test;

import hellofresh.qa.tools.Configuration;
import hellofresh.qa.tools.TestListener;
import hellofresh.qa.tools.Wiremock;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.List;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

@Listeners({TestListener.class})
@Epic("API Tests")
@Feature("API tests for the countries")
public class APITest {

    private Logger log = LoggerFactory.getLogger(APITest.class);
    private Configuration conf = new Configuration();
    private String result = "";
    private JsonPath jsonPath;
    private List<String> countryList = conf.config.getStringList("testdata.countries");
    private Wiremock mock = new Wiremock();

    @BeforeTest
    public void start()
    {
        RestAssured.baseURI = "http://services.groupkt.com/country";
    }

    @Test
    @Description("Get all countries and validate that US, DE and GB were returned in the response")
    @Title("Get all countries and validate the response")
    public void getAllCountriesAndValidate() {
        log.info("Calling GET/all to get list of all the countries");
        result = given().when().get("/get/all").then().assertThat().statusCode(200).and().extract().response().asString();

        if (result != null) {
            jsonPath = new JsonPath(result);
            for (String country : countryList) {
                log.info("Verifying if the response contains the expected country [" + country + "]");
                assertTrue(jsonPath.getString("RestResponse.result.alpha2_code").contains(country), "Country [" + country + "] not found in the list");
            }
        }
    }

    @Test
    @Description("Get each country (US, DE and GB) from the country list individually and validate the response")
    @Title("Get individual country and validate the response")
    public void getIndividualCountryAndValidate() {
        log.info("Calling GET/country to get the individual response");

        for (String country : countryList)
        {
            log.info("Verifying if the response contains the expected country [" + country + "]");
            given().when().get("/get/iso2code/" + country).then().assertThat().statusCode(200).
                and().assertThat().body("RestResponse.result.alpha2_code", org.hamcrest.Matchers.equalTo(country));
        }
    }

    @Test
    @Description("Get information for non-existant country and validate the response")
    @Title("Get invalid country and validate the response")
    public void getInvalidCountryAndValidate() {
        String country = "xyz";
        log.info("Calling GET/country for a Non-existent country " + country);

        result = given().when().get("/get/iso2code/" + country).then().assertThat().statusCode(200).and().extract().response().asString();
        if (result != null) {
            jsonPath = new JsonPath(result);
            log.info("Verifying if the response contains the expected country [" + country + "]");
            assertTrue(jsonPath.getString("RestResponse.messages")
                    .contains("No matching country found for requested code [" + country + "]"));
        }
    }

    @Test
    @Description("Validate new country addition using POST")
    @Title("Add a new country and validate the response")
    public void postNewCountryAdditionAndValidate() {
        log.info("Calling POST to add a new Country named as 'TC'");
        RestAssured.baseURI = "http://localhost:8080";
        mock.wiremockStart();
        log.info("Adding a new country TC and verifying if the response contains the new country");
        given().when().header("Content-Type", "application/json").body("postRequest.json")
         .post("/post").then().assertThat().statusCode(200).body("alpha2_code", org.hamcrest.Matchers.equalTo("TC"));

        mock.wiremockStop();
    }
}