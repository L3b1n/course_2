package com.state_pattern.SendDocument;

import com.state_pattern.DeliveredDocument.DeliveredDocument;
import com.state_pattern.DocumentContext.DocumentContext;
import com.state_pattern.IDocumentState.IDocumentState;
import com.state_pattern.NewDocument.NewDocument;
import com.state_pattern.StatusName.StatusName;

public class SendDocument implements IDocumentState {
 
    @Override
    public void nextDocumStatus(DocumentContext documentContext) {
        documentContext.setiDocumentState(new DeliveredDocument());
    }
 
    @Override
    public void previousDocumStatus(DocumentContext documentContext) {
        documentContext.setiDocumentState(new NewDocument());
    }
 
    @Override
    public String getStatusName() {
        return StatusName.SEND.getStatusName();
    }
     
}
