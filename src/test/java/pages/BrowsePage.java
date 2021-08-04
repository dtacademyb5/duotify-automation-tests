package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BrowsePage extends PageBase{


    @FindBy(xpath = "//div[@class='gridViewInfo']")
    public List<WebElement> albums;



}
