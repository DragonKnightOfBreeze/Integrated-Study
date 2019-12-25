package com.windea.study.designpattern.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyFactory {
	private Object target;

	public ProxyFactory(Object target) {
		this.target = target;
	}

	public Object getProxyInstance() {
		//参数说明
		//loader 目标对象使用的类加载器，使用固定方式获取
		//interfaces 目标对象实现的接口类型，使用泛型方式确认
		//h 执行目标对象的方法时，会触发的事件处理器方法
		var loader = target.getClass().getClassLoader();
		var interfaces = target.getClass().getInterfaces();
		InvocationHandler h = (proxy, method, args) -> {
			System.out.println("代理开始");
			var invoke = method.invoke(target, args);
			System.out.println("代理结束");
			return invoke;
		};

		return Proxy.newProxyInstance(loader, interfaces, h);
	}
}
