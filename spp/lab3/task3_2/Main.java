package lab3.task3_2;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        
        System.out.println("Типы изданий:");
        PublicationType[] types = PublicationType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println(types[i] + " - " + types[i].getPrintFormat());
        }
        
        Employee emp = new Employee("Иванов", "Иван", "Иванович", "+375291234567");
        System.out.println(emp.getFullName());
        System.out.println(emp);
        
        Client client = new Client("Петров", "Петр", "Петрович", "+375297654321");
        System.out.println(client.getFullName());
        System.out.println(client);
        
        Publication pub1 = new Publication("Вечерний Гомель", 15.50, PublicationType.NEWSPAPER);
        Publication pub2 = new Publication("Наука и жизнь", 45.00, PublicationType.JOURNAL);
        
        System.out.println(pub1);
        System.out.println(pub2);
        
        emp.createSubscription(client, pub1);
        
        Subscription sub = new Subscription(client, emp, pub1, LocalDate.now(), 6);
        System.out.println(sub);
        System.out.println("Стоимость: " + sub.calculateTotalCost());
        
        client.processSubscription(emp);
        sub.processSubscription(emp);
        
        System.out.println(client.getProcessingDetails());
        System.out.println(sub.getProcessingDetails());
        
        Person p1 = emp;
        Person p2 = client;
        System.out.println(p1.getFullName());
        System.out.println(p2.getFullName());
        
        PublicationType mag = PublicationType.MAGAZINE;
        System.out.println(mag.getDisplayName());
        System.out.println(mag.getStandardDurationDays());
        System.out.println(mag.isScientific());
        
        System.out.println("\nЗаполнение с клавиатуры через интерфейс:");
        IConsoleFillable fillableClient = new Client();
        fillableClient.fillFromConsole();
        System.out.println(fillableClient);
    }
}
