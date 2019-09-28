import java.util.*;

public class Car {

    int id;
    ArrayList<Ride> assignedRides = new ArrayList<>(); //assignedRides

    int currentRow;
    int currentColumn;

    int stepsTaken;


    /**
     * Car constructor, this initalises the variables which determine that car's starting position on the grid and gives the car it's ID.
     *
     * @param number - an integer value provided by the i in the for loop where it's created. This is used to uniquely identify each car that is in the carList.
     */

    public Car(int number) {

        currentColumn = 0; //sets the currentColumn to 0.
        currentRow = 0; //sets the currentRow to 0.

        id = number; //sets the car's ID number to the value that was passed through.
    }


    /**
     * The getCurrentRow method, is an accessor that gets the current row of the car.
     *
     * @return the car's Current Row coordinate.
     *
     */

    public int getCurrentRow() {

        return currentRow; //get's current row.
    }


    /**
     * The setCurrentRow method, takes a value of int which then changes the car's current positioning on the map.
     *
     * @param currentRow - an integer that that is used to set the currentRow to a new value.
     *
     */

    public void setCurrentRow(int currentRow) {

        this.currentRow = currentRow; //sets the currentRow to the new value provided..
    }


    /**
     * The getCurrentColumn method, is an accessor that gets the current column of the car.
     *
     * @return the cars current position in terms of the column.
     *
     */

    public int getCurrentColumn() {

        return currentColumn; //returns the cars current column.
    }


    /**
     * The setCurrentColumn method, take a value of int which then changes the cars current column.
     *
     * @param currentColumn - an integer that uis used to set the current column to a new value.
     *
     */

    public void setCurrentColumn(int currentColumn) {

        this.currentColumn = currentColumn; //sets the current column to the variable in the params.
    }



    /**
     * The assignedRides method, shows all of the objects of ride a particular car has been assigned.
     *
     * @return an arrayList with all of the rides a car has been assigned.
     *
     */

    public ArrayList<Ride> assignedRides() {

        return assignedRides; //returns the assignedRides arrayList.
    }


    /**
     * The setAssignedRides method, accepts an object of Ride which is then added
     *
     * @param newRide - the ride that is being allocated to this car.
     *
     */

    public void setAssignedRides(Ride newRide) {

        assignedRides.add(newRide); //adds the new ride to the assignedRides arrayList.
    }


    /**
     * Gets the assigned ride Id's and puts them into a arrayList and then returns the arrayList.
     *
     * @return rideIds - an arrayList of ride Id's.
     */

    public ArrayList<Integer> getAllocatedRideId() {

        ArrayList<Integer> rideIds = new ArrayList<>();

        for(Ride ride : assignedRides) { //foreach ride assignedrides

            rideIds.add(ride.getId()); //get the id of the ride and add it to rideIds.

        }

        return rideIds;
    }


    /**
     * Gets the number of steps taken by a car.
     *
     * @return stepsTaken - the amount of steps the car has taken.
     */

    public int getStepsTaken() {

        return stepsTaken;
    }


    /**
     * Adds the number of steps taken to the previous steps taken.
     *
     * @param newSteps - the number of steps to be added to the steps taken.
     */

    public void addStepsTaken(int newSteps) {

        stepsTaken+=newSteps;

    }

}
