package lab3.task3_2;

import java.util.Scanner;

public class Employee extends Person {
    private JobTitle jobTitle;

    public Employee(String fullName, String phone, JobTitle jobTitle) {
        super(fullName, phone);
        this.jobTitle = jobTitle;
    }
    
    public Employee() {
        super();
        this.jobTitle = JobTitle.POSTMAN;
    }
    
    public void fillFromConsole(Scanner scanner) {
        super.fillFromConsole(scanner);
        jobTitle = JobTitle.fillFromConsole(scanner);
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "Employee { " +
                "fullName='" + getFullName() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", jobTitle='" + jobTitle.getDisplayName() + '\'' +
                " } ";
    }

    @Override
    public String getShortDescription() {
        return "Role: Employee, jobTitle: " + jobTitle;
    }
}