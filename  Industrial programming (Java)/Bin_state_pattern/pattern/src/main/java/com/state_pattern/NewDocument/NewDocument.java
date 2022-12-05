package com.state_pattern.NewDocument;

import com.state_pattern.DocumentContext.DocumentContext;
import com.state_pattern.IDocumentState.IDocumentState;
import com.state_pattern.SendDocument.SendDocument;
import com.state_pattern.StatusName.StatusName;

public class NewDocument implements IDocumentState {
     
    @Override
    public void nextDocumStatus(DocumentContext documentContext) {
        documentContext.setiDocumentState(new SendDocument());
    }
 
    @Override
    public void previousDocumStatus(DocumentContext documentContext) {
        System.out.println("DocumentNew previousDocumStatus NOTHING happens");       
    }
 
    @Override
    public String getStatusName() {
        return StatusName.NEW.getStatusName();
    }
 
}
