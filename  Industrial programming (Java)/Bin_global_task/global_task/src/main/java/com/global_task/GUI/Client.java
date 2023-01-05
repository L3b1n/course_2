package com.global_task.GUI;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class Client {
    private Socket client;
    private OutputStream outputStream;

    public void connectServer(int serverPort) throws Throwable {
        try(ServerSocket server = new ServerSocket(serverPort)) {
            client = server.accept();
        } 
        outputStream = client.getOutputStream();
    }

    public void stopClient() throws Throwable {
        client.close();
    }

    public void sendFile(File tempFile, File directoryFile, String flag, ArrayList<String> keys, String compressLevel) throws Throwable {
        PrintWriter writer = new PrintWriter(outputStream, true);
        writer.println(tempFile.getName());
        writer.println(directoryFile.getAbsolutePath());
        writer.println(flag);
        writer.println(keys.size());
        if(keys.size() != 0) {
            for(String key : keys) {
                writer.println(key);
            }
        }
        writer.println(compressLevel);
        System.out.println(tempFile.getName() + "  " + flag + "  " + keys);
        if(tempFile.getName().substring(tempFile.getName().lastIndexOf('.') + 1).equals("zip")) {
            ZipEntry entry;
            ZipInputStream in = new ZipInputStream(new FileInputStream(tempFile));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(outputStream));
            while((entry = in.getNextEntry()) != null) {
                writer.println(entry.getName());
                for(int c = in.read(); c != -1; c = in.read()) {
                    out.write(c);
                }
                out.flush();
                in.closeEntry();
            }
            out.close();
            in.close();
        } else {
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
}