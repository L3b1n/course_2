package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

class MainTestsTrue {
    @Test
    public void test1() {
        assertTrue(Main.isTrue("fpmi@bsu.by"));
    }

    @Test
    public void test2() {
        assertTrue(Main.isTrue("test-27@yandex.com"));
    }

    @Test
    public void test3() {
        assertTrue(Main.isTrue("test.27@yandex.com"));
    }

    @Test
    public void test4() {
        assertTrue(Main.isTrue("test.100@devcolibri.com.ua"));
    }

    @Test
    public void test5() {
        assertTrue(Main.isTrue("test@1.com"));
    }

    @Test
    public void test6() {
        assertTrue(Main.isTrue("test-27@yandex-test.com"));
    }

    @Test
    public void test7() {
        assertTrue(Main.isTrue("valid@megapochta.com"));
    }

    @Test
    public void test8() {
        assertTrue(Main.isTrue("test.27@yandex.com"));
    }

    @Test
    public void test9() {
        assertTrue(Main.isTrue("test@test.com"));
    }
    
    @Test
    public void test10() {
        assertTrue(Main.isTrue("test313@test-3747.com"));
    }
}
