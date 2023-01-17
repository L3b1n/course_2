package com.patterns.Adapter;

public class TypeCtoLightningPhoneAdapter implements PhoneWithLightning {
    private final PhoneWithTypeC phoneWithTypeC;
    public TypeCtoLightningPhoneAdapter(PhoneWithTypeC phoneWithTypeC) {
        this.phoneWithTypeC = phoneWithTypeC;
    }

    @Override
    public void useLightning() {
        phoneWithTypeC.useTypeC();
    }

    @Override
    public void chargePhone() {
        phoneWithTypeC.chargePhone();
    }
}
