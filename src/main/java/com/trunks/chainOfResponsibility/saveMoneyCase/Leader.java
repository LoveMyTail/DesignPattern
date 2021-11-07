package com.trunks.chainOfResponsibility.saveMoneyCase;

public class Leader extends AbstractWorker {
    public Leader() {
        super.type = "组长";
    }

    @Override
    public int getLimit() {
        return 100000;
    }
}
