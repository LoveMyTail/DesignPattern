package com.trunks.chainOfResponsibility.saveMoneyCase;

public class Clerk extends AbstractWorker {
    public Clerk() {
        super.type = "普通职员";
    }

    @Override
    public int getLimit() {
        return 20000;
    }
}
