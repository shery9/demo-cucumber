package com.demo.cucumbertest.steps.product;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductStepDefinition {

    private static final String FIELD_TITLE = "title";
    private static final String FIELD_ID = "id";
    private static final String FIELD_BRAND = "brand";
    private static final String FIELD_PRICE = "price";

    private Response response;

    @Given("I send a GET request to {string}")
    public void callProductAPI(String url) {
        response = RestAssured.get(url);;
    }

    @When("I receive a response")
    public void iReceiveAResponse() {
        // The response was already received in the previous step
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @Then("the response should contain the following fields:")
    public void theResponseShouldContainTheFollowingFields(DataTable dataTable) {
        dataTable.asMaps(String.class, String.class).forEach(data -> {
            String expectedId = (String) data.get(FIELD_ID);
            String expectedTitle = (String) data.get(FIELD_TITLE);
            String expectedBrand = (String) data.get(FIELD_BRAND);
            String expectedPrice = (String) data.get(FIELD_PRICE);

            JsonPath jsonPathResponse = response.jsonPath();

            assertThat(jsonPathResponse)
                    .returns(expectedId, jsonPath -> jsonPath.getString(FIELD_ID))
                    .returns(expectedTitle, jsonPath -> jsonPath.getString(FIELD_TITLE))
                    .returns(expectedBrand, jsonPath -> jsonPath.getString(FIELD_BRAND))
                    .returns(expectedPrice, jsonPath -> jsonPath.getString(FIELD_PRICE));
        });
    }
}
