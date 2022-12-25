package com.example.ObjectClasses;

public class Camera {
    private Integer amount;

    public Camera(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Camera [amount = " + amount + "]";
    }
}
