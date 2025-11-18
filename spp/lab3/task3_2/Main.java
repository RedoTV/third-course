package lab3.task3_2;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Subscription subscription = new Subscription();
        subscription.fillFromConsole(scanner);
        subscription.output();     
        
        Person p = new Person() {
            @Override
            public String getShortDescription() {
                
                return null;
            }
        };
        
        // Person client = new Employee();
        // client.fillFromConsole(scanner);
        // client.output();
        // System.out.println(client.getShortDescription());

        // PublicationType type = new PublicationType("Журнал", 0);
        // Publication magazine = new Publication("Vogue", 100.0, type);

        // Subscription subscription = new Subscription(
        //     new Client(),
        //     new Employee(),
        //     magazine,
        //     LocalDate.now(),
        //     12
        // );
       
        // System.out.println(magazine.getFinancialStatement());

        // System.out.println(subscription.getFinancialStatement());
    }
}
