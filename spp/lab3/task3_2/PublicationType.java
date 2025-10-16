package lab3.task3_2;

public enum PublicationType implements IPrintable {
    NEWSPAPER("Газета", 30),
    MAGAZINE("Журнал", 60),
    JOURNAL("Научный журнал", 90),
    BULLETIN("Бюллетень", 14);
    
    private final String displayName;
    private final int standardDurationDays;
    
    PublicationType(String displayName, int standardDurationDays) {
        this.displayName = displayName;
        this.standardDurationDays = standardDurationDays;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public int getStandardDurationDays() {
        return standardDurationDays;
    }
    
    @Override
    public String getPrintFormat() {
        return displayName + " (выпуск каждые " + standardDurationDays + " дней)";
    }
    
    public boolean isScientific() {
        return this == JOURNAL;
    }
}
