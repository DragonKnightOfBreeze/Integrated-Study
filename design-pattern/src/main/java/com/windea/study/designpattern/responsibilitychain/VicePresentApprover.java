package com.windea.study.designpattern.responsibilitychain;

public class VicePresentApprover extends Approver {
	public VicePresentApprover(String name) {
		super(name);
	}

	@Override
	public void processRequest(PurchaseRequest request) {
		if(request.getPrice() > 10000 && request.getPrice() <= 30000) {
			System.out.println(String.format("请求编号 id = %d 被副校长 %s 处理了。", request.getId(), name));
		} else {
			approver.processRequest(request);
		}
	}
}
