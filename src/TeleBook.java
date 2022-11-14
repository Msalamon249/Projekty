import java.io.*;
import java.util.*;


public class TeleBook {
    private Map<String, Contact> contacts = new TreeMap<>();



    public TeleBook() {

    }


    public TeleBook(Map<String, Contact> contacts) {
        this.contacts = contacts;
    }


    public Map<String, Contact> getContacts() {
        return contacts;
    }

    public List<Contact> findByName(String name) {
        List<Contact> foundByName = new ArrayList<>();
        for (var result : contacts.entrySet()) {
            if (result.getKey().contains(name)) {
                foundByName.add(result.getValue());
            }
        }
        return foundByName;
    }

    public List<Contact> findByNumber(String number) {
        List<Contact> foundNumberList = new ArrayList<>();
        for (var result : contacts.values()) {
            if (result.getTelephone().contains(number)) {
                foundNumberList.add(result);
            }
        }
        return foundNumberList;
    }

    public boolean removeContact(String name) {
        return contacts.remove(name) != null;

    }


    public boolean add(String name, String telephone) {


        if (name == null || telephone == null)
            throw new NullPointerException("name and telephone cannot be null");
        if (name.isEmpty() || telephone.isEmpty())
            throw new IllegalArgumentException("name and telephone cannot be empty");
        if (!contacts.containsKey(name)) {
            Contact contact = new Contact(name, telephone);
            contacts.put(name, contact);
            return true;
        } else {
            return false;
        }


    }


}
