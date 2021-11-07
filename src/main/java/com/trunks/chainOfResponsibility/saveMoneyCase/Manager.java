package com.trunks.chainOfResponsibility.saveMoneyCase;

public class Manager extends AbstractWorker {
    public Manager() {
        super.type = "总经理";
    }

    @Override
    public int getLimit() {
        return 1000000000;
    }
}
