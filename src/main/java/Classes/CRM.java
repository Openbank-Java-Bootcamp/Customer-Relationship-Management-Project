package Classes;

import java.util.*;

public class CRM {
    public Map<String,Lead> leadList = new HashMap();
    private List<Contact> contactList = new ArrayList<>();

    private static String menuOptions = "Enter NEW LEAD to create a new Lead.\n" +
            "Enter SHOW LEADS to see all Leads.\n" +
            "Enter LOOKUP LEAD along with the Lead ID to see a particular lead.\n" +
            "Enter CONVERT along with the Lead ID to convert a lead to an opportunity.\n" +
            "Enter CLOSE-WON along with the Opportunity ID to close an won Opportunity.\n" +
            "Enter CLOSE-LOST along with the Opportunity ID to close a lost Opportunity.\n" +
            "Enter EXIT to exit.\n";

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
        //leadList.add(newLead);
        leadList.put(newLead.getId(), newLead);
        //System.out.println("Operation successful, Lead is created and added to the Lead's list" + "\nPlease type another command");
        System.out.println(newLead.toString());
    }

    public void showLeads() {
        for (int i = 0; i < leadList.size(); i++) {
            System.out.println(leadList.get(i).toString());
        }
    }

    public Lead lookupLead(String lead_id) {
        if (leadList.get(lead_id) == null) {
            throw new IllegalArgumentException("Not a valid Lead ID");
        }
        return leadList.get(lead_id);
    }

    public Contact createContact(Lead lead){
        if (lead.getName() == null || lead.getPhoneNumber() == 0 || lead.getEmail() == null) {
            throw new IllegalArgumentException("Not a valid Lead");
        }
        String contactName = lead.getName();
        int contactNumber = lead.getPhoneNumber();
        String contactEmail = lead.getEmail();
        Contact newContact = new Contact(contactName, contactNumber, contactEmail);
        contactList.add(newContact);
        //drop Lead from leadList
        leadList.remove(lead.getId());
        //delete Lead
        deleteLead(lead);
        return newContact;
    }

    //can't delete a Lead object, but can set all info to null
    public void deleteLead(Lead lead) {
        if (lead.getName() == null) {
            throw new IllegalArgumentException("Not a valid Lead.");
        }
        lead.setIdToNull();
        lead.setName(null);
        lead.setPhoneNumber(0);
        lead.setEmail(null);
        lead.setCompanyName(null);
    }

    public void menu(Scanner scanner) throws Exception {
        System.out.println(menuOptions);
        String userChoice = scanner.nextLine().toUpperCase();

        while (!userChoice.equals("QUIT")) {
            if (userChoice.contains("NEW LEAD")) {  //works
                createLead(scanner);
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
            } else if (userChoice.contains("SHOW LEADS")) {  //did not try yet
                showLeads();
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
            } else if (userChoice.contains("LOOKUP LEAD")) {  //works
                String lead_id = userChoice.split(" ")[2];
                Lead lead = lookupLead(lead_id);
                System.out.println(lead.toString());
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
            } else if (userChoice.contains("CONVERT")) {  //did not try yet
                String lead_id = userChoice.split(" ")[1];
                Lead lead = leadList.get(lead_id);
                createContact(lead);
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
            } else if (userChoice.contains("CLOSE-WON")) { //did not try yet
                //method to close opportunity and change status to won
            } else if (userChoice.contains("CLOSE-LOST")) { //did not try yet
                //method to close opportunity and change status to lost
            } else if (userChoice.equals("EXIT")) {
                break;
            } else {
                System.err.println("Invalid Entry\n");
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
            }
        }
    }
    




}
