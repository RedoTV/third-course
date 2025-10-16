package lab3.task3_2;

import java.util.Scanner;

public class Client extends Person implements IProcessableByEmployee {
    
    public Client(String lastName, String firstName, String middleName, String phone) {
        super(lastName, firstName, middleName, phone);
    }
    
    public Client() {
        super();
    }
    
    @Override
    public String getFullName() {
        return lastName + " " + firstName + " " + middleName;
    }
    
    @Override
    public void fillFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Ввод данных клиента ---");
        System.out.print("Введите фамилию: ");
        this.lastName = scanner.nextLine();
        System.out.print("Введите имя: ");
        this.firstName = scanner.nextLine();
        System.out.print("Введите отчество: ");
        this.middleName = scanner.nextLine();
        System.out.print("Введите телефон: ");
        this.phone = scanner.nextLine();
    }
    
    @Override
    public void processSubscription(Employee employee) {
        System.out.println("Клиент " + getFullName() + " обработан сотрудником " + 
                         employee.getFullName());
    }
    
    @Override
    public String getProcessingDetails() {
        return "Клиент: " + getFullName();
    }
    
    @Override
    public String toString() {
        return "Client{" +
                "fullName='" + getFullName() + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
