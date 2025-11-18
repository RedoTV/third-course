package lab3.task3_2;

import java.util.Scanner;

public class Client extends Person {
    private String address;

    public Client(String fullname, String phone, String address) {
        super(fullname, phone);
        this.address = address;
    }
    
    public Client() {
        super();
        this.address = "";
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public void fillFromConsole(Scanner scanner) {
        super.fillFromConsole(scanner);
        System.out.print("Введите адрес клиент: ");
        this.address = scanner.nextLine();
    }

    @Override
    public String toString() {
        return super.toString() +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public String getShortDescription() {
        return "Client ";
    }
}