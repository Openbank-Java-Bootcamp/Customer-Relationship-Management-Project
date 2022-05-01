package Classes;

import java.util.*;

public class CRM {
    List<Lead> leadList = new ArrayList<>();

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
        System.out.println("Operation successful, Lead is created and added to the Lead's list" +
                "\nPlease type another command");
    }

    public void showLead() {
        for (int i = 0; i < leadList.size(); i++) {
            System.out.println(leadList.get(i).toString());
        }
    }


}
