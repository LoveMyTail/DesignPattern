package com.trunks.chainOfResponsibility.saveMoneyCase;

public class Client {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Leader leader = new Leader();
        Manager manager = new Manager();
        clerk.setSuperior(leader);
        leader.setSuperior(manager);
        Request request1 = new Request(50000);
        clerk.approveRequest(request1);
        Request request2 = new Request(9500000);
        clerk.approveRequest(request2);
    }
}
