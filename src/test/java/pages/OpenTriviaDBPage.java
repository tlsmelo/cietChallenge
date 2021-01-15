package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OpenTriviaDBPage {

    public WebDriver driver;

    public OpenTriviaDBPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Login link - Header navigation
    @FindBy(how = How.XPATH, using = "//ul[@class='nav navbar-nav navbar-right']/li[5]/a")
    public WebElement LoginLink;

    //Browse link - Header navigation
    @FindBy(how = How.XPATH, using = "//ul[@class='nav navbar-nav navbar-right']/li[1]/a")
    public WebElement BrowseLink;

    //Questions Database - Search field
    @FindBy(how = How.XPATH, using = "//input[@id='query']")
    public WebElement QuestionDBSearchField;

    //Search button
    @FindBy(how = How.XPATH, using = "//button[text()=' Search']")
    public WebElement SearchButton;

    //Type Dropdown
    @FindBy(how = How.XPATH, using = "//select[@id='type']")
    public WebElement TypeDropDown;

    public void SetTypeDropDown (String value) {
        Select select = new Select(TypeDropDown);
        select.selectByVisibleText(value);
    }

    //#1 row - User link
    @FindBy(how = How.XPATH, using = "//table[@class='table table-bordered']/tbody/tr[1]/td[6]/a")
    public WebElement UserLinkFirstRow;

    //Profile User Link
    @FindBy(how = How.XPATH, using = "//div[@class='container']/h1/a")
    public WebElement ProfileUserLink;

    //Profile Verified Questions table - Verified Question value
    @FindBy(how = How.XPATH, using = "//table[@class='table table-bordered'][1]/tbody/tr/td[1]")
    public WebElement VerifiedQuestionValue;
}
