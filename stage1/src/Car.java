import java.util.*;

public class Car {

    int id;
    ArrayList<Ride> assignedRides = new ArrayList<>(); //assignedRides
    Ride currentRide;
    int currentRow;
    int currentColumn;
    int startRow;
    int startColumn;
    int destinationRow;
    int destinationColumn;
    int currentEarliestStart;
    int currentLatestFinish;
    boolean isOccupied = false;

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

    public int getcurrentRow() {

        return currentRow; //get's current row.
    }


    /**
     * The setCurrentRow method, takes a value of int which then changes the car's current positioning on the map.
     *
     * @param currentRow - an integer that that is used to set the currentRow to a new value.
     *
     */

    public void setcurrentRow(int currentRow) {

        this.currentRow = currentRow; //sets the currentRow to the new value provided..
    }


    /**
     * The getCurrentColumn method, is an accessor that gets the current column of the car.
     *
     * @return the cars current position in terms of the column.
     *
     */

    public int getcurrentColumn() {

        return currentColumn; //returns the cars current column.
    }


    /**
     * The setCurrentColumn method, take a value of int which then changes the cars current column.
     *
     * @param currentColumn - an integer that uis used to set the current column to a new value.
     *
     */

    public void setcurrentColumn(int currentColumn) {

        this.currentColumn = currentColumn; //sets the current column to the variable in the params.
    }


    /**
     * The getStartColumn method, is a mutator that gets the start column of car's ride.
     *
     * @return returns the car's start column.
     *
     */

    public int getStartColumn() {

        return startColumn; //returns the car's current start column.
    }


    /**
     * The set occupation method, sets the car to occupied. Was used for debugging and remains ceremoniously.
     *
     */

    public void setOccupation() {

        if(!isOccupied) //if isOccupied is false.
        {
            isOccupied = true; //set it to true.
        }
        else if (isOccupied) //otherwise, if isOccupied is true.
        {
            isOccupied = false; //set it to false.
        }

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
     * The updateRide method, accepts an object of Ride which then updates the cars variables with details of the current ride.
     *
     * @param ride - the object of ride that is currently going on.
     *
     */

    public void updateRide(Ride ride) {

        currentRide = ride; //the currentRide is the object of ride passed through.

        startRow = currentRide.getRideFromRow(); //calls the getRideFromRow method from the ride object that was passed through and sets the start row to the value there.
        startColumn = currentRide.getRideFromColumn(); //calls the getRideFromColumn method from the ride object that was passed through and sets the start column to the value there.
        destinationRow = currentRide.getRideToRow(); //calls the getRideToRow method from the ride object that was passed through and sets the destination row to the value there.
        destinationColumn = currentRide.getRideToColum(); //calls the getRideToColum method from the ride object that was passed through and sets the destination column to the value there.
        currentEarliestStart = currentRide.getEarliestStart(); //calls the getEarliestStart method from the ride object that was passed through and sets the earliest start to the value there.
        currentLatestFinish = currentRide.getLatestFin(); //calls the getLatestFin method from the ride object that was passed through and sets the latest finish to the value there.
    }


    /**
     * The getId method, an accessor method that gets the id of the current car.
     *
     * @return the id that the car has been assigned.
     *
     */

    public int getId() {

        return id; //returns the id of the car.
    }


    /**
     * The distanceScore method, calculates the distance of a ride if it finishes before the latest finsh.
     *
     * @return the distance score.
     */

    public int distanceScore() {

        int distanceScore = Math.abs(startRow - destinationRow) + Math.abs(startColumn - destinationColumn);
        return distanceScore;
    }

}
