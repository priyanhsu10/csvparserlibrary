package parser;

public class User {
//	Login email;Identifier;First name;Last name

	@Override
	public String toString() {
		return "User [email=" + email + ", identifier=" + identifier + ", firstName=" + firstName + ", lastName="
				+ lastName + "]";
	}
	@FieldName(name = "Login email")
	private String email;
	private int identifier;
	@FieldName(name = "First name")
	private String firstName;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIdentifier() {
		return identifier;
	}
	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@FieldName(name = "Last name")
	private String lastName;
}
