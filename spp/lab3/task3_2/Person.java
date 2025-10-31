package lab3.task3_2;

import java.util.Scanner;

public abstract class Person {
    private String fullName;
    private String phone;

    public Person(String fullName, String phone) {
        this.fullName = fullName;
        this.phone = phone;
    }

    public Person() {
        this.fullName = "";
        this.phone = "";
    }

    public abstract String getShortDescription();

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

    public void output() {
        System.out.println(this);
    }

    public void fillFromConsole(Scanner scanner) {
        System.out.print("Введите ФИО человека: ");
        this.fullName = scanner.nextLine();
        System.out.print("Введите телефон человека: ");
        this.phone = scanner.nextLine();
    }
}