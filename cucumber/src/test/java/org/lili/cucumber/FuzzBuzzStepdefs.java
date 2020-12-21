package org.lili.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

/**
 * @author lili
 * @date 2020/12/22 0:44
 * @see
 * @since
 */
public class FuzzBuzzStepdefs {

    FizzBuzz fizzBuzz;
    String result;

    @Given("Create a FizzBuzz game play")
    public void createAFizzBuzzGamePlay() throws Throwable {
        fizzBuzz = new FizzBuzz();
    }


    @When("I play with number {int}")
    public void iPlayWithNumber(int number) throws Throwable {
        result = fizzBuzz.play(number);
    }


    @Then("The result is {string}")
    public void theResultIs(String resultString) throws Throwable {
        Assertions.assertEquals(result, resultString);
    }

}
