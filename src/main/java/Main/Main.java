package Main;


import Classes.CRM;
import Classes.Lead;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);









       // scanner.close();


        Lead lead1 = new Lead("John Smith", 123456789, "jsmith@example.com",
                "Company A");
        lead1.setName(null);
        lead1.setPhoneNumber(0);
        lead1.setEmail(null);


        System.out.println(lead1.toString());

    }
}
