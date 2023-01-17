package com.global_task;

public class Main {
    // private static final String FirstKey = "squirreldgwndlaj";
    // private static final String SecondKey = "abshgsfrenhlksmp";

    public static void main(String[] args) throws Throwable {
        while(true) {
            Server serverGet = new Server();
            try {
                serverGet.createServer(4444);
                System.out.println("Please wait");
                serverGet.getFile();
                serverGet.createNewFile();
                Server serverSend = new Server();
                serverSend.createServer(4445);
                serverSend.sendResult("");
                serverSend.stopServer();
                System.out.println("Done");
                serverGet.stopServer();
            } catch(Throwable e) {
                Server serverSend = new Server();
                serverSend.createServer(4445);
                Server.deleteFile(serverGet.getFileName());
                serverSend.sendResult(e.toString().substring(e.toString().indexOf(':') + 1));
                serverSend.stopServer();
                System.out.println("Not done");
            }
        }
    }
}