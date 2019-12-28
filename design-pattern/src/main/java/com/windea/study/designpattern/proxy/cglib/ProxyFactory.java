package com.windea.study.designpattern.proxy.cglib;

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

public class ProxyFactory implements MethodInterceptor {
	private Object target;

	public ProxyFactory(Object target) {
		this.target = target;
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("cglib代理开始");
		var result = method.invoke(target, args);
		System.out.println("cglib代理结束");
		return result;
	}

	public Object getProxyInstance() {
		//创建一个工具类
		var enhancer = new Enhancer();
		//设置父类
		enhancer.setSuperclass(target.getClass());
		//设置回调
		enhancer.setCallback(this);
		//创建子类，即代理对象
		return enhancer.create();
	}
}
