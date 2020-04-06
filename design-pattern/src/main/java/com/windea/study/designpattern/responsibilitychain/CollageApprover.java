package com.windea.study.designpattern.responsibilitychain;

public class CollageApprover extends Approver {
    public CollageApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if(request.getPrice() > 5000 && request.getPrice() <= 10000) {
            System.out.println(String.format("请求编号 id = %d 被学院 %s 处理了。", request.getId(), name));
        } else {
            approver.processRequest(request);
        }
    }
}
