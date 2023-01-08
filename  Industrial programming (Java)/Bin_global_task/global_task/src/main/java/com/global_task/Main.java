package com.global_task;

public class Main {
    // private static final String FirstKey = "squirreldgwndlaj";
    // private static final String SecondKey = "abshgsfrenhlksmp";

    public static void main(String[] args) throws Throwable {
        while(true) {
            try {
                Server serverGet = new Server();
                serverGet.createServer(9527);
                System.out.println("Please wait");
                serverGet.getFile();
                serverGet.createNewFile();
                Server serverSend = new Server();
                serverSend.createServer(9528);
                serverSend.sendResult("");
                serverSend.stopServer();
                System.out.println("Done");
                serverGet.stopServer();
            } catch(Throwable e) {
                Server serverSend = new Server();
                serverSend.createServer(9528);
                serverSend.sendResult(e.toString().substring(e.toString().indexOf(':') + 1));
                serverSend.stopServer();
                System.out.println("Not done");
            }
        }
    }
}