package com.state_pattern.DocumentContext;

import com.state_pattern.IDocumentState.IDocumentState;
import com.state_pattern.NewDocument.NewDocument;

public class DocumentContext {  
    private IDocumentState iDocumentState = new NewDocument();
 
    public IDocumentState getiDocumentState() {
        return iDocumentState;
    }
     
    public void nextDocumStatus() {
        iDocumentState.nextDocumStatus(this);       
    }
     
    public void previousDocumStatus() {
        iDocumentState.previousDocumStatus(this);       
    }
 
    public void setiDocumentState(IDocumentState iDocumentState) {
        this.iDocumentState = iDocumentState;
    }   
         
    public String getStatusName() {
        return iDocumentState.getStatusName();
    }
}
