package lab3.task3_1;

import java.util.Scanner;

public class Client {
    private String fullname;
    private String phone;
    private String address;

    public Client(String fullname, String phone, String address) {
        this.fullname = fullname;
        this.phone = phone;
        this.address = address;
    }
    
    public Client() {
        this.fullname = "";
        this.phone = "";
        this.address = "";
    }
    
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public void fillFromConsole(Scanner scanner) {
        System.out.print("Введите ФИО клиента: ");
        this.fullname = scanner.nextLine();
        System.out.print("Введите телефон клиента: ");
        this.phone = scanner.nextLine();
        System.out.print("Введите адрес клиент: ");
        this.address = scanner.nextLine();
    }
    
    public void output() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Client { " +
                "fullname='" + fullname + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
