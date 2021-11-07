package com.trunks.chainOfResponsibility.saveMoneyCase;

/**
 * 存钱请求类
 */
public class Request {
    private int money;

    public Request(int money) {
        System.out.println("有新的请求，需要存钱：" + money + "元");
        this.money = money;
    }

    public int saveMoney() {
        return money;
    }
}
