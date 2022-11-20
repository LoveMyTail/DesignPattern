package com.trunks.strategy.travelCase;

import javafx.beans.binding.ObjectExpression;

import javax.annotation.PostConstruct;
import java.util.HashMap;

public class TravelEngine {

    private HashMap<Integer, Object> map = new HashMap<>();

    @PostConstruct
    private void init() {
        map.put(100, Train.class);
        map.put(1000, Airplane.class);
        map.put(1, Walk.class);
    }


    public TravelStrategy getTravelMethod(Integer money) {
        init();
        return (TravelStrategy) map.get(money);
    }
}
