package com.state_pattern.DeliveredDocument;

import com.state_pattern.DocumentContext.DocumentContext;
import com.state_pattern.IDocumentState.IDocumentState;
import com.state_pattern.SendDocument.SendDocument;
import com.state_pattern.StatusName.StatusName;

public class DeliveredDocument implements IDocumentState {
 
    @Override
    public void nextDocumStatus(DocumentContext documentContext) {
        System.out.println("DocumentAccept nextDocumStatus NOTHING happens");
    }
 
    @Override
    public void previousDocumStatus(DocumentContext documentContext) {
        documentContext.setiDocumentState(new SendDocument());
    }
 
    @Override
    public String getStatusName() {
        return StatusName.DELIVERED.getStatusName();
    }
 
}
