package com.patterns.Adapter;

public class Iphone implements PhoneWithLightning {
    private String isConnected = "disConnected";
    @Override
    public void useLightning() {
        isConnected = "connected";
        System.out.println("Lightning port is connected");
    }

    @Override
    public void chargePhone() {
        if(isConnected.equals("connected")) {
            System.out.println("\tCharging started");
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }
            System.out.println("\tCharging finished");
        } else {
            System.out.println("Android phone is " + isConnected + ". Connect lightning to phone first");
        }
    }
}
