package com.global_task.FileReaders;

public class FileReaderInfo {
    public FileReaderInfo(String inputFileName) {
        this.inputName = inputFileName;
    }

    public String getFileName() {
        return this.inputName;
    }

    protected String inputName;
}
