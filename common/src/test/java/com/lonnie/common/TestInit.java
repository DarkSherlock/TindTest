package com.lonnie.common;

import org.junit.Test;

/**
 * @author: lonnie.liang
 * date: 2022/10/29 23:48
 */
public class TestInit {

    @Test
    public void test() {
        Son son = new Son();
    }

    public static class Parent {
        private boolean pFlagA = true;
        public Parent() {
            System.out.println("Parent's constructor begin");
            System.out.println("pFlagA="+pFlagA);
            System.out.println("pFlagB="+pFlagB);
            System.out.println("Parent's constructor end");
        }
        private boolean pFlagB = true;
    }

    public static class Son extends Parent {
        private boolean sFlagA = true;
        public Son() {
            System.out.println("Son's constructor begin");
            System.out.println("sFlagA="+sFlagA);
            System.out.println("sFlagB="+sFlagB);
            System.out.println("Son's constructor end");
        }
        private boolean sFlagB = true;
    }
}
