package Classes;

import java.util.*;

public class CRM {
    public Map<String, Lead> leadList = new HashMap();
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


    public void createLead2(Scanner scanner) {
        String leadName = null;
        String leadPhone = null;
        String leadEmail = null;
        String leadCompany = null;
        while (leadName == null) {
            try {
                System.out.println("Please type Lead's name");
                leadName = scanner.nextLine();
                verifyName(leadName);
            } catch (IllegalArgumentException e) {
                leadName = null;
                System.err.println("Only letters and spaces allowed");
            }
        }
        while (leadPhone == null) {
            try {
                System.out.println("Please type Lead's phone number");
                leadPhone = scanner.nextLine();
                verifyPhone(leadPhone);
            } catch (IllegalArgumentException e) {
                leadPhone = null;
                System.err.println("Phone number must be 9 digits.");
            }
        }
        int leadPhoneAsInt = Integer.parseInt(leadPhone);
        while (leadEmail == null) {
            try {
                System.out.println("Please type Lead's email");
                leadEmail = scanner.nextLine();
                verifyEmail(leadEmail);
            } catch (IllegalArgumentException e) {
                leadEmail = null;
                System.err.println("Not a valid email address.");
            }
        }
        while (leadCompany == null) {
            try {
                System.out.println("Please type Lead's company");
                leadCompany = scanner.nextLine();
                verifyCompany(leadCompany);
            } catch (IllegalArgumentException e) {
                leadCompany = null;
                System.err.println("Invalid input");
            }
        }
        Lead newLead = new Lead(leadName, leadPhoneAsInt, leadEmail, leadCompany);
        leadList.put(newLead.getId(), newLead);
        System.out.println("\n\nLead created:");
        System.out.println(newLead.toString());
    }




    public void showLeads() {


            if (leadList.size() < 1) {
                System.out.println("\n\nNo leads to show\n\n");
            } else {
                for (Map.Entry<String, Lead> entry : leadList.entrySet()) {
                    System.out.println(entry.getValue());
                }
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
