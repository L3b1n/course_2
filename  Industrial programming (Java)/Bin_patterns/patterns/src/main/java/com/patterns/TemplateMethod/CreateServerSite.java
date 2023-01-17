package com.patterns.TemplateMethod;

public class CreateServerSite extends CreateSite {
    @Override
    public void generateSite() {
        System.out.println("\tServer site generated\n");
    }
}
