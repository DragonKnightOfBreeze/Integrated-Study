package com.atguigu.observer.improve;

//�ӿ�, ��WeatherData ��ʵ��
public interface Subject {

	void registerObserver(Observer o);

	void removeObserver(Observer o);

	void notifyObservers();
}
