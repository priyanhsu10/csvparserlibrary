package parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class CsvparserTest {

	@Test
	public void parseObject() throws IOException {
		CsvParser parser = new CsvParser();
		String s = "/Users/priyanshuparate/projects/java/Libraries/parser/students.txt";
		String data=readcsv(s);
		List<Student> students = parser.parse( data,Student.class);
		
		for (Student student : students) {
			System.out.println(student);
		}

	}

	
	@Test
	public void parseCityObject() throws IOException {
		CsvParser parser = new CsvParser();
		String s = "/Users/priyanshuparate/projects/java/Libraries/parser/cities.csv";
		String data=readcsv(s);
		List<City> cites = parser.parse( data,City.class);
		
		for (City city : cites) {
			System.out.println(city);
		}
		System.out.println(cites.size());

	}
private static String readcsv(String path) throws IOException {
		
		return Files.readAllLines(Path.of(path)).stream().collect(Collectors.joining("\n"));
	}
	
}

