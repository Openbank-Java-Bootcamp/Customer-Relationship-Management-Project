package Main;


import Classes.CRM;
import Classes.Lead;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CRM crm = new CRM();
        crm.createLead(scanner);
        crm.createLead(scanner);
        crm.createLead(scanner);
        crm.showLeads();
        crm.lookupLeadId(scanner);
        crm.lookupLeadId(scanner);








        scanner.close();
    }
}
