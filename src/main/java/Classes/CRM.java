package Classes;

import Enums.Industry;
import Enums.Product;
import Enums.Status;

import java.util.*;

public class CRM {
    public Map<String,Lead> leadList = new HashMap();
    private List<Contact> contactList = new ArrayList<>();
    private Map<String,Opportunity> opportunityList = new HashMap<>();
    private Map<String,Account> accountList = new HashMap<>();


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
                convertLead(scanner, lead_id);
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
            } else if (userChoice.contains("CLOSE-WON")) { //did not try yet
                String closeWonId = userChoice.split(" ")[1];
                closeOpportunity(closeWonId, Status.CLOSED_WON);
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
                //method to close opportunity and change status to won
            } else if (userChoice.contains("CLOSE-LOST")) { //did not try
                String closeLostId = userChoice.split(" ")[1];
                closeOpportunity(closeLostId, Status.CLOSED_LOST);
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
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



    public void convertLead(Scanner scanner, String id){
        Contact newContact = createContact(leadList.get(id));
        Product productType = typeOfProduct(scanner);
        Opportunity newOpportunity = createOpportunity(productType, newContact);
        createAccount(scanner, newContact, newOpportunity);
    }

    public Product typeOfProduct(Scanner scanner){
        System.out.println(
                "Enter (1) for the product: Hybrid" +
                        "\nEnter (2) for the product: Flatbed" +
                        "\nEnter (3) for the product: Box"
        );
        int productChoice = CRM.verifyIntInput(scanner, 1, 3);
        switch(productChoice){
            case 1:
                return Product.HYBRID;
            case 2:
                return Product.FLATBED;
            default:
                return Product.BOX;
        }
    }
    public Industry typeOfIndustry(Scanner scanner){
        System.out.println(
                "Enter (1) for the industry: produce" +
                        "\nEnter (2) for the product: ecommerce" +
                        "\nEnter (3) for the product: manufacturing"+
                        "\nEnter (4) for the product: medical"+
                        "\nEnter (5) for the product: other"
        );
        int productChoice = CRM.verifyIntInput(scanner, 1, 5);
        switch(productChoice){
            case 1:
                return Industry.PRODUCE;
            case 2:
                return Industry.ECOMMERCE;
            case 3:
                return Industry.MANUFACTURING;
            case 4:
                return Industry.MEDICAL;
            default:
                return Industry.OTHER;
        }
    }
    public Opportunity createOpportunity(Product productType, Contact newContact){
        Opportunity newOpportunity = new Opportunity(productType, newContact, Status.OPEN);
        opportunityList.put(newOpportunity.getId(), newOpportunity);
        return newOpportunity;
    }
    public void createAccount(Scanner scanner, Contact newContact, Opportunity newOpportunity){
        Industry industryType = typeOfIndustry(scanner);
        System.out.println("Please type the number of employees");
        int employeeCount = CRM.verifyIntInput(scanner, 1, Integer.MAX_VALUE);
        System.out.println("Please type Account city");
        String city = scanner.nextLine();
        System.out.println("Please type Account country");
        String country = scanner.nextLine();
        List<Contact> newContactList = Arrays.asList(newContact);
        List<Opportunity> newOpportunityList = Arrays.asList(newOpportunity);
        Account newAccount = new Account(industryType, employeeCount, city, country, newContactList, newOpportunityList);
        accountList.put(newAccount.getId(),newAccount);
    }

    public void closeOpportunity(String id, Status status){
        if (opportunityList.get(id) == null) {
            throw new IllegalArgumentException("Not a valid Opportunity ID");
        }
        opportunityList.get(id).setStatus(status);

    }
    public static int verifyIntInput(Scanner scanner, int min, int max) {
        boolean flag;
        int num = -1;
        String input;
        do { input = scanner.next();
            try {
                Integer.parseInt(input);
                flag = false;
            } catch (NumberFormatException e) {
                System.err.println("Enter a number " + min + "-" + max);
                flag = true;
            }
            if (!flag) {
                num = Integer.parseInt(input);
                if (num > max || num < min) {
                    System.err.println("Enter a number " + min + "-" + max);
                    flag = true;
                }
            }
        } while (flag);
        return num;
    }

}
