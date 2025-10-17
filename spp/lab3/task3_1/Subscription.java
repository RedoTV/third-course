package lab3.task3_1;

import java.time.LocalDate;
import java.util.Scanner;

public class Subscription {
    private Client client;
    private Employee employee;
    private Publication publication;
    private LocalDate startDate;
    private int durationMonths;
    
    public Subscription(Client client, Employee employee, Publication publication, 
                       LocalDate startDate, int durationMonths) {
        this.client = client;
        this.employee = employee;
        this.publication = publication;
        this.startDate = startDate;
        this.durationMonths = durationMonths;
    }
    
    public Subscription() {
        this.client = new Client();
        this.employee = new Employee();
        this.publication = new Publication();
        this.startDate = LocalDate.now();
        this.durationMonths = 0;
    }
    
    public void fillFromConsole(Scanner scanner) {
        client.fillFromConsole(scanner);
        employee.fillFromConsole(scanner);
        publication.fillFromConsole(scanner);

        System.out.print("Введите срок подписки (месяцев): ");
        this.durationMonths = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите дату начала подписки (yyyy-mm-dd): ");
        String date = scanner.nextLine();
        startDate = LocalDate.parse(date);
    }
    
    public void setClient(Client client) {
        this.client = client;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public void setPublication(Publication publication) {
        this.publication = publication;
    }
    
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    public void setDurationMonths(int durationMonths) {
        this.durationMonths = durationMonths;
    }
    
    public Client getClient() {
        return client;
    }
    
    public Employee getEmployee() {
        return employee;
    }
    
    public Publication getPublication() {
        return publication;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public int getDurationMonths() {
        return durationMonths;
    }

    public void output() {
        System.out.println(this);
    }
    
    @Override
    public String toString() {
        return "Subscription {\n  " +
                client + ",\n  " +
                employee + ",\n  " +
                publication + ",\n" +
                "  startDate=" + startDate + ", " +
                "  durationMonths=" + durationMonths + "\n" +
                " } ";
    }
}