# csv Parser java library
 
## This new library is very flexible and work with file and URI

```
public class Student {
	
	private int id;
	@FieldName(name = "name") --> using this attribute you can give the column name whichi is in csv file
	private String studentName; --> you can give any custom name for field 
	@FieldName(name = "age")
	private int studentAge;
	@Override
	public String toString() {
		return "Student [id=" + id + ", studentName=" + studentName + ", studentAge=" + studentAge + "]";
	}
	public int getAge() {
		return studentAge;
	}
 .... getters setters


```


## Test case show how to use this 

``` 
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
	
```
## You can load CSV from Url No need to download the csv file , Just provide the url to fil it work just seamlessly
	
```
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

```
## you can define POJO with only required field from CSV and its just work fine
```

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
``