package lab3.task3_1;

import java.util.Scanner;

public class Employee {
    private String fullName;
    private String phone;
    private String jobTitle;

    public Employee(String fullName, String phone, String jobTitle) {
        this.fullName = fullName;
        this.phone = phone;
        this.jobTitle = jobTitle;
    }
    
    public Employee() {
        this.fullName = "";
        this.phone = "";
        this.jobTitle = "";
    }
    
    public void fillFromConsole(Scanner scanner) {
        System.out.print("Введите ФИО сотрудника: ");
        this.fullName = scanner.nextLine();
        System.out.print("Введите телефон сотрудника: ");
        this.phone = scanner.nextLine();
        System.out.print("Введите должность сотрудника: ");
        this.jobTitle = scanner.nextLine();
    }
    
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    
    public void output() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Employee { " +
                "fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                " } ";
    }
}