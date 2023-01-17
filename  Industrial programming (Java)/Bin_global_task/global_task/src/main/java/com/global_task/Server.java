package com.global_task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import com.global_task.FileReaders.CompressFileReader;
import com.global_task.FileReaders.EncryptedFileReader;
import com.global_task.FileReaders.FileBuilder;
import com.global_task.FileReaders.ZippedFileReader;
import com.global_task.contracts.Interface.InterfaceFileReader;

public class Server {
    private Socket clienSocket;
    OutputStream outputStream;
    InputStream inputStream;
    private String flag;
    private String fileName;
    private String compressLevel;
    private String uzippedFileName;
    private String resultDirectory;
    private InterfaceFileReader reader;
    private ArrayList<String> keys = new ArrayList<>();
    
    void createNewFile() throws Throwable {
        String command = "";
        System.out.println(fileName);
        Integer i = 0, j = 0;
        FileBuilder builder = new FileBuilder(fileName.substring(fileName.lastIndexOf('.') + 1), fileName);
        while(flag.contains("Ca")) {
            command = flag.substring(0, 2);
            flag = flag.substring(2);
            if(command.equals("Ed")) {
                builder.setEncrypt(keys.get(i++));
            }
            if(command.equals("Cd")) {
                builder.setCompressed(true);
            }
            if(command.equals("Zu")) {
                builder.setZip(true);
            }
            if(command.equals("Ca")) {
                reader = builder.getFileReader();
                if(uzippedFileName != null) {
                    fileName = uzippedFileName;
                }
                reader.getResult(fileName);
            }
        }
        i = keys.size() - 1;
        while(!flag.equals("")) {
            command = flag.substring(0, 2);
            flag = flag.substring(2);
            builder = new FileBuilder(fileName.substring(fileName.lastIndexOf('.') + 1), fileName);
            if(command.equals("Ee")) {
                EncryptedFileReader.Encrypt(keys.get(j++), fileName, "new" + fileName);
                File temp = new File("new" + fileName);
                temp.renameTo(new File(fileName));
            }
            if(command.equals("Ed")) {
                builder.setEncrypt(keys.get(i--));
                reader = builder.getFileReader();
                reader.Write(reader.Read(), "new" + fileName);
                File temp = new File("new" + fileName);
                temp.renameTo(new File(fileName));
            }
            if(command.equals("Cc")) {
                CompressFileReader.Compress(Integer.valueOf(compressLevel), fileName, "new" + fileName);
                File temp = new File("new" + fileName);
                temp.renameTo(new File(fileName));
            }
            if(command.equals("Cd")) {
                builder.setCompressed(true);
                reader = builder.getFileReader();
                reader.Write(reader.Read(), "new" + fileName);
                File temp = new File("new" + fileName);
                temp.renameTo(new File(fileName));
            }
            if(command.equals("Zz")) {
                ZippedFileReader.WriteZipped(fileName, fileName.substring(0, fileName.lastIndexOf('.')) + ".zip");
                deleteFile(fileName);
                uzippedFileName = fileName;
                fileName = fileName.substring(0, fileName.lastIndexOf('.')) + ".zip";
            }
            if(command.equals("Zu")) {
                builder.setZip(true);
                reader = builder.getFileReader();
                reader.Write(reader.Read(), uzippedFileName);
                deleteFile(fileName);
                fileName = uzippedFileName;
            }
        }
        createFile(fileNameBuilder("new", fileName));
        copyFile(fileName, fileNameBuilder("new", fileName));
        deleteFile(fileName);
    }
    
    public void createServer(int serverPort) throws Throwable {
        while(clienSocket == null) {
            try {
                clienSocket = new Socket(InetAddress.getLocalHost(), serverPort);
            } catch(IOException e) {}
        }
        inputStream = clienSocket.getInputStream();
        outputStream = clienSocket.getOutputStream();
    }

    public void connectServer(int serverPort) throws Throwable {
        try(ServerSocket socket = new ServerSocket(serverPort)) {
            clienSocket = socket.accept();
        }
        inputStream = clienSocket.getInputStream();
        outputStream = clienSocket.getOutputStream();
    }

    public void stopServer() throws Throwable{
        clienSocket.close();
    }

    public void sendResult(String resultString) throws Throwable {
        PrintWriter writer = new PrintWriter(outputStream, true);
        writer.println(resultString);
        writer.close();
    }

    public void getFile() throws Throwable {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        fileName = reader.readLine();
        resultDirectory = reader.readLine();
        flag = reader.readLine();
        Integer size = Integer.valueOf(reader.readLine());
        if(size != 0) {
            keys = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                keys.add(reader.readLine());
            }
        }
        compressLevel = reader.readLine();
        if(fileName.substring(fileName.lastIndexOf('.') + 1).equals("zip")) {
            uzippedFileName = reader.readLine();
            ZipOutputStream writer = new ZipOutputStream(new FileOutputStream(fileName));
            ZipEntry entry = new ZipEntry(uzippedFileName);
            writer.putNextEntry(entry);
            String tmp;
            StringBuilder temp = new StringBuilder();
            tmp = reader.readLine();
            while(true) {
                temp.append(tmp);
                if((tmp = reader.readLine()) != null) {
                    temp.append("\n");
                } else {
                    break;
                }
                writer.flush();
            }
            writer.write(temp.toString().getBytes());
            writer.closeEntry();
            writer.close();
            reader.close();
        } else {
            String temp = reader.readLine();
            FileWriter writer = new FileWriter(fileName);
            while(true) {
                writer.write(temp);
                if((temp = reader.readLine()) != null) {
                    writer.write("\n");
                } else {
                    break;
                }
                writer.flush();
            }
            writer.close();
            reader.close();
        }
    }

    public static void copyFile(String inputFileName, String outputFileName) throws Throwable {
        if(inputFileName.substring(inputFileName.lastIndexOf('.') + 1).equals("zip")) {
            ZipEntry entry;
            ZipInputStream reader = new ZipInputStream(new FileInputStream(inputFileName));
            ZipOutputStream writer = new ZipOutputStream(new FileOutputStream(outputFileName));
            while((entry = reader.getNextEntry()) != null) {
                String name = entry.getName();
                ZipEntry entry1 = new ZipEntry(name);
                writer.putNextEntry(entry1);
                byte[] bytes = reader.readAllBytes();
                writer.write(bytes);
                writer.flush();
                reader.closeEntry();
            }
            writer.close();
            reader.close();
        } else {
            Scanner reader = new Scanner(new FileReader(inputFileName));
            FileWriter writer = new FileWriter(outputFileName);
            while(true) {
                writer.write(reader.nextLine());
                if(reader.hasNextLine()) {
                    writer.write("\n");
                } else {
                    break;
                }
            }
            writer.close();
            reader.close();
        }
    }

    private String fileNameBuilder(String typeOfFile, String fileName) {
        return resultDirectory + System.getProperty("file.separator") + typeOfFile + fileName.substring(0, 1).toUpperCase() + fileName.substring(1);
    }

    public static void createFile(String fileName) throws Throwable {
        File tempFile = new File(fileName);
        if(tempFile.createNewFile()) {
            System.out.println("File created: " + tempFile.getName());
        } else {
            System.out.println("File already exists");
        }
    }
    
    public static void deleteFile(String fileName) {
        File tempFile = new File(fileName);
        if(tempFile.delete()) {
            System.out.println("File " + fileName + " deleted successfully");
        } else {
            System.out.println("Failed to delete " + fileName + " file");
        }
    }

    public String getFileName() {
        return fileName;
    }
}