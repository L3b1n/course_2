package com.global_task.GUI;

import java.net.Socket;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
// import java.io.RandomAccessFile;
import java.net.ServerSocket;

public class Client {
    private Socket client;
    private InputStream inputStream;
    private OutputStream outputStream;

    public void connectServer(int serverPort) throws Throwable {
        try(ServerSocket server = new ServerSocket(serverPort)) {
            client = server.accept();
        } 
        inputStream = client.getInputStream();
        outputStream = client.getOutputStream();
    }

    public void stopClient() throws Throwable {
        client.close();
    }

    public void getFile() throws Throwable {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        // String fileName = reader.readLine();
        // File tempFile = new File(fileName);
        // RandomAccessFile writer = new RandomAccessFile(tempFile, "rw");
        // int num = reader.read();
        // while(num != -1) {
        //     writer.write(num);
        //     writer.skipBytes(num);
        //     num = reader.read();
        // }
        // writer.close();
        reader.close();
    }

    public void sendFile(File tempFile, String flag, String keys, String compressLevel) throws Throwable {
        PrintWriter writer = new PrintWriter(outputStream, true);
        writer.println(tempFile.getName());
        writer.println(flag);
        writer.println(keys);
        writer.println(compressLevel);
        System.out.println(tempFile.getName() + "  " + flag + "  " + keys);
        Scanner in = new Scanner(new FileReader(tempFile));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(outputStream));
        while(in.hasNextLine()) {
            String temp = in.nextLine();
            out.write(temp + "\n");
            out.flush();
        }
        out.close();
        in.close();
        client.close();
    }
}
