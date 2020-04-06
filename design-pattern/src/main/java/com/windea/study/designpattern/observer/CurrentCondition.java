package com.windea.study.designpattern.observer;

public class CurrentCondition implements Observer {
    private Weather weather;

    @Override
    public void update(Subject subject) {
        if(subject instanceof Weather) {
            this.weather = (Weather) subject;
        } else {
            throw new IllegalArgumentException();
        }
        display();
    }

    public void display() {
        System.out.println("温度：" + weather.getTemperature());
        System.out.println("气压：" + weather.getPressure());
        System.out.println("湿度：" + weather.getHumidity());
    }
}
