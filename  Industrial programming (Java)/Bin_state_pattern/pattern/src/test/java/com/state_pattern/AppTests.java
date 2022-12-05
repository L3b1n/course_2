package com.state_pattern;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.state_pattern.DocumentContext.DocumentContext;

/**
 * Unit test for simple App.
 */
public class AppTests 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithNew()
    {
        DocumentContext d = new DocumentContext();
        assertEquals("новый", d.getStatusName());
    }
    
    @Test
    public void shouldAnswerWithSend()
    {
        DocumentContext d = new DocumentContext();
        d.nextDocumStatus();
        assertEquals("отправлен", d.getStatusName());
    }
    
    @Test
    public void shouldAnswerWithDelivered()
    {
        DocumentContext d = new DocumentContext();
        d.nextDocumStatus();
        d.nextDocumStatus();
        assertEquals("доставлен", d.getStatusName());
    }
}
