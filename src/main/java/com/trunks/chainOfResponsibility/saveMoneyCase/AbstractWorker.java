package com.trunks.chainOfResponsibility.saveMoneyCase;

public abstract class AbstractWorker {
    // 上级
    private AbstractWorker superior = null;
    // 员工类型
    protected String type;

    public void setSuperior(AbstractWorker superior) {
        this.superior = superior;
    }

    public void approveRequest(Request request) {
        if (getLimit() >= request.saveMoney()) {
            System.out.println(getType() + "处理存钱请求");
        } else {
            if (this.superior != null) {
                this.superior.approveRequest(request);
            } else {
                System.out.println("没人能处理存钱请求");
            }
        }
    }

    public abstract int getLimit();

    public String getType() {
        return type;
    }
}
