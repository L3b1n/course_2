package com.patterns.Adapter;

public class Main {
    public static void rechargePhone(PhoneWithLightning phoneWithLightning) {
        phoneWithLightning.useLightning();
        phoneWithLightning.chargePhone();
    }

    public static void main(String[] args) {
        Iphone iphone = new Iphone();
        rechargePhone(iphone);
        AndroidPhone androidPhone = new AndroidPhone();
        rechargePhone(new TypeCtoLightningPhoneAdapter(androidPhone));
    }
}
