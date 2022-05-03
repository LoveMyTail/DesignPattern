package com.trunks.strategy.travelCase;

/**
 * @author ：Sun Yangfan
 * @date ：Created in 2022/5/3
 */
public class Airplane implements TravelStrategy {
    @Override
    public void travel() {
        System.out.println("通过飞机出行");
    }
}
