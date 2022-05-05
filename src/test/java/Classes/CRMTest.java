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
    private Lead emptyLead;
    private CRM testCrm;
    private Contact contact1;


    @BeforeEach
    public void setUp() {
        testCrm = new CRM();
        lead1 = new Lead("John Smith", 123456789, "jsmith@example.com", "Company A");
        testCrm.leadList.put(lead1.getId(), lead1);
        emptyLead = new Lead();
    }

    @Test
    void lookupLead_ValidId_Works() {
        assertEquals(lead1, testCrm.lookupLead("Lookup lead 1"));
    }
    @Test
    public void lookupLead_invalidID_NoThrow() {
        assertDoesNotThrow(() -> testCrm.lookupLead("lookup lead 999"));
        assertDoesNotThrow(() -> testCrm.lookupLead("lookup lead -78"));
        assertDoesNotThrow(() -> testCrm.lookupLead("lookup lead word"));
    }
    @Test
    public void lookupLead_NoId_NoThrow() {
        assertDoesNotThrow(() -> testCrm.lookupLead("lookup lead"));
    }

    @Test
    public void createContact_ValidLead_Works() {
        String lead1ID = lead1.getId();
        contact1 = testCrm.createContact(lead1);
        //info matches
        assertEquals("John Smith", contact1.getName());
        assertEquals(123456789, contact1.getPhoneNumber());
        assertEquals("jsmith@example.com", contact1.getEmail());
        //lead deleted
        assertEquals(null, testCrm.lookupLead(lead1ID));
    }

    @Test
    public void createContact_InvalidLead_IllegalArgumentException() {
        assertDoesNotThrow(() -> testCrm.createContact(emptyLead));
    }

    //Testings of methods used in convertId
    //typeOfProduct

    @Test
    public void typeOfProduct_goodData_Works(){
        Scanner testScan = new Scanner(new StringReader("1"));
        assertEquals(Product.HYBRID, testCrm.typeOfProduct(testScan));
    }

    @Test
    public void typeOfProduct_emptyProductChoice_IllegalArgumentException(){
        Scanner testScan1 = new Scanner(new StringReader(" "));
        assertThrows(java.util.NoSuchElementException.class, () -> testCrm.typeOfProduct(testScan1));
    }

    @Test
    public void typeOfProduct_invalidProductChoice_IllegalArgumentException(){
        Scanner testScan1 = new Scanner(new StringReader("5"));
        Scanner testScan2 = new Scanner(new StringReader("-5"));
        Scanner testScan3 = new Scanner(new StringReader("0"));
        assertThrows(java.util.NoSuchElementException.class, () -> testCrm.typeOfProduct(testScan1));
        assertThrows(java.util.NoSuchElementException.class, () -> testCrm.typeOfProduct(testScan2));
        assertThrows(java.util.NoSuchElementException.class, () -> testCrm.typeOfProduct(testScan3));
    }

    @Test
    public void typeOfIndustry_goodData_Works(){
        Scanner testScan = new Scanner(new StringReader("3"));
        assertEquals(Industry.MANUFACTURING, testCrm.typeOfIndustry(testScan));
    }

    @Test
    public void typeOfIndustry_emptyProductChoice_IllegalArgumentException(){
        Scanner testScan1 = new Scanner(new StringReader(" "));
        assertThrows(java.util.NoSuchElementException.class, () -> testCrm.typeOfIndustry(testScan1));
    }

    @Test
    public void typeOfIndustry_invalidProductChoice_IllegalArgumentException(){
        Scanner testScan1 = new Scanner(new StringReader("10"));
        Scanner testScan2 = new Scanner(new StringReader("-10"));
        Scanner testScan3 = new Scanner(new StringReader("0"));
        assertThrows(java.util.NoSuchElementException.class, () -> testCrm.typeOfIndustry(testScan1));
        assertThrows(java.util.NoSuchElementException.class, () -> testCrm.typeOfIndustry(testScan2));
        assertThrows(java.util.NoSuchElementException.class, () -> testCrm.typeOfIndustry(testScan3));
    }

    @Test
    public void verifyName_Strings_NoThrow() {
        assertDoesNotThrow(()-> testCrm.verifyName("A Very Long Name With-Hyphen"));
    }

    @Test
    public void verifyName_InvalidInput_Throws() {
        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyName(""));
        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyName(" "));
        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyName("4"));
    }

    @Test
    public void verifyPhone_ValidNumber_NoThrow() {
        assertDoesNotThrow(()-> testCrm.verifyPhone("123456789"));
        assertDoesNotThrow(()-> testCrm.verifyPhone("999999999"));
    }

    @Test
    public void verifyPhone_InvalidInput_Throws() {
        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyPhone(""));
        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyPhone("9"));
        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyPhone("12345678"));
        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyPhone("12345678910"));
    }

    @Test
    public void verifyEmail_ValidEmail_Works() {
        assertDoesNotThrow(()-> testCrm.verifyEmail("fake@mail.com"));
        assertDoesNotThrow(()-> testCrm.verifyEmail("another.dummy.address@yahoo.com"));
        assertDoesNotThrow(()-> testCrm.verifyEmail("h328h48h4@school.edu"));
    }

    @Test
    public void verifyEmail_InvalidInput_Throws() {
        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyEmail("@gmail.com"));
        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyEmail("email"));
        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyEmail("email@"));
        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyEmail("4hwkej32"));
        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyEmail(""));
    }

    @Test
    public void verifyCompany_ValidInput_NoThrow() {
        assertDoesNotThrow(()-> testCrm.verifyCompany("Random Company"));
        assertDoesNotThrow(()-> testCrm.verifyCompany("3M"));
        assertDoesNotThrow(()-> testCrm.verifyCompany("Yahoo!"));
        assertDoesNotThrow(()-> testCrm.verifyCompany("SK-II"));
    }

    @Test
    public void verifyCompany_Null_Throws() {
        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyCompany(""));
        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyCompany(" "));
    }





}