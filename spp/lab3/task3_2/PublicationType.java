package lab3.task3_2;

import java.util.Scanner;

public class PublicationType {
    private String name;
    private double cost;
    
    public PublicationType(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
    
    public PublicationType() {
        this.name = "";
        this.cost = 0.0;
    }
    
    public void fillFromConsole(Scanner scanner) {
        System.out.print("Введите название типа издания: ");
        this.name = scanner.nextLine();
        System.out.print("Введите стоимость: ");
        this.cost = scanner.nextDouble();
        scanner.nextLine();
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setCost(double cost) {
        this.cost = cost;
    }
    
    public String getName() {
        return name;
    }
    
    public double getCost() {
        return cost;
    }
    
    public void output() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "PublicationType { " +
                    "name='" + name + '\'' + ", cost=" + cost + 
                " } ";
    }
}