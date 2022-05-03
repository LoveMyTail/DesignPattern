package com.trunks.strategy.travelCase;

/**
 * @author ：Sun Yangfan
 * @date ：Created in 2022/5/3
 */
public class TravelMethod {

    public TravelStrategy travelStrategy;

    public void setTravelStrategy(TravelStrategy travelStrategy) {
        this.travelStrategy = travelStrategy;
    }

    public TravelStrategy getTravelStrategy() {
        return travelStrategy;
    }

    public void travelMethod() {
        travelStrategy.travel();
    }
}
