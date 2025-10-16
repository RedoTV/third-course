package lab3.task3_2;

public abstract class Person implements IConsoleFillable {
    protected String lastName;
    protected String firstName;
    protected String middleName;
    protected String phone;
    
    public Person(String lastName, String firstName, String middleName, String phone) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.phone = phone;
    }
    
    public Person() {
        this.lastName = "";
        this.firstName = "";
        this.middleName = "";
        this.phone = "";
    }
    
    public abstract String getFullName();
    
    public abstract void fillFromConsole();
    
    public String getContactInfo() {
        return "Телефон: " + phone;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getMiddleName() {
        return middleName;
    }
    
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
}