package lab3.task3_1;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        //это писал не я
        Subscription subscription = new Subscription();
        // System.out.println(subscription.getClient().getFullname().length());
        subscription.fillFromConsole(scanner);
        subscription.output();
        // System.out.println(subscription.toString());

        //а это уже я
        
        

        // PublicationType type1 = new PublicationType("Газета", 10.0);
        // PublicationType type2 = new PublicationType("Журнал", 20.0);
        // type1.output();
        // type2.output();
        
        // Employee employee = new Employee("Иванов Иван Иванович", "+375291234567", "Менеджер");
        // employee.output();
        
        // Client client = new Client("Петров Петр Петрович", "+375297654321", "г. Гомель, ул. Советская 1");
        // client.output();
        
        // Publication pub1 = new Publication("Вечерний Гомель", 15.50, type1);
        // Publication pub2 = new Publication("Cosmopolitan", 25.00, type2);
        // pub1.output();
        // pub2.output();
        
        // Subscription sub = new Subscription(client, employee, pub1, LocalDate.now(), 6);
        // sub.output();
        
        // System.out.println("\nГеттеры:");
        // System.out.println(sub.getClient().getFullname());
        // System.out.println(sub.getPublication().getTitle());
        // System.out.println(sub.getPublication().getPrice());
        // System.out.println(sub.getDurationMonths());
        // System.out.println(sub.getEmployee().getPhone());
        
        // System.out.println("\nСеттеры:");
        // sub.setDurationMonths(12);
        // System.out.println(sub.getDurationMonths());
        // client.setPhone("+375291111111");
        // System.out.println(client.getPhone());
        // employee.setJobTitle("Старший менеджер");
        // System.out.println(employee.getJobTitle());
        // pub1.setPrice(18.00);
        // System.out.println(pub1.getPrice());
        
        // System.out.println("\nПустые конструкторы:");
        // Client client2 = new Client();
        // client2.setFullname("Сидоров Сидор Сидорович");
        // client2.setPhone("+375298888888");
        // client2.setAddress("г. Минск, пр. Независимости 10");
        // client2.output();
        
        // Employee employee2 = new Employee();
        // employee2.setFullName("Козлова Мария Петровна");
        // employee2.setPhone("+375297777777");
        // employee2.setJobTitle("Кассир");
        // employee2.output();
        
        // System.out.println("\nfillFromConsole:");
        // System.out.println("\nКлиент:");
        // Client client3 = new Client();
        // client3.fillFromConsole(scanner);
        // client3.output();

        // System.out.println("\nСотрудник:");
        // Employee employee3 = new Employee();
        // employee3.fillFromConsole(scanner);
        // employee3.output();
        
        // System.out.println("\nТипИздания");
        // PublicationType type3 = new PublicationType();
        // type3.fillFromConsole(scanner);
        // type3.output();
        
        // System.out.println("\nИздание:");
        // Publication pub3 = new Publication();
        // pub3.fillFromConsole(scanner);
        // pub3.setPublicationType(type3);
        // pub3.output();
        
        // System.out.println("\nПодписка:");
        // Subscription sub2 = new Subscription();
        // sub2.setClient(client3);
        // sub2.setEmployee(employee3);
        // sub2.setPublication(pub3);
        // sub2.setStartDate(LocalDate.now());
        // sub2.fillFromConsole(scanner);
        // sub2.output();
        
        // System.out.println("\nПустая подписка:");
        // Subscription emptySub = new Subscription();
        // emptySub.output();
        
        scanner.close();
    }
}
