package tests;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class NewAccountDDT {
	String name;
	String email;
	String phone;
	String gender;
	String password;
	String country;

	@Test
	public void NewAccountTest() {
		System.out.println("NEW RECORD: " + name + " " + email + " " + phone + " " + gender);
	}

	@Parameters
	public static List<String[]> getData() {
		String filename = "D:\\selenium\\nvhuy\\Training\\Week3\\6. Build a Complete Data-Driven Framework\\43. UserAccounts.csv";
		return utilities.CSV.get(filename);
	}

	// Constructor that passes parameters to the test method
	// Thu tu cua bien truyen vao trong ham truyen quyet dinh thu tu cua gia tri lay
	// ra
	public NewAccountDDT(String name, String email, String phone, String gender, String password, String country,
			String weeklyEmail, String monthlyEmail, String occasionalEmail) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.password = password;
		this.country = country;
	}
}
