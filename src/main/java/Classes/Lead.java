package Classes;

import java.util.concurrent.atomic.AtomicInteger;



public class Lead {
    private String id;
    private String name;
    private int phoneNumber;
    private String email;
    private String companyName;
    private static AtomicInteger leadIdCounter = new AtomicInteger();





    //CONSTRUCTOR
    public Lead(String name, int phoneNumber, String email, String companyName) {
        id = createId();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
    }

    // only used to create a null Lead for testing purposes
    public Lead() {}




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

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }





    //GETTERS
    public String getId() { return id; }

    public String getName() {
        return name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCompanyName() {
        return companyName;
    }




    //METHODS
    public static String createId() {
        return String.valueOf(leadIdCounter.getAndIncrement() + 1);
    }

    public void setIdToNull() {
        this.id = null;
    }

    @Override
    public String toString() {
        return "\nLead " + id + " has these details:" +
                "\nName: " + name +
                "\nPhone Number: " + phoneNumber +
                "\nEmail: " + email +
                "\nCompany Name: " + companyName + "\n\n";
    }
}
