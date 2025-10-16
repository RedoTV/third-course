package lab3.task3_1;

import java.util.Scanner;

public class Employee {
    private String lastName;
    private String firstName;
    private String middleName;
    private String phone;
    
    public Employee(String lastName, String firstName, String middleName, String phone) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.phone = phone;
    }
    
    public Employee() {
        this.lastName = "";
        this.firstName = "";
        this.middleName = "";
        this.phone = "";
    }
    
    public void fillFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите фамилию: ");
        this.lastName = scanner.nextLine();
        System.out.print("Введите имя: ");
        this.firstName = scanner.nextLine();
        System.out.print("Введите отчество: ");
        this.middleName = scanner.nextLine();
        System.out.print("Введите телефон: ");
        this.phone = scanner.nextLine();
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getMiddleName() {
        return middleName;
    }
    
    public String getPhone() {
        return phone;
    }
    
    @Override
    public String toString() {
        return "Employee{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}