package stepDefinitions;
import dataProvider.ConfigFileReader;

import static global.SharedWebDriver.getDriver;

import businessModel.OpenTriviaDBBM;
import global.SharedContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import static org.junit.Assert.assertTrue;

import pages.*;
import utils.WebElementUtils;

public class StepsOpenTrivia {

    private OpenTriviaDBPage openTriviaDBPage;
    private OpenTriviaDBBM openTriviaDBBMInfo;

    public StepsOpenTrivia(){
        openTriviaDBBMInfo = SharedContext.getOpenTriviaDBBMInstance();
    }

    @Given("I navigate to the Questions Database search page")
    public void iNavigateToTheQuestionsDataBaseSearchPage(){
        this.navigateToTheMainUrl(ConfigFileReader.getInstance().getOpenTriviaDBUrl());

        openTriviaDBPage.BrowseLink.click();
        WebElementUtils.waitForElementToLoad(openTriviaDBPage.QuestionDBSearchField);
    }

    @And("I input {} in the search field")
    public void iInputDataInTheSearchField(String value){
        openTriviaDBPage.QuestionDBSearchField.clear();
        openTriviaDBPage.QuestionDBSearchField.sendKeys(value);
    }

    @When("I click on the Search button")
    public void iClickOnTheSearchButton(){
        openTriviaDBPage.SearchButton.click();
        WebElementUtils.explicitWaitMs(2000);
    }

    @When("I should see No Questions Found message")
    public void iShouldSeeNoQuestionsFoundMessage(){
        WebDriver driver = getDriver();
        Boolean messagePresent = !driver.findElements(By.xpath("//body/div[2]/div[text()='No questions found.']")).isEmpty();

        assertTrue("Error: Message is not the expected one.", messagePresent);
    }

    @Then("I select the Type to {}")
    public void iSelectTheTypeToX(String typeValue){
        openTriviaDBPage.SetTypeDropDown(typeValue);
    }

    @Then("I should see {} items in the table results")
    public void iShouldSeeXItemsInTheTableResults(String qtyValue){
        WebDriver driver = getDriver();
        int qtyTable = driver.findElements(By.xpath("//table[@class='table table-bordered']/tbody/tr")).size();
        openTriviaDBBMInfo.setTableResultQty(qtyTable);

        Boolean qtyMatching = openTriviaDBBMInfo.getTableResultQty().equalsIgnoreCase(qtyValue);

        assertTrue("Error: Quantity of items expected is " + qtyValue + ", but the quantity found was " + openTriviaDBBMInfo.getTableResultQty(), qtyMatching);
    }

    @When("I should see the pagination control active")
    public void iShouldSeeThePaginationControlActive(){
        WebDriver driver = getDriver();
        Boolean paginationControlPresent = !driver.findElements(By.xpath("//ul[@class='pagination pagination-lg']/li[@class='active']")).isEmpty();

        assertTrue("Error: Pagination control is not active.", paginationControlPresent);
    }

    @When("I click on the first row user link")
    public void iClickOnTheFirstRowUserLink(){
        openTriviaDBPage.UserLinkFirstRow.click();
        WebElementUtils.waitForElementToLoad(openTriviaDBPage.ProfileUserLink);
    }

    @And("I should match the verified questions value with returned in search")
    public void iShouldMatchTheVerifiedQuestionsValue(){
        Boolean qtyVerifiedMatching = openTriviaDBBMInfo.getTableResultQty().equalsIgnoreCase(openTriviaDBPage.VerifiedQuestionValue.getText());

        assertTrue("Error: Quantity of Verified Questions expected is " + openTriviaDBBMInfo.getTableResultQty() + ", but the quantity found was " + openTriviaDBPage.VerifiedQuestionValue.getText(), qtyVerifiedMatching);
    }

    private void navigateToTheMainUrl(String url){
        WebDriver driver = getDriver();
        driver.navigate().to(url);
        openTriviaDBPage = new OpenTriviaDBPage(driver);

        driver.manage().window().maximize();

        WebElementUtils.waitForElementToLoad(openTriviaDBPage.LoginLink);
    }
}
