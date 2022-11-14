import java.io.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CSVMenager {

    private static final String fileName = "teleBook.csv";


    public static TeleBook importData() {
        TeleBook book = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            Map<String, Contact> collect = bufferedReader.lines()
                    .map(s -> s.split(";"))
                    .map(strings -> new Contact(strings[0], strings[1]))
                    .collect(Collectors.toMap(Contact::getName, Function.identity()));
            System.out.println("Wgrano dane z pliku");
            book = new TeleBook(new TreeMap<>(collect));
        } catch (IOException e) {

        }
        return book != null? book : new TeleBook();
    }


    public static void exportData(Collection<Contact> collection) {
        try (
                var bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (Contact contact : collection) {
                bufferedWriter.write(contact.toCSV());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
