import java.util.NoSuchElementException;

public enum Options {
    ADD_CONTACT(0, "Dodaj kontakt"),
    RESEARCH_BY_NAME(1, "Szukaj po nazwie"),
    RESEARCH_BY_NUMBER(2, "Szukaj po telefonie"),
    DELETE(3, "Usuä kontakt"),
    END(4, "Koniec");

    private String description;
    private int id;

    Options(int id, String description) {
        this.description = description;
        this.id = id;
    }

    public static Options createOptionFromInt(int userChoice) {
        if (userChoice >= values().length || userChoice < 0)
            throw new NoSuchElementException();
        return values()[userChoice];
    }

    @Override
    public String toString() {
        return id + " - " + description;
    }
}
