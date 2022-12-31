package com.global_task.FileReaders;

import java.util.ArrayList;

import com.global_task.contracts.Interface.FileReaderBuilder;
import com.global_task.contracts.Interface.InterfaceFileReader;

public class FileBuilder implements FileReaderBuilder{
    protected InterfaceFileReader fileReader;
    public FileBuilder(String fileType, String fileName) {
        if(fileType.equals("txt")) {
            fileReader = new TXTFileReader(fileName);
        } else if(fileType.equals("json")) {
            fileReader = new JSONFileReader(fileName);
        } else if(fileType.equals("xml")) {
            fileReader = new XMLFileReader(fileName);
        }
    }
    
    @Override
    public void setEncrypt(String key) {
        fileReader = new EncryptedFileReader(key, fileReader);
    }
    
    @Override
    public void setEncrypt(ArrayList<String> keyList) {
        for(String key : keyList) {
            fileReader = new EncryptedFileReader(key, fileReader);
        }
    }

    @Override
    public void setCompressed(boolean isCompressed) {
        if(isCompressed){ fileReader = new CompressFileReader(fileReader);}
    }

    @Override
    public void setZip(boolean isZipped) {
        if(isZipped){ fileReader = new ZippedFileReader(fileReader);}
    }

    @Override
    public InterfaceFileReader getFileReader() {
        return fileReader;
    }
}
