package parser;

public class Student {
	
	private int id;
	@FieldName(name = "name")
	private String studentName;
	@FieldName(name = "age")
	private int studentAge;

	public int getAge() {
		return studentAge;
	}

	public void setAge(int age) {
		this.studentAge = age;
	}

	public String getName() {
		return studentName;
	}

	public void setName(String name) {
		this.studentName = name;
	}



	@Override
	public String toString() {
		return "Student [id=" + id + ", studentName=" + studentName + ", studentAge=" + studentAge + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}

