package Classes;

import Enums.Industry;
import Enums.Product;
import Enums.Status;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CRM {



    public Map<String,Lead> leadList = new HashMap<>();

    private List<Contact> contactList = new ArrayList<>();
    private Map<String,Opportunity> opportunityList = new HashMap<>();
    private Map<String,Account> accountList = new HashMap<>();


    private static String menuOptions = "\n\nEnter NEW LEAD to create a new Lead.\n" +
            "Enter SHOW LEADS to see all Leads.\n" +
            "Enter LOOKUP LEAD along with the Lead ID to see a particular lead.\n" +
            "Enter CONVERT along with the Lead ID to convert a lead to an opportunity.\n" +
            "Enter CLOSE-WON along with the Opportunity ID to close an won Opportunity.\n" +
            "Enter CLOSE-LOST along with the Opportunity ID to close a lost Opportunity.\n" +
            "Enter EXIT to exit.\n";

    public CRM() {
    }

    public void verifyName(String name) {
        String regx = "[a-zA-Z]+\\.?";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        if(!matcher.find()) {
            throw new IllegalArgumentException("Only letters and spaces allowed");
        }
    }

    public void verifyPhone(String phone) {
        String regx = "^[0-9]{9}$";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(phone);
        if(!matcher.find()) {
            throw new IllegalArgumentException("Phone number must be 9 digits.");
        }
    }

    public void verifyEmail(String email) {
        String regx = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.find()) {
            throw new IllegalArgumentException("Not a valid email address.");
        }
    }

    public void verifyCompany(String company) {
        String regx = ".*\\S.*";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(company);
        if(!matcher.find()) {
            throw new IllegalArgumentException("Invalid input");
        }
    }

    public void createLead(Scanner scanner) {
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

    public Lead lookupLead(String userChoice) {
        String lead_id = null;
        try {
            lead_id = userChoice.split(" ")[2];
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            System.out.println(leadList.get(lead_id).toString());
        } catch (NullPointerException e) {
            System.err.println("Not a valid Lead Id.");
        }
        return leadList.get(lead_id);
    }

    public Contact createContact(Lead lead){
        String contactName = lead.getName();
        int contactNumber = lead.getPhoneNumber();
        String contactEmail = lead.getEmail();
        Contact newContact = new Contact(contactName, contactNumber, contactEmail);
        contactList.add(newContact);
        leadList.remove(lead.getId());
        return newContact;
    }

    public void menu(Scanner scanner) {
        System.out.println(menuOptions);
        String userChoice = scanner.nextLine().toUpperCase();

        while (!userChoice.equals("QUIT")) {
            if (userChoice.contains("NEW LEAD")) {  //works
                createLead(scanner);
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
            } else if (userChoice.contains("SHOW LEADS")) {  //works
                showLeads();
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
            } else if (userChoice.contains("LOOKUP LEAD")) {  //works
                lookupLead(userChoice);
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
            } else if (userChoice.contains("CONVERT")) {  //works
                String lead_id = userChoice.split(" ")[1];
                convertLead(scanner, lead_id);
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
            } else if (userChoice.contains("CLOSE-WON")) { //did not try yet
                String closeWonId = userChoice.split(" ")[1];
                closeOpportunity(closeWonId, Status.CLOSED_WON);
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
            } else if (userChoice.contains("CLOSE-LOST")) { //did not try
                String closeLostId = userChoice.split(" ")[1];
                closeOpportunity(closeLostId, Status.CLOSED_LOST);
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
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
        System.out.println("New Opportunity created:\n" + newOpportunity.toString());
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
