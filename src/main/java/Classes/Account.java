package Classes;

import Enums.Industry;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Account {
    private String id;
    private Industry industry;
    private int employeeCount;
    private String city;
    private String country;
    private Map<String, Contact> contactList;
    private Map<String, Opportunity> opportunityList;
    private static AtomicInteger accountIdCounter = new AtomicInteger();
    // Should Account have Lead's company name and be added to constructor?




    //CONSTRUCTOR
    public Account(Industry industry, int employeeCount, String city, String country, Map<String,Contact> contactList, Map<String, Opportunity> opportunityList) {
        this.id = createID();
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
        this.contactList = contactList;
        this.opportunityList = opportunityList;
    }



    //SETTERS
    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setContactList(Map<String, Contact> contactList) {
        this.contactList = contactList;
    }

    public void setOpportunityList(Map<String, Opportunity> opportunityList) {
        this.opportunityList = opportunityList;
    }




    //GETTERS
    public String getId() {
        return id;
    }

    public Industry getIndustry() {
        return industry;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Map<String, Contact> getContactList() {
        return contactList;
    }

    public Map<String, Opportunity> getOpportunityList() {
        return opportunityList;
    }



    //METHODS
    public static String createID() {
        return String.valueOf(accountIdCounter.getAndIncrement() + 1);
    }

    @Override
    public String toString() {
        return "Account: " + id +
                "Industry:  " + industry +
                "Employee Count:  " + employeeCount +
                "City: " + city +
                "Country:  " + country +
                "Contact List" + contactList +
                "Opportunity List" + opportunityList;
    }
}
