package com.patterns.TemplateMethod;

public abstract class CreateSite {
    public abstract void generateSite();

    public void createSite() {
        this.verifySiteDetails();
        this.verifyIdentificationDetails();
        this.generateSite();
    }

    public void verifySiteDetails() {
		System.out.println("Set Site details");
	}
	
	public void verifyIdentificationDetails() {
		System.out.println("Set the Identification Details");
	}
}
