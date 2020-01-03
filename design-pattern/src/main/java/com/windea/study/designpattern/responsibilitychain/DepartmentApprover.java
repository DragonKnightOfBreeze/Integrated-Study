package com.windea.study.designpattern.responsibilitychain;

public class DepartmentApprover extends Approver {
	public DepartmentApprover(String name) {
		super(name);
	}

	@Override
	public void processRequest(PurchaseRequest request) {
		if(request.getPrice() <= 5000) {
			System.out.println(String.format("请求编号 id = %d 被部门 %s 处理了。", request.getId(), name));
		} else {
			approver.processRequest(request);
		}
	}
}
