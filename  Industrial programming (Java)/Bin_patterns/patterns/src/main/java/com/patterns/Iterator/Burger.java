package com.patterns.Iterator;

public class Burger implements Container {
    private String burgerName;
    private String[] burgerContains;

    public Burger(String burgerName, String[] burgerContains) {
        this.burgerName = burgerName;
        this.burgerContains = burgerContains;
    }

    public String getBurgerName() {
        return burgerName;
    }

    public String[] getBurgerContains() {
        return burgerContains;
    }

    @Override
    public Iterator getIterator() {
        return new BurgerContainsIterator();
    }

    private class BurgerContainsIterator implements Iterator {
        int index;
        @Override
        public boolean hasNext() {
            if(index < burgerContains.length) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            return burgerContains[index++];
        }
    }
}
