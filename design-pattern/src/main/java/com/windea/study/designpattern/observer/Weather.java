package com.windea.study.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

public class Weather implements Subject {
	private float temperature;
	private float pressure;
	private float humidity;
	private List<Observer> observers = new ArrayList<>();

	public float getTemperature() {
		return temperature;
	}

	public float getPressure() {
		return pressure;
	}

	public float getHumidity() {
		return humidity;
	}

	public void setData(float temperature, float pressure, float humidity) {
		this.temperature = temperature;
		this.pressure = pressure;
		this.humidity = humidity;
		notifyObservers();
	}

	@Override
	public void registerObserver(Observer observer) {
		this.observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		this.observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for(var observer : observers) {
			observer.update(this);
		}
	}
}
