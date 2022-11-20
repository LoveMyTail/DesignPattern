package com.trunks.strategy.travelCase;

/**
 * @author ：Sun Yangfan
 * @date ：Created in 2022/5/3
 */
public class Client {
    public static void main(String[] args) {
        // 策略模式上DDD的实际使用上，目的在于是在业务层或者领域层中不必过分关注底层的具体实现

        // 可以额外定义一个类用来获取策略的具体实现方式

        Integer money = 100;

        TravelEngine engine = new TravelEngine();
        TravelStrategy method = engine.getTravelMethod(money);
        method.travel();

//        int money = 1000;
//        Train train = new Train();
//        Airplane airplane = new Airplane();
//        Walk walk = new Walk();
//
//
//
//
//        TravelMethod travelMethod = new TravelMethod();
//        // 这一步可以引入策略工厂来做
//        if (money <= 0) {
//            travelMethod.setTravelStrategy(walk);
//        } else if (money <= 500) {
//            travelMethod.setTravelStrategy(train);
//        } else {
//            travelMethod.setTravelStrategy(airplane);
//        }
//        travelMethod.travelMethod();
    }
}
