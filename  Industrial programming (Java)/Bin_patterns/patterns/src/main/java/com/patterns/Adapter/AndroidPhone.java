package com.patterns.Adapter;

public class AndroidPhone implements PhoneWithTypeC {
    private String isConnected = "disConnected";
    @Override
    public void useTypeC() {
        isConnected = "connected";
        System.out.println("TypeC port is connected");
    }

    @Override
    public void chargePhone() {
        if(isConnected.equals("connected")) {
            System.out.println("\tCharging started");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }
            System.out.println("\tCharging finished");
        } else {
            System.out.println("Android phone is " + isConnected + ". Connect typeC to phone first");
        }
    }
}
