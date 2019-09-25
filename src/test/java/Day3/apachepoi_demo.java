package Day3;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class apachepoi_demo {
  @Test
  public void f() throws IOException {
	  File src=new File("C:\\Users\\training_b6B.01.16\\Desktop\\testdata.xlsx");
	  FileInputStream fis=new FileInputStream(src);
	  XSSFWorkbook WB= new XSSFWorkbook(fis);
	  XSSFSheet SH=WB.getSheetAt(0);
	  System.out.println("first row number " +SH.getFirstRowNum());
	  System.out.println("last row number " +SH.getLastRowNum());
	  int rowCount=SH.getLastRowNum()-SH.getFirstRowNum();
	  for(int i=1; i<=rowCount; i++) {
	  System.out.println("the total rowcount is "+rowCount);
	  System.out.println(SH.getRow(i).getCell(0).getStringCellValue()+"\t\t\t" + SH.getRow(i).getCell(1).getStringCellValue());
	  //System.out.println(SH.getRow(1).getCell(0).getStringCellValue()+"\t\t\t" + SH.getRow(1).getCell(1).getStringCellValue());
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\training_b6B.01.16\\Desktop\\BrowerDrivers\\chromedriver.exe");
	  WebDriver driver=new ChromeDriver();
	  driver.get("http://10.232.237.143:443/TestMeApp/fetchcat.htm");
	  driver.manage().window().maximize();
	  driver.findElement(By.linkText("SignIn")).click();
	  driver.findElement(By.name("userName")).sendKeys(SH.getRow(i).getCell(0).getStringCellValue());
	  driver.findElement(By.id("password")).sendKeys(SH.getRow(i).getCell(1).getStringCellValue());
	  driver.findElement(By.name("Login")).click();
	  //driver.close();
	  
	  ExtentHtmlReporter reporter=new ExtentHtmlReporter("C:\\Users\\training_b6B.01.16\\Desktop\\mynewextentreport.html");
	  ExtentReports extent=new ExtentReports();
	  extent.attachReporter(reporter);;
	  ExtentTest logger=extent.createTest("DemoWebShop");
	  logger.log(Status.INFO, "Apache POI is used in the script");
	  logger.log(Status.PASS, "Excel data reading is done successfully");
	  logger.log(Status.FAIL, MarkupHelper.createLabel("this test case fails", ExtentColor.CYAN));
	  logger.addScreenCaptureFromPath("C:\\Users\\training_b6B.01.16\\Desktop\\download.jfif");
	  extent.flush();
	  driver.close();
	  
	/*  

	//writing into excel file

	XSSFRow header=SH.getRow(0);
	XSSFCell header2=header.createCell(2);
	header2.setCellValue("status");
	SH.getRow(1).createCell(2).setCellValue("Pass");
	SH.getRow(2).createCell(2).setCellValue("Fail");
	FileOutputStream fos=new FileOutputStream(src);
	WB.write(fos);
	
	*/
  }
}
}
