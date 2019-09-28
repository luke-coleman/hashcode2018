import java.util.*;
import java.io.*;

public class Simulation {

    ArrayList<Car> carList;
    ArrayList<Ride> rideList;

    int carsAvaliable;

    public int rows;
    public int columns;
    int cars;
    int ride;
    int bonus;
    int steps;

    public Simulation() {

    }


    /**
     * A method that effectively controls the world and what order things happen.
     *
     * @param worldAndRidesFileName - the input file that we're using to get rides and allocate them to different cars.
     */

    public void Run(String worldAndRidesFileName) {

        try {

            carList = new ArrayList<>(); //creates a new arrayList where Car Objects are then stored.
            rideList = new ArrayList<>(); //creates a new arrayList where Ride Objects are then stored, these are not yet allocated to cars.

            BufferedReader br = new BufferedReader(new FileReader(new File(worldAndRidesFileName))); //uses buffered reader to read the worldAndRides file.

            String[] details = br.readLine().split(" "); //the array details is initialised here as a string, since the text file will be stored as a string. the readLine feature reads the line left to right and the split means that when it comes across a space " " e.g. 200 50, it will store it into the individual values into the array, instead of index 0 being the whole of the first line.
            int[] parsed = new int[details.length];//then a new array parsed is then initialised to the size of details. This is so that when the conversion takes place on the next line, it doesn't run out of space or is too big, taking up memory when it doesn't need to.

            for (int i = 0; i < details.length; i++) //then uses the length of details again to be the constraint.
            {
                parsed[i] = Integer.parseInt(details[i]); //then for each index of the array details, it is converted from a string to an int and stored in the array parsed. cite Integer api here
            }

            rows = parsed[0]; //index 0 of parsed is where the the number of city rows are stored.
            columns = parsed[1]; //index1 of parsed is where the number of city columns are stored.
            cars = parsed[2]; //index2 of parsed is where the number of cars are stored.
            ride = parsed[3]; //index3 of parsed is where the number of rides are stored.
            bonus = parsed[4]; //index4 of parsed is where the bonus amount is stored.
            steps = parsed[5]; //index5 of parsed is where the number of steps in the simulation are stored.

            rideToList(br); //calls the rideToList method, which is designed to create Ride Objects and store them in an arrayList, the bufferedReader is passed through to keep track of current progress.

            orderRides();

            createCars(cars); //calls the createCars method is called and has the variable cars passed to it - this is so it knows the number of car objects it needs to create.

            allocateRides(); //calls the assignRides method and passes through the allocation file, so that cars can be assigned the correct ride.


        } catch (IOException e) {
            e.printStackTrace(); //if there is an error, then an error message is printed out.
        }


    }


    /**
     * The createCars method, is designed to take the parameter x, which designates how many cars are going to be made in accord to the worldAndRides.
     *
     * @param x - the number of cars that will be created.
     */

    public void createCars(int x) {

        for (int i = 0; i < x; i++) { //essentially, for the amount of cars there are:

            Car c = new Car(i); //creates a new object of car, where i is the assigned id number.
            carList.add(c); //the instance of car is then added to an arrayList.

        }

        carsAvaliable = carList.size();

    }


    /**
     * The rideToList method, is designed to take a parameter reader, which passes on the bufferedReader used in Run() in order to then begin getting the ride parameters and
     * putting them into the rideList.
     *
     * @param reader - the bufferedReader used in the Run method, allows for the bufferedReader to analyse the rest worldAndRides fields.
     */

    public void rideToList(BufferedReader reader) {

        for (int i = 0; i < ride; i++) { //essentially, for each ride that there is...

            try {

                String[] details = reader.readLine().split(" "); //create an array that is filled with the parameters for each ride, which is separated whenever there is a space.
                int[] parsed1 = new int[details.length]; //another array that is the same length as the details array.

                for (int n = 0; n < details.length; n++) //then uses the length of details again to be the constraint.
                {
                    parsed1[n] = Integer.parseInt(details[n]); //then for each index of the array details, it is converted from a string to an int and stored in the array parsed. cite Integer api here
                }

                Ride r = new Ride(); //creates a new object of ride.

                r.id = i; //the unique id of the ride is the same as i. This is used in order to identify which ride is allocated to which car.
                r.RideFromRow = parsed1[0]; //the RideFromRow is allocated whatever is in parsed1 at that index.
                r.RideFromColumn = parsed1[1]; //the RideFromColumn is allocated whatever is in parsed1 at that index.
                r.RideToRow = parsed1[2]; //the RideToRow is allocated whatever is in parsed1 at that index.
                r.RideToColum = parsed1[3]; //the RideToColum is allocated whatever is in parsed1 at that index.
                r.earliestStart = parsed1[4]; //the earliestStart is allocated whatever is in parsed1 at that index.
                r.latestFinish = parsed1[5]; //the latestFinish is allocated whatever is in parsed1 at that index.

                rideList.add(r); //adds the object of ride to the ride

            } catch (IOException e) {
                e.printStackTrace(); //if there is an error, then an error message is printed out.
            }
        }

    }


