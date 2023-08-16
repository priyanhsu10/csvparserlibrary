package parser;

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

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
