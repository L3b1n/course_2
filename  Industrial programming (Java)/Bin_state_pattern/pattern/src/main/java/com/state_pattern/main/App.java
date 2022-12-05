package com.state_pattern.main;

import com.state_pattern.DocumentContext.DocumentContext;

public class App {
	public static void main(String[] args) {
        DocumentContext d = new DocumentContext();
        System.out.println(d.getStatusName());
        d.nextDocumStatus();
        System.out.println(d.getStatusName());
        d.nextDocumStatus();
        System.out.println(d.getStatusName());
        d.nextDocumStatus();
        System.out.println(d.getStatusName());
    }
}