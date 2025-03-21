package io.github.retrobitcoder;

import java.util.ArrayList;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class SolverTest extends TestCase {

    public void testSolver() {
        // cuts
        Measurement c1 = new Measurement(10, 8);
        Measurement c2 = new Measurement(16, 4);
        Measurement c3 = new Measurement(22, 0);
        Measurement c4 = new Measurement(5, 0);
        Measurement c5 = new Measurement(4, 0);
        Measurement c6 = new Measurement(8, 6);

        // rolls
        Measurement r1 = new Measurement(31, 6);
        Measurement r2 = new Measurement(100, 0);

        // lists
        ArrayList<Measurement> cutsList = new ArrayList<>();
        ArrayList<Measurement> rollsList = new ArrayList<>();

        cutsList.add(c1);
        cutsList.add(c2);
        cutsList.add(c3);
        cutsList.add(c4);
        cutsList.add(c5);
        cutsList.add(c6);

        rollsList.add(r1);
        rollsList.add(r2);

        // solver
        Solver solver = new Solver(cutsList, rollsList);

        solver.solve();

        System.out.println(solver.getSolutions());

        fail("TODO: check solution");
    }
}
