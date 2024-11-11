package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.*;

@RunWith(Cucumber.class)
@CucumberOptions(
		features= {"C:\\Users\\pratham.shanbhag\\eclipse-workspace\\Oct_Selenium\\src\\test\\java\\features\\login.feature"},
		glue= {"StepDefination"},
		plugin= {"pretty"},
		monochrome=true
		)
public class TestRunner{
	
}




