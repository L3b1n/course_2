package com.global_task.java.FileReaders;

import com.global_task.contracts.Interface.FileReaderBuilder;
import com.global_task.contracts.Interface.InterfaceFileReader;

public class FileBuilder implements FileReaderBuilder{
    protected InterfaceFileReader fileReader;
    public FileBuilder(String fileType, String fileName) {
        if(fileType == "Txt") {
            fileReader = new TXTFileReader(fileName);
        } else if(fileType == "Json") {
            fileReader = new JSONFileReader(fileName);
        } else {
            fileReader = new XMLFileReader(fileName);
        }
    }

    @Override
    public void setEncrypt(String key) {
        fileReader = new EncryptedFileReader(key, fileReader);
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
