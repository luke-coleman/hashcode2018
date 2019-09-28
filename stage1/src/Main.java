import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        //Runs the automated test files:
        String worldAndRidesFileName = args[0];
        String allocationFileName = args[1];

        //Test case 1:
        //String worldAndRidesFileName = "test1a.in";
        //String allocationFileName = "test1a.out";

        //Test case 2:
        //String worldAndRidesFileName = "test1b.in";
        //String allocationFileName = "test1b.out";

        //Test case 3:
        //String worldAndRidesFileName = "test2.in";
        //String allocationFileName = "test2.out";

        //Test case 4:
        //String worldAndRidesFileName = "test3.in";
        //String allocationFileName = "test3.out";

        //Test case 5:
        //String worldAndRidesFileName = "test4.in";
        //String allocationFileName = "test4.out";

        //Test case 6:
        //String worldAndRidesFileName = "d_metropolis.in";
        //String allocationFileName = "d_metropolis.out";

        //Test case 7, less than 6 world parmeters
        //String worldAndRidesFileName = "worldParamFail.in";
        //String allocationFileName = "worldParamFail.out";

        //Test case 8, less than 6 items in rides
        //String worldAndRidesFileName = "rideAssignFail.in";
        //String allocationFileName = "rideAssignFail.out";

        //Test case 9, duplicate ride
        //String worldAndRidesFileName = "duplicate.in";
        //String allocationFileName = "duplicate.out";

        //Test case 10, incorrect number of lines
        //String worldAndRidesFileName = "wrongLines.in";
        //String allocationFileName = "wrongLines.out";

        //Test case 11, exceeds number of steps - shouldn't print an error
        //String worldAndRidesFileName = "stepsExceed.in";
        //String allocationFileName = "stepsExceed.out";

        //Test case 12, my test case, expected score is 12.
        //String worldAndRidesFileName = "LukeTest.in";
        //String allocationFileName = "LukeTest.out";



        //modify the following to launch the simulation which would read the two input files, e.g.
        Simulation s = new Simulation();
        s.Run(worldAndRidesFileName, allocationFileName);
    }

}
