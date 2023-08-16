package parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CsvparserTest {

	@Test
	public void parseObject() throws IOException {
		CsvParser parser = new CsvParser();
		String s = "/Users/priyanshuparate/projects/java/Libraries/parser/students.txt";
	
		List<Student> students = parser.parse(s, Student.class);

		for (Student student : students) {
			System.out.println(student);
		}

	}

	@Test
	public void parseCityObject() throws IOException {
		CsvParser parser = new CsvParser();
		String s = "/Users/priyanshuparate/projects/java/Libraries/parser/cities.csv";
		List<City> cites = parser.parse(s, City.class);

		for (City city : cites) {
			System.out.println(city);
		}
		System.out.println(cites.size());
		
	}
	@Test
	public void parseObjectFromUri() throws IOException {
		CsvParser parser = new CsvParser(";");
		String s ="https://support.staffbase.com/hc/en-us/article_attachments/360009197071/email.csv";
		URI p=URI.create(s);
		List<User> users = parser.parse(p, User.class);

		for (User user : users) {
			System.out.println(user);
		}

	}
	@Test
	public void parseObjectFromUriWithCustomFields() throws IOException {
		CsvParser parser = new CsvParser(";");
		String s ="https://support.staffbase.com/hc/en-us/article_attachments/360009197071/email.csv";
		URI p=URI.create(s);
		List<CustomUser> users = parser.parse(p, CustomUser.class);

		for (CustomUser user : users) {
			System.out.println(user);
		}

	}






}
