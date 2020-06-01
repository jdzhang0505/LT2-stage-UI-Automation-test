package nopcommerce4.LT2.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshots {
	
	
	public static String getScreenshots(WebDriver driver,String fileName) throws IOException {
		//generate随机数，使得每次的截图名称不重复
		Random random=new Random();
		int i=random.nextInt(10);
		fileName=fileName+i+".png";
		
		String fileDir=Contents.ScreenshotsPath;
		File sourceFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String imagePath=fileDir+fileName;
		FileUtils.copyFile(sourceFile, new File(imagePath));
		return imagePath;
	}

}
