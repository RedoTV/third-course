package lab3.task3_2;

import java.time.LocalDate;
import java.util.Scanner;

public class Subscription implements IProcessableByEmployee, IConsoleFillable {
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
        this.client = null;
        this.employee = null;
        this.publication = null;
        this.startDate = null;
        this.durationMonths = 0;
    }
    
    @Override
    public void fillFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Ввод данных подписки ---");
        System.out.print("Введите количество месяцев: ");
        this.durationMonths = scanner.nextInt();
    }
    
    @Override
    public void processSubscription(Employee employee) {
        System.out.println("Подписка обработана сотрудником " + employee.getFullName());
    }
    
    @Override
    public String getProcessingDetails() {
        return "Подписка для клиента " + client.getFullName() + 
               " на издание " + publication.getTitle();
    }
    
    public double calculateTotalCost() {
        return publication.getPrice() * durationMonths;
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
    
    @Override
    public String toString() {
        return "Subscription{" +
                "client=" + client.getFullName() +
                ", employee=" + employee.getFullName() +
                ", publication=" + publication.getTitle() +
                ", startDate=" + startDate +
                ", duration=" + durationMonths + " months" +
                ", totalCost=" + calculateTotalCost() +
                '}';
    }
}
