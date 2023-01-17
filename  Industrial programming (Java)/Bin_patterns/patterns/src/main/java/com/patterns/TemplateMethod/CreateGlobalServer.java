package com.patterns.TemplateMethod;

public class CreateGlobalServer extends CreateSite {
    @Override
    public void generateSite() {
        System.out.println("\tGlobal server site generated\n");
    }
}
