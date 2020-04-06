package com.windea.study.designpattern.responsibilitychain;

public abstract class Approver {
    protected String name;
    protected Approver approver;

    public Approver(String name) {
        this.name = name;
    }

    public void setApprover(Approver approver) {
        this.approver = approver;
    }

    public abstract void processRequest(PurchaseRequest request);
}
