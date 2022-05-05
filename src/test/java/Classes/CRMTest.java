package Classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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


}