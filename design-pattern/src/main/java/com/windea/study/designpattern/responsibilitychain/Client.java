package com.windea.study.designpattern.responsibilitychain;

public class Client {
    public static void main(String[] args) {
        //可以考虑使用双向链表进行优化

        var request = new PurchaseRequest(1, 1, 1000);

        var departmentApprover = new DepartmentApprover("张主任");
        var collegeApprover = new CollageApprover("李院长");
        var vicePresentApprover = new VicePresentApprover("王副校长");
        var presentApprover = new PresentApprover("董校长");

        departmentApprover.setApprover(collegeApprover);
        collegeApprover.setApprover(vicePresentApprover);
        vicePresentApprover.setApprover(presentApprover);
        presentApprover.setApprover(departmentApprover);

        departmentApprover.processRequest(request);
    }
}
