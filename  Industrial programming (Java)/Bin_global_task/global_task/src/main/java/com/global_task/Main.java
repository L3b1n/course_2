package com.global_task;

public class Main {
    // private static final String FirstKey = "squirreldgwndlaj";
    // private static final String SecondKey = "abshgsfrenhlksmp";

    public static void main(String[] args) throws Throwable {
        while(true) {
            Server serverGet = new Server();
            serverGet.createServer(9527);
            System.out.println("Please wait");
            serverGet.getFile();
            serverGet.createNewFile();
            System.out.println("Done");
        }
    }
}