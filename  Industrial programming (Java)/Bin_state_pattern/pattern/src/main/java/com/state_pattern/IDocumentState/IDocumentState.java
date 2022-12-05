package com.state_pattern.IDocumentState;

import com.state_pattern.DocumentContext.DocumentContext;

public interface IDocumentState {
    String getStatusName();
    void nextDocumStatus(DocumentContext documentContext);
    void previousDocumStatus(DocumentContext documentContext);
}
