package lab3.task3_1;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        
        PublicationType type1 = new PublicationType("Газета", 10.0);
        PublicationType type2 = new PublicationType("Журнал", 20.0);
        
        System.out.println(type1);
        System.out.println(type2);
        
        Employee employee = new Employee("Иванов", "Иван", "Иванович", "+375291234567");
        System.out.println(employee);
        
        Client client = new Client("Петров", "Петр", "Петрович", "+375297654321");
        System.out.println(client);
        
        Publication pub1 = new Publication("Вечерний Гомель", 15.50, type1);
        Publication pub2 = new Publication("Cosmopolitan", 25.00, type2);
        
        System.out.println(pub1);
        System.out.println(pub2);
        
        Subscription sub = new Subscription(client, employee, pub1, LocalDate.now(), 6);
        System.out.println(sub);
        
        System.out.println("Клиент: " + sub.getClient().getFirstName());
        System.out.println("Издание: " + sub.getPublication().getTitle());
        System.out.println("Месяцев: " + sub.getDurationMonths());
        
        sub.setDurationMonths(12);
        System.out.println("Новая длительность: " + sub.getDurationMonths());
        
        Client client2 = new Client();
        client2.setLastName("Сидоров");
        client2.setFirstName("Сидор");
        client2.setMiddleName("Сидорович");
        client2.setPhone("+375298888888");
        System.out.println(client2);
        
        Client client3 = new Client();
        client3.fillFromConsole();
        System.out.println(client3);
        
        PublicationType type3 = new PublicationType();
        type3.fillFromConsole();
        System.out.println(type3);
    }
}
