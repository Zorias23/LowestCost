package com.example.admin.lowestcost;

import junit.framework.TestCase;

/**
 * Created by Admin on 1/30/2018.
 */

public class MatrixTesterRunner {
private String filename = "graph.txt"; //modify your file name here for testing
private int dimensions = 7; //set how many dimensions you want the Matrix to be.
    public void main(String[] S)
    {
        TestCase fileTest= new MatrixTester("testInputFile") {
            public void runTest() {
                testInputFile(filename);
            }
        };
        fileTest.run();

        TestCase threeDimenTest = new MatrixTester("test3DimenMatrix") {
            public void runTest() {
                test3DimenMatrix(filename);
            }
        };
        threeDimenTest.run();

        TestCase fiveDimenTest = new MatrixTester("test5DimenMatrix") {
            public void runTest() {
                test5DimenMatrix(filename);
            }
        };
        fiveDimenTest.run();

        TestCase nDimenTest = new MatrixTester("testNDimenMatrix") {
            public void runTest() {
                testNDimenMatrix(filename, dimensions);
            }
        };
        nDimenTest.run();
    }
}
