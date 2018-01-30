package com.example.admin.lowestcost;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Admin on 1/30/2018.
 */

/**
 * To be run with MatrixTesterRunner class
 */
public class MatrixTester extends TestCase{
    private String methodName;
    public MatrixTester(String s)
    {
        methodName = s;
    }
    /**
     * If the matrix has been loaded properly and with proper values. the underlying RoutingTable should have both available paths and visited
     * paths
     * @param filename
     * @throws FileNotFoundException
     */
    public void test5DimenMatrix(String filename) {
        Graph.RoutingTable r = null;
        try {
            r = createMatrixPaths(filename, 5);
        } catch (FileNotFoundException e) {
            fail("File does not exist.  Matrix not created.");
        }
        assertTrue(r.getAvailablePaths() != null && r.getAvailablePaths().size() > 0);
        assertTrue(r.getVisitedPaths() != null && r.getVisitedPaths().size() > 0);
    }

    public void test3DimenMatrix(String filename)
    {
        Graph.RoutingTable r = null;
        try {
            r = createMatrixPaths(filename, 3);
        } catch (FileNotFoundException e) {
            fail("File does not exist.  Matrix not created.");
        }
        assertTrue(r.getAvailablePaths() != null && r.getAvailablePaths().size() > 0);
        assertTrue(r.getVisitedPaths() != null && r.getVisitedPaths().size() > 0);
    }

    public void testNDimenMatrix(String filename, int n)
    {
        Graph.RoutingTable r = null;
        if (n > 100)
        {
            //this was part of the design specs
            fail("More than 100 dimensions are not allowed for a Matrix.");
        }
        try {
            r = createMatrixPaths(filename, n);
        } catch (FileNotFoundException e) {
            fail("File does not exist.  Matrix not created.");
        }
        assertTrue(r.getAvailablePaths() != null && r.getAvailablePaths().size() > 0);
        assertTrue(r.getVisitedPaths() != null && r.getVisitedPaths().size() > 0);
    }
    public void testInputFile(String fileName)
    {
        try {
            File readfile = new File(fileName);
            Scanner readme = new Scanner(readfile);

            int from, to, cost;
            while (readme.hasNext()) {
                if (!readme.hasNextInt()) {
                    readme.next();
                } else {
                    from = readme.nextInt();
                    to = readme.nextInt();
                    cost = readme.nextInt();
                    if (from < 0 || to < 0 || cost < 0)
                    {
                        fail("All integers in the Matrix should be positive.");
                    }
                }
            }
         //we should be able to read the file and fetch each token of data as an integer, if not, we have an invalid file
        } catch (Exception e)
        {
            fail("Exception caught while trying to parse file.  Invalid input file.");
        }
    }
    public Graph.RoutingTable createMatrixPaths(String fileName, int nodes) throws FileNotFoundException
    {
        Graph dijkstras = new Graph(7, fileName);
        Graph.RoutingTable Route1 = dijkstras.new RoutingTable(1, nodes);
        Route1.generatePath();

        for (int i = 1; i <= nodes; i++)
        {
            for (int j = 1; j <= nodes; j++)
            {
                Route1 = dijkstras.new RoutingTable(i, j);
                Route1.generatePath();
            }
        }
        return Route1;
    }
}
