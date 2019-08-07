package FirstProjectGuru;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import FirstProjectUtil.FirstProjectUtil;

public class FirstProjectGuru 
{
	@Test(dataProvider="search")
	public void verify(String Username,String Password) throws InterruptedException, IOException
	  {
		System.setProperty("webdriver.chrome.driver","Driver/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get(FirstProjectUtil.WebsiteURL);
		driver.findElement(By.name("uid")).sendKeys(Username);
		driver.findElement(By.name("password")).sendKeys(Password);
		driver.findElement(By.name("btnLogin")).submit();
		if(Username==FirstProjectUtil.usrname && Password==FirstProjectUtil.pwd)
		{
			int useddays=31;
			if(useddays>30)
			{
				Alert t=driver.switchTo().alert();
				String alertboxtitle=t.getText();
				t.accept();
				if(FirstProjectUtil.Expecterror.contains(alertboxtitle))
				{
					System.out.println("Expired Pasword Verification: Passed");
				}
				else
				{
					System.out.println("Expired Pasword Verification: Failed");
				}
				
			}
			String actualtitle=driver.getTitle();
			if(FirstProjectUtil.Expectedtitle.contains(actualtitle))
			{
				System.out.println("Title Verification: Passed");
				String pageText = driver.findElement(By.tagName("tbody")).getText();		
				String[] parts = pageText.split(FirstProjectUtil.PATTERN);
				String dynamicText = parts[1];
				assertTrue(dynamicText.substring(1, 5).equals(FirstProjectUtil.FIRST_PATTERN));
				String remain = dynamicText.substring(dynamicText.length() - 4);
				assertTrue(remain.matches(FirstProjectUtil.SECOND_PATTERN));
				System.out.println("Manager Id Verification: Passed");
			}
			else
			{
				System.out.println("TitleVerification and Manager Id Verification: Failed");
			}
		}
		else
		{
			Alert a=driver.switchTo().alert();
			String alertboxtitle=a.getText();
			a.accept();
			if(FirstProjectUtil.Expecterror.contains(alertboxtitle))
			{
				System.out.println("Alert Text Message Verification: Passed");
			}
			else
			{
				System.out.println("Alert Text Message Verification: Failed");
			}
			
		}
		
	}
	
	
		
	@DataProvider(name="search")
	public Object[][] getdata()
	{
	return new Object[][]
			{
			{"mngr176222","Egugude"},
			//{"invalid","Egugude"},
			//{"mngr176222","invalid"},
			{"invalid","invalid"}
			};	
	}		

}
