package lab3.task3_2;

import java.util.Scanner;

public class Employee extends Person {
    
    public Employee(String lastName, String firstName, String middleName, String phone) {
        super(lastName, firstName, middleName, phone);
    }
    
    public Employee() {
        super();
    }
    
    @Override
    public String getFullName() {
        return lastName + " " + firstName + " " + middleName;
    }
    
    @Override
    public void fillFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Ввод данных сотрудника ---");
        System.out.print("Введите фамилию: ");
        this.lastName = scanner.nextLine();
        System.out.print("Введите имя: ");
        this.firstName = scanner.nextLine();
        System.out.print("Введите отчество: ");
        this.middleName = scanner.nextLine();
        System.out.print("Введите телефон: ");
        this.phone = scanner.nextLine();
    }
    
    public void createSubscription(Client client, Publication publication) {
        System.out.println("Сотрудник " + getFullName() + " оформляет подписку для клиента " + 
                         client.getFullName() + " на издание " + publication.getTitle());
    }
    
    @Override
    public String toString() {
        return "Employee{" +
                "fullName='" + getFullName() + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
