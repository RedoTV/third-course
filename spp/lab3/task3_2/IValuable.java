package lab3.task3_2;

public interface IValuable {
    double calculateValue();
    String getValueContext();

    default String getFinancialStatement() {
        return "Объект расчета: \"" + getValueContext() + "\" "
            + "| Итоговая стоимость: " + 
        String.format("%.2f", calculateValue()) + " руб.";
    }
}