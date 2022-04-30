package Classes;

import java.util.concurrent.atomic.AtomicInteger;

public class Contact {
    private String id;
    private static AtomicInteger contactIdCounter = new AtomicInteger();



    //Lead's name, phone Number, email should be added to the constructor
    //CONSTRUCTOR
    public Contact(){
        id = createID();
    }




    //GETTERS
    public String getId() {
        return id;
    }




    //METHODS
    public static String createID() {
        return String.valueOf(contactIdCounter.getAndIncrement() + 1);
    }




}
