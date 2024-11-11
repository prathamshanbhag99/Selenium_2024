package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"C:\\Users\\pratham.shanbhag\\eclipse-workspace\\Oct_Selenium\\src\\test\\java\\features2\\snapdeal.feature"},
        glue = {"StepDefination2"},
        plugin = {"pretty"},
        monochrome = true
)
public class TestRunner {

}




