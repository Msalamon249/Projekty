import java.util.Objects;

public class Contact implements Comparable<Contact> {
    private String name;
    private String number;

    public Contact(String name, String telephone) {
        this.name = name;
        this.number = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return number;
    }

    public void setTelephone(String telephone) {
        this.number = telephone;
    }

    @Override
    public int compareTo(Contact c) {
        return this.name.compareTo(c.name);
    }

    @Override
    public String toString() {
        return name + " - " + number;
    }

    public String toCSV() {
        return name + ";" + number;
    }

}
