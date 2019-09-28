public class Ride {

    int id;
    int RideFromRow;
    int RideFromColumn;
    int RideToRow;
    int RideToColum;
    int earliestStart;
    int latestFinish;

    public Ride() {

    }


    /**
     * The getId method, returns the unique ID that this ride has been given.
     *
     * @return returns this rides Id.
     *
     */

    public int getId()
    {
        return id; //returns the id of the ride.
    }


    /**
     * The getRideFromRow method, returns the location of where the ride will depart from.
     *
     * @return the value where the car will ride from.
     *
     */

    public int getRideFromRow() {

        return RideFromRow; //returns the row where the ride will depart from.

    }


    /**
     * The getRideFromColumn method, returns the column that the car will ride from.
     *
     * @return the column that the car will ride from.
     *
     */

    public int getRideFromColumn() {

        return RideFromColumn; //returns the column the car will leave from.
    }


    /**
     * The getRideToRow method, returns the row where the car will finish its ride on.
     *
     * @return the row where the car will end its ride.
     *
     */

    public int getRideToRow() {

        return RideToRow; //returns the row the car will finish at.
    }


    /**
     * The getRideToColum (spelt incorrectly ofcourse) method, returns the column that the car will end its ride at.
     *
     * @return the column where the car will finish ride at.
     *
     */

    public int getRideToColum() {

        return RideToColum; //returns the column that the car will finish at.
    }


    /**
     * The getEarliestStart method, returns the earliest start that the car can start the journey at.
     *
     * @return the earliest the car can start this ride, will be used for bonus points.
     *
     */

    public int getEarliestStart() {

        return earliestStart; //returns the earliestStart the car can go.
    }

    /**
     * The getLatestFinish method, returns the latest finish of the journey.
     *
     * @return the latest finish of the ride.
     *
     */

    public int getLatestFinish() {

        return latestFinish;
    }

}
