# CSV Parser java library

### CSV parser library is very flexible . It works with  file path and  URI seamlessly


### Separator config
Custom separator can be provided in constructor

CsvParser parser = new CsvParser(";");

Default separator is ","

### @FieldName(name ="column name form csv" )
exp :-> @FieldName(name ="First name" )

you can give @Fieldname attribute to fields. 
sometime column name like "first name" include space .
Library handle this case . You no need ot worry if you column name contain space

### Name matching is case Insensitive


If you not apply @Fieldname attribute to POJO field  then it simply try to match with field name 
and this also case Insensitive

```

public class Student {
	
	private int id;
	//--> using @FieldName attribute you can give the column name which is in csv file
	@FieldName(name = "name") 
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


## Test case shows how to use this 

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

### You can load CSV file from Url No need to download the csv file, You just provide the url to the file ,it work just seamlessly

	
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
## you can define POJO with only required fields from CSV and its just work fine
@FieldName(name ="first name" )

 you can give @fieldname attribute and sometime column name like "first name" include space . 
 library handle this case as well  no need ot worry if you column name contain space


```
public class CustomUser {
    private String identifier;
    @FieldName(name ="first name" ) 
    private String  name;

    @Override
    public String toString() {
        return "CustomUser{" +
                "identifier='" + identifier + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
    getter setters ...
 
...

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