package io.github.retrobitcoder;

import java.util.ArrayList;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class SolverTest extends TestCase {

    public void testNoLists() {
        String solution = "Cuts and Rolls list can not be all 0s";

        Solver solver = new Solver(null, null);

        solver.solve();

        assertEquals(solution, solver.getSolutions());

        solver.setCuts(new ArrayList<>());

        solver.solve();

        assertEquals(solution, solver.getSolutions());

        solver.setRolls(new ArrayList<>());

        solver.solve();

        assertEquals(solution, solver.getSolutions());
    }
    
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

        String solution = "31.6: 8.6, 4.0, 5.0, 22.0\n100.0: 16.4, 10.8";

        assertEquals(solution, solver.getSolutions());

        cutsList.clear();

        c1 = new Measurement(20, 0);
        c2 = new Measurement(10, 0);

        rollsList.clear();

        r1 = new Measurement(31, 0);
        r2 = new Measurement(15, 0);

        cutsList.add(c1);
        cutsList.add(c2);

        rollsList.add(r1);
        rollsList.add(r2);

        solver = new Solver(cutsList, rollsList);

        solver.solve();

        solution = "31.0: 10.0, 20.0\n15.0: ";

        assertEquals(solution, solver.getSolutions());
    }
}
