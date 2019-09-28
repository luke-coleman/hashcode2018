public class Main {

    public static void main(String[] args) {

        //Production:
        String worldAndRidesFileName = args[0];

        //Test Case 1:
        //String worldAndRidesFileName = "a_example.in";

        //Test Case 2:
        //String worldAndRidesFileName = "b_should_be_easy.in";

        //Test Case 3:
        //String worldAndRidesFileName = "c_no_hurry.in";

        //Test Case 4:
        //String worldAndRidesFileName = "d_metropolis.in";

        //Test Case 5:
        //String worldAndRidesFileName = "e_high_bonus.in";

        //modify the following to launch the allocation which would read the rides file and print
        //the allocation to the standard output, e.g.

        Simulation s = new Simulation();
        s.Run(worldAndRidesFileName);
    }
}