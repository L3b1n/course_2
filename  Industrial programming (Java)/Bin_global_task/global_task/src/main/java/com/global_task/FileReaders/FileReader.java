package com.global_task.FileReaders;

import com.global_task.contracts.Interface.InterfaceFileReader;

public abstract class FileReader implements InterfaceFileReader{
    protected String inputName;
    protected InterfaceFileReader reader;
    public FileReader(InterfaceFileReader reader) {
        this.reader = reader;
        inputName = reader.getFileName();
    }

    @Override
    public String getFileName() {
        return this.inputName;
    }
}
