package com.windea.study.designpattern.responsibilitychain;

public class PresentApprover extends Approver {
	public PresentApprover(String name) {
		super(name);
	}

	@Override
	public void processRequest(PurchaseRequest request) {
		if(request.getPrice() > 30000) {
			System.out.println(String.format("请求编号 id = %d 被校长 %s 处理了。", request.getId(), name));
		} else {

			approver.processRequest(request);
		}
	}
}
