package com.rest.rest_api.mathLogic.FileReaders;

import com.rest.rest_api.mathLogic.contracts.Interface.InterfaceFileReader;

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
