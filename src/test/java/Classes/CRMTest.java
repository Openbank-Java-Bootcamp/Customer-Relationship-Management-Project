package Classes;

import Enums.Industry;
import Enums.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CRMTest {
    private Lead lead1;
    private Lead empty_lead;
    private CRM test_crm;
    private Contact contact1;


    @BeforeEach
    public void setUp() {
        test_crm = new CRM();
        lead1 = new Lead("John Smith", 123456789, "jsmith@example.com", "Company A");
        test_crm.leadList.put(lead1.getId(), lead1);
        //empty_lead = new Lead("", 0, "", "");
        empty_lead = new Lead();
    }

    @Test
    void lookupLead_ValidId_Works() {
        assertEquals(lead1, test_crm.lookupLead("Lookup lead 1"));
    }
    @Test
    void lookupLead_invalidID_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> test_crm.lookupLead("lookup lead 999"));
        assertThrows(IllegalArgumentException.class, () -> test_crm.lookupLead("lookup lead -78"));
        assertThrows(IllegalArgumentException.class, () -> test_crm.lookupLead("lookup lead word"));
    }

    @Test
    void lookupLead_NoId_ArrayIndexOutOfBounds() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> test_crm.lookupLead("lookup lead"));
    }


    //   @Test

 //   @Test

//    void createContact_goodData_Works() {
//        String lead1ID = lead1.getId();
//        contact1 = test_crm.createContact(lead1);
//        //info matches
//        assertEquals("John Smith", contact1.getName());
//        assertEquals(123456789, contact1.getPhoneNumber());
//        assertEquals("jsmith@example.com", contact1.getEmail());
//        //lead deleted
//        assertThrows(IllegalArgumentException.class, () -> test_crm.lookupLead(lead1ID));
//    }

    @Test
    void createContact_emptyLead_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class , () -> test_crm.createContact(empty_lead));
    }

    @Test
    void deleteLead_goodData_Works() {
        test_crm.deleteLead(lead1);
        assertEquals(null, lead1.getId());
        assertEquals(null, lead1.getName());
        assertEquals(0, lead1.getPhoneNumber());
        assertEquals(null, lead1.getEmail());
    }
    @Test
    void deleteLead_invalidLead_Throws() {
        assertThrows(IllegalArgumentException.class, () -> test_crm.deleteLead(empty_lead));
    }

    //Testings of methods used in convertId
    //typeOfProduct

    @Test
    public void typeOfProduct_goodData_Works(){
        Scanner testScan = new Scanner(new StringReader("1"));
        assertEquals(Product.HYBRID, test_crm.typeOfProduct(testScan));
    }

    @Test
    public void typeOfProduct_emptyProductChoice_IllegalArgumentException(){
        Scanner testScan1 = new Scanner(new StringReader(" "));
        assertThrows(java.util.NoSuchElementException.class, () -> test_crm.typeOfProduct(testScan1));
    }

    @Test
    public void typeOfProduct_invalidProductChoice_IllegalArgumentException(){
        Scanner testScan1 = new Scanner(new StringReader("5"));
        Scanner testScan2 = new Scanner(new StringReader("-5"));
        Scanner testScan3 = new Scanner(new StringReader("0"));
        assertThrows(java.util.NoSuchElementException.class, () -> test_crm.typeOfProduct(testScan1));
        assertThrows(java.util.NoSuchElementException.class, () -> test_crm.typeOfProduct(testScan2));
        assertThrows(java.util.NoSuchElementException.class, () -> test_crm.typeOfProduct(testScan3));
    }

    @Test
    public void typeOfIndustry_goodData_Works(){
        Scanner testScan = new Scanner(new StringReader("3"));
        assertEquals(Industry.MANUFACTURING, test_crm.typeOfIndustry(testScan));
    }

    @Test
    public void typeOfIndustry_emptyProductChoice_IllegalArgumentException(){
        Scanner testScan1 = new Scanner(new StringReader(" "));
        assertThrows(java.util.NoSuchElementException.class, () -> test_crm.typeOfIndustry(testScan1));
    }

    @Test
    public void typeOfIndustry_invalidProductChoice_IllegalArgumentException(){
        Scanner testScan1 = new Scanner(new StringReader("10"));
        Scanner testScan2 = new Scanner(new StringReader("-10"));
        Scanner testScan3 = new Scanner(new StringReader("0"));
        assertThrows(java.util.NoSuchElementException.class, () -> test_crm.typeOfIndustry(testScan1));
        assertThrows(java.util.NoSuchElementException.class, () -> test_crm.typeOfIndustry(testScan2));
        assertThrows(java.util.NoSuchElementException.class, () -> test_crm.typeOfIndustry(testScan3));
    }



}