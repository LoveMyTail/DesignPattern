package com.trunks.strategy.travelCase;

/**
 * @author ：Sun Yangfan
 * @date ：Created in 2022/5/3
 */
public class Train implements TravelStrategy {
    @Override
    public void travel() {
        System.out.println("通过火车出行");
    }
}