    /**
     * A bubble sort which sorts all rides from lowest to highest depending on their earliest start.
     *
     */

    public void orderRides() {

        Ride temp = null; //initalises a object of ride called temp, this will be used when swapping data around.

        for(int i = 0; i < rideList.size(); i++){ //go through the rideList

            for(int j = 0; j < rideList.size()-i-1; j++){

                if(rideList.get(j).getEarliestStart() > rideList.get(j+1).getEarliestStart()){ //if whatever at index j in the rideList is greater than what's at the next one along, then...

                    temp = rideList.get(j); //store whats in j into temp.
                    rideList.set(j, rideList.get(j+1)); //set what is at j to what is at j+1.
                    rideList.set(j+1, temp); //set what is at j+1 to temp, swapping these two values around.

                }
            }
        }
    }


    /**
     * A method which determines which ride to give which car, it attempts to give all cars an approximately even distribution of rides.
     *
     */

    public void allocateRides() {

        while(carsAvaliable > 0) { //whilst our cars available is more than 0

            for (Car car : carList) { //foreach object of car

                findRide(car); //find a ride for that object of car.

            }
        }

        for(Car car : carList) {

            String formatted = car.getAllocatedRideId().toString().replace("[", "").replace("]", "").replace(",", ""); //removes the [, ] and ,'s
            System.out.println(car.assignedRides().size() + " " + formatted); //prints out the size, followed by the ride allocation for each car.

        }
    }


    /**
     * Go's through the ride list and then makes sure that the car can handle that ride, if it cannot - then
     *
     * @param car - the object of car that we're working with in allocate ride.
     */

    public void findRide(Car car) {

        boolean rideFound = false; //initalise a boolean called ridefound, this will determine when we have found rides for our cars.
        Ride selectedRide = null; //initalise a Ride type that can.

        for (Ride ride : rideList) { //for each object of ride in the ride list.

            if(car.getStepsTaken() + totalDistance(ride, car) < steps) { //so long as the steps taken for that ride is less than the max number of steps.
                car.setAssignedRides(ride); //then assign that ride to the car.

                rideFound = true; //set ride found to true
                selectedRide = ride; //we need to store this object in a variable, so we can delete it as we cannot delete items from an arrayList in side the for loop.
                break; //break out of the for loop.
            }
        }

        if(rideFound) { //after we have been through and found a ride, then remove that ride from the ride list.

            car.setCurrentRow(selectedRide.getRideToRow()); //we then update the cars current row to the end of the newly allocated ride.
            car.setCurrentColumn(selectedRide.getRideToColum()); //we then update the cars current column to the end of the newly allocated ride.

            car.addStepsTaken(totalDistance(selectedRide, car)); //updates

            rideList.remove(rideList.indexOf(selectedRide)); //we then remove the selected ride from the arrayList which stores rides. This is done as removing the index of that ride, was since just a remove statement depended on the id to remove it - but the ride id might be pointing to another ride as the rides would move index upon another rides deletion.

        }
        else {

            carsAvaliable--; //if we have been through the whole rides list, and rideFound is still set to false, then we know that this car cannot take anymore rides.

        }
    }


    /**
     * A method that works out the total number of steps that will be undertaken when a car does a ride.
     *
     * @param ride - the current object of ride we're working with in the find ride method.
     * @param car - the current object of car we're working with, also in the find ride method.
     *
     * @return - the entire number of steps that it will take to complete
     */

    public int totalDistance(Ride ride, Car car) {

        int distanceToStart = Math.abs(car.getCurrentRow() - ride.getRideFromRow()) + Math.abs(car.getCurrentColumn() - ride.getRideFromColumn()); //works out the cars distance to the start.
        int rideDistance = Math.abs(ride.getRideFromRow() - ride.getRideToRow()) + Math.abs(ride.getRideFromColumn() - ride.getRideToColum()); //works out the distance of the ride.

        return distanceToStart + rideDistance; //returns the sum of these values

    }
}