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

    public void testSingleRoll() {
        ArrayList<Measurement> rollList = new ArrayList<>();
        ArrayList<Measurement> cutsList = new ArrayList<>();

        rollList.add(new Measurement(0, 5));

        cutsList.add(new Measurement(0, 1));
        cutsList.add(new Measurement(0, 4));
        cutsList.add(new Measurement(0, 2));
        cutsList.add(new Measurement(0, 2));

        Solver solver = new Solver(cutsList, rollList);

        solver.solve();

        String solution = "0.5: 0.4, 0.1";
        System.out.println(solver.getSolutions());
        assertEquals(solution, solver.getSolutions());
    }

    public void testMultipleRolls() {
        ArrayList<Measurement> rollsList = new ArrayList<>();
        ArrayList<Measurement> cutsList = new ArrayList<>();
    
        cutsList.add(new Measurement(12, 6));
        cutsList.add(new Measurement(12, 6));
        cutsList.add(new Measurement(12, 9));

        rollsList.add(new Measurement(25, 2));
        rollsList.add(new Measurement(30, 0));

        Solver solver = new Solver(cutsList, rollsList);

        solver.solve();

        String solution = "25.2: 12.6, 12.6\n30.0: 12.9";

        System.out.println(solver.getSolutions());

        assertEquals(solution, solver.getSolutions());

        Measurement c1 = new Measurement(10, 8);
        Measurement c2 = new Measurement(16, 4);
        Measurement c3 = new Measurement(22, 0);
        Measurement c4 = new Measurement(5, 0);
        Measurement c5 = new Measurement(4, 0);
        Measurement c6 = new Measurement(8, 6);

        Measurement r1 = new Measurement(31, 6);
        Measurement r2 = new Measurement(100, 0);

        cutsList.clear();
        rollsList.clear();

        cutsList.add(c1);
        cutsList.add(c2);
        cutsList.add(c3);
        cutsList.add(c4);
        cutsList.add(c5);
        cutsList.add(c6);

        rollsList.add(r1);
        rollsList.add(r2);

        solver = new Solver(cutsList, rollsList);

        solver.solve();

        solution = "31.6: 4.0, 16.4, 10.8\n100.0: 8.6, 5.0, 22.0";

        System.out.println(solver.getSolutions());

        assertEquals(solution, solver.getSolutions());
    }
}
