package com.global_task.contracts.Interface;

public interface FileReaderBuilder {
    void setEncrypt(String key);
    void setZip(boolean isZip);
    InterfaceFileReader getFileReader();
}
