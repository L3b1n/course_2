package com.patterns.Iterator;

public class Main {
    public static void main(String[] args) {
        String[] burgerContains = {"Java", "Beef", "PostgreSQL", "Salad"};
        Burger burger = new Burger("Thanks collegue", burgerContains);
        Iterator iterator = burger.getIterator();
        System.out.println("Burger name: " + burger.getBurgerName());
        System.out.print("Burger contains: \n\t");
        while(iterator.hasNext()) {
            System.out.print(iterator.next().toString() + "\n\t");
        }
    }
}
