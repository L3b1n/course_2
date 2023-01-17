package com.patterns.TemplateMethod;

public class Main {
    public static void main( String[] args ) {
        CreateSite server = new CreateServerSite();
		server.createSite();
        server = new CreateGlobalServer();
        server.createSite();
    }
}
