package lab3.task3_1;

import java.util.Scanner;

public class Publication {
    private String title;
    private double price;
    private PublicationType publicationType;
    
    public Publication(String title, double price, PublicationType publicationType) {
        this.title = title;
        this.price = price;
        this.publicationType = publicationType;
    }
    
    public Publication() {
        this.title = "";
        this.price = 0.0;
        this.publicationType = null;
    }
    
    public void fillFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название издания: ");
        this.title = scanner.nextLine();
        System.out.print("Введите цену: ");
        this.price = scanner.nextDouble();
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setPublicationType(PublicationType publicationType) {
        this.publicationType = publicationType;
    }
    
    public String getTitle() {
        return title;
    }
    
    public double getPrice() {
        return price;
    }
    
    public PublicationType getPublicationType() {
        return publicationType;
    }
    
    @Override
    public String toString() {
        return "Publication{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", publicationType=" + publicationType +
                '}';
    }
}
