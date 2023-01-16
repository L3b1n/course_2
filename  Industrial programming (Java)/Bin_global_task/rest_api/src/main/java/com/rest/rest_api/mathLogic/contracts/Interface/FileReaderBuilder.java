package com.rest.rest_api.mathLogic.contracts.Interface;

import java.util.ArrayList;

public interface FileReaderBuilder {
    void setEncrypt(String key);
    void setEncrypt(ArrayList<String> keyList);
    void setCompressed(boolean isCompressed);
    void setZip(boolean isZip);
    InterfaceFileReader getFileReader();
}
