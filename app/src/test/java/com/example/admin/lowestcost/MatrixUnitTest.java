package com.example.admin.lowestcost;

import org.junit.Test;
import com.example.admin.lowestcost.Graph.RoutingTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MatrixUnitTest {


    @Test
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
    @Test
    public void test5DimenMatrix(String filename) {
        RoutingTable r = null;
        try {
            r = createMatrixPaths(filename, 5);
        } catch (FileNotFoundException e) {
            fail("File does not exist.  Matrix not created.");
        }
        assertTrue(r.getAvailablePaths() != null && r.getAvailablePaths().size() > 0);
        assertTrue(r.getVisitedPaths() != null && r.getVisitedPaths().size() > 0);
    }

    @Test
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

    @Test
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