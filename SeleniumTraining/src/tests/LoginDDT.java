package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginDDT {
	@Test(dataProvider = "getData")
	public void loginTest(String name, String email, String password) {
		System.out.println("RECORD: " + name + " " + email + " " + password + "\n");
	}
	
	@DataProvider
	public String[][] getData() {
		String filename = "D:\\selenium\\nvhuy\\Training\\Week3\\6. Build a Complete Data-Driven Framework\\45. UserLogin.xls";
		return utilities.Excel.get(filename);
	}
}
