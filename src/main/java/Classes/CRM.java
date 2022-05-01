package Classes;

import java.util.*;

public class CRM {
    List<Lead> leadList = new ArrayList<>();
    private List<Contact> contactList = new ArrayList<>();

    public CRM() {
    }

    public void createLead(Scanner scanner) {
        System.out.println("Please type Lead's name");
        String leadsName = scanner.nextLine();
        System.out.println("Please type Lead's phone number");
        String leadsPhoneNumberAsString = scanner.nextLine();
        int leadsPhoneNumberAsInt = Integer.parseInt(leadsPhoneNumberAsString);
        System.out.println("Please type Lead's email");
        String leadsEmail = scanner.nextLine();
        System.out.println("Please type Lead's company's name");
        String leadsCompany = scanner.nextLine();
        Lead newLead = new Lead(leadsName, leadsPhoneNumberAsInt, leadsEmail, leadsCompany);
        leadList.add(newLead);
        System.out.println("Operation successful, Lead is created and added to the Lead's list" + "\nPlease type another command");
    }

    public void showLeads() {
        for (int i = 0; i < leadList.size(); i++) {
            System.out.println(leadList.get(i).toString());
        }
    }

    public void lookupLeadId(Scanner scanner){
        String inputLine = scanner.nextLine();
        String[] array = inputLine.split(" ");
        String id = array[2];
        for(int i = 0; i < leadList.size(); i++){
            if(id.equals(leadList.get(i).getId())){
                System.out.println(leadList.get(i).toString());
            }
        }
    }

    public Contact createContact(Lead lead){
        String contactName = lead.getName();
        int contactNumber = lead.getPhoneNumber();
        String contactEmail = lead.getEmail();
        Contact newContact = new Contact(contactName, contactNumber, contactEmail);
        contactList.add(newContact);
        return newContact;
    }
    




}
