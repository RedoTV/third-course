package lab3.task3_2;

import java.util.Scanner;

public enum JobTitle {
    POSTMAN("Почтальон"),
    OPERATOR("Оператор связи"),
    SORTER("Сортировщик"),
    BRANCH_MANAGER("Начальник отделения"),
    DRIVER("Водитель-курьер");

    private final String displayName;

    JobTitle(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


    public static JobTitle fillFromConsole(Scanner scanner) {
        while (true) {
            System.out.println("Выберите должность сотрудника:");
            
            int i = 1;
            for (JobTitle job : JobTitle.values()) {
                System.out.println(i++ + ". " + job.getDisplayName());
            }

            System.out.print("Введите номер должности: ");
            String input = scanner.nextLine();

            int choice = Integer.parseInt(input);
            if (choice >= 1 && choice <= JobTitle.values().length) {
                return JobTitle.values()[choice - 1];
            } else {
                System.out.println("Ошибка: номер вне диапазона. Попробуйте еще раз.");
            }
        }
    }
}
