package Classes;

import Enums.Product;
import Enums.Status;

import java.util.concurrent.atomic.AtomicInteger;

public class Opportunity {
    private String id;
    private Product product;
    private Contact decisionMaker;
    private Status status;
    private static AtomicInteger opportunityIdCounter = new AtomicInteger();



    //CONSTRUCTOR
    public Opportunity(Product product, Contact decisionMaker, Status status) {
        id = createID();
        this.product = product;
        this.decisionMaker = decisionMaker;
        this.status = status;
        setStatus(Status.OPEN);
    }



    //SETTERS

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setDecisionMaker(Contact decisionMaker) {
        this.decisionMaker = decisionMaker;
    }

    public void setStatus(Status status) {
        this.status = status;
    }



    //GETTERS
    public String getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Contact getDecisionMaker() {
        return decisionMaker;
    }

    public Status getStatus() {
        return status;
    }



    //METHODS
    public static String createID() {
        return String.valueOf(opportunityIdCounter.getAndIncrement() + 1);
    }


    @Override
    public String toString() {
        return "Opportunity " + id +
                "\nProduct:  " + product +
                "\nDecision Maker:  " + decisionMaker +
                "\nStatus:  " + status;
    }
}
