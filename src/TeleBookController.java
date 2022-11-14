import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TeleBookController {
    Scanner scanner = new Scanner(System.in);
    private TeleBook teleBook;


    public TeleBookController() {
        teleBook = CSVMenager.importData();
    }

    void mainLoop() {
        boolean choose = false;
        do {
            System.out.println("Witamy w naszej ksi¥¾ce telefonicznej");
            System.out.println("Wybierz jedn¥ z opcji");
            printOptions();
            int userChoice = chooseOption();
            switch (Options.createOptionFromInt(userChoice)) {
                case ADD_CONTACT -> {
                    addContact();
                }
                case RESEARCH_BY_NAME -> {
                    findByName();
                }
                case RESEARCH_BY_NUMBER -> {
                    findByNumber();
                }
                case DELETE -> {
                    deleteContact();
                }
                case END -> {
                    endProgram();
                    choose = true;
                }
            }
        } while (choose != true);

    }

    private void endProgram() {
        Collection<Contact> values = teleBook.getContacts().values();
        CSVMenager.exportData(values);
        scanner.close();
        System.out.println("Zapisano dane do pliku, bye bye!");
    }

    private int chooseOption() {
        int userChoice = scanner.nextInt();
        scanner.nextLine();
        return userChoice;
    }

    private void deleteContact() {
        System.out.println("Podaj nazw© kontaktu do usuni©cia: ");
        String name = scanner.nextLine();
        boolean removed = teleBook.removeContact(name);
        if (removed) {
            System.out.println("Pomy˜lnie usuni©to kontakt");
        } else {
            System.out.println("Nie ma rekordu o takiej nazwie");
        }

    }

    private void findByNumber() {
        System.out.println("Podaj fragment numeru: ");
        String name = scanner.nextLine();
        List<Contact> byNumber = teleBook.findByNumber(name);
        if (byNumber.isEmpty()) {
            System.out.println("Brak wynik¢w.");
        } else {
            System.out.println("Znalezione rekordy:");
            byNumber.forEach(System.out::println);

        }
    }

    private void findByName() {
        System.out.println("Podaj fragment nazwy: ");
        String name = scanner.nextLine();
        List<Contact> byName = teleBook.findByName(name);
        if (byName.isEmpty()) {
            System.out.println("Brak wynik¢w.");
        } else {
            System.out.println("Znalezione rekordy:");
            byName.forEach(System.out::println);
        }
    }

    private void addContact() {
        System.out.println("Podaj nazw© kontaktu: ");
        String name = scanner.nextLine();
        System.out.println("Podaj numer telefonu: ");
        String number = scanner.nextLine();
        try {
            boolean add = teleBook.add(name, number);
            if (add) {
                System.out.println("Rekord dodany.");
            } else {
                System.out.println("Nie mo¾na doda† rekordu. Wpis o takiej nazwie ju¾ istnieje.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Nazwa ani numer telefonu nie mog¥ by† puste");
        }
    }

    private void printOptions() {
        System.out.println(">>>>Opcje<<<<");
        for (Options value : Options.values()) {
            System.out.println(value);
        }
    }

}
