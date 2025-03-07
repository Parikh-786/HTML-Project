package seleniumProject_Flipkart;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MyProjectclass {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		Thread.sleep(3000);
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//header/div[2]/div[2]/div[1]/div[1]/div[1]")).click();
		Thread.sleep(3000);
		WebElement electronics = driver.findElement(By.xpath("//body/div[@id='container']/div[1]/div[2]/div[1]/div[1]/span[1]"));
		Actions actions = new Actions(driver);
		actions.clickAndHold(electronics).build().perform();	
		Thread.sleep(3000);
		driver.findElement(By.linkText("Mobiles")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//body/div[@id='container']/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[1]/div[1]/input[1]")).sendKeys("Mobiles");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//body/div[@id='container']/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[1]/button[1]/*[1]")).click();
		Thread.sleep(3000);
		List <WebElement> products =  driver.findElements(By.xpath("//body/div[@id='container']/div[1]/div[3]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]"));
	
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet  = workbook.createSheet("Phones");
		int rowNum = 0;
		List<WebElement> name = List.of();
		List<WebElement> price = List.of();
		
		Row row = null;
		
		for(WebElement product: products) {
			
			name = product.findElements(By.xpath("//div[@class=\"KzDlHZ\"]"));
			price = product.findElements(By.xpath("//div[@class=\"Nx9bqj _4b5DiR\"]"));
			
		}
		for(int i = 0;i<name.size();i++) {
			String item = name.get(i).getText();
			String pp = price.get(i).getText();
			
			row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(item);
			row.createCell(1).setCellValue(pp);
			
			System.out.println(item +" " +pp);
		}
		FileOutputStream  os = new FileOutputStream("Phones.xlsx");
		workbook.write(os);
		Thread.sleep(3000);
		
		driver.close();
	}

}
