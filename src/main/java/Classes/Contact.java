package Classes;

import java.util.concurrent.atomic.AtomicInteger;

public class Contact {
    private String id;
    private String name;
    private int phoneNumber;
    private String email;
    private static AtomicInteger contactIdCounter = new AtomicInteger();




    public Contact(String name, int phoneNumber, String email){
        id = createID();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }




    //GETTERS
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }



    //SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }




    //METHODS
    public static String createID() {
        return String.valueOf(contactIdCounter.getAndIncrement() + 1);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                '}';
    }
}
