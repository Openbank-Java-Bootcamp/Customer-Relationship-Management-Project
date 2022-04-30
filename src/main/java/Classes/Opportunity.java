package Classes;

import Enums.Product;
import Enums.Status;

public class Opportunity {
    private String id;
    private Product product;
    private Contact decisionMaker;
    private Status status;



    //CONSTRUCTOR
    public Opportunity(String id, Product product, Contact decisionMaker, Status status) {
        this.id = id;
        this.product = product;
        this.decisionMaker = decisionMaker;
        this.status = status;
        setStatus(Status.OPEN);
    }



    //SETTERS
    public void setId(String id) {
        this.id = id;
    }

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




}
