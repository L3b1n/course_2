package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

class MainTestsFalse {
    @Test
    public void test1() {
        assertTrue(Main.isTrue("-test4@test.by"));
    }

    @Test
    public void test2() {
        assertTrue(Main.isTrue(".test4@test.by"));
    }

    @Test
    public void test3() {
        assertTrue(Main.isTrue("val@val.a.a.a.a"));
    }

    @Test
    public void test4() {
        assertTrue(Main.isTrue("val@val"));
    }

    @Test
    public void test5() {
        assertTrue(Main.isTrue("Just Text2"));
    }

    @Test
    public void test6() {
        assertTrue(Main.isTrue("bug@@@com.ru"));
    }

    @Test
    public void test7() {
        assertTrue(Main.isTrue("44invalid@megapochta.com"));
    }

    @Test
    public void test8() {
        assertTrue(Main.isTrue("12323123@111[]][]"));
    }

    @Test
    public void test9() {
        assertTrue(Main.isTrue("[test@test][<,.>].com"));
    }
    
    @Test
    public void test10() {
        assertTrue(Main.isTrue("[test@test].com"));
    }
}
