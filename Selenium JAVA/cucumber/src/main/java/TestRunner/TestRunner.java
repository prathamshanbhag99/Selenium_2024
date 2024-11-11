package TestRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"C:\\Users\\pratham.shanbhag\\cucumber\\src\\test\\java\\features\\snapdeal.feature"},
        glue = {"StepDefination"},
        plugin = {"pretty"},
        monochrome = true
)
public class TestRunner {

}

