package com.trunks.strategy.travelCase;

/**
 * @author ：Sun Yangfan
 * @date ：Created in 2022/5/3
 */
public class Client {
    public static void main(String[] args) {
        int money = 1000;
        Train train = new Train();
        Airplane airplane = new Airplane();
        Walk walk = new Walk();
        TravelMethod travelMethod = new TravelMethod();
        // 这一步可以引入策略工厂来做
        if (money <= 0) {
            travelMethod.setTravelStrategy(walk);
        } else if (money <= 500) {
            travelMethod.setTravelStrategy(train);
        } else {
            travelMethod.setTravelStrategy(airplane);
        }
        travelMethod.travelMethod();
    }
}
