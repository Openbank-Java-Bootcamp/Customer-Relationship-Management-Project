package Classes;

import java.util.*;

public class CRM {
    public Map<String,Lead> leadList = new HashMap();
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
        //leadList.add(newLead);
        leadList.put(newLead.getId(), newLead);
        System.out.println("Operation successful, Lead is created and added to the Lead's list" + "\nPlease type another command");
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
    




}
