//package ../stage1/src;
import java.io.*;
import java.util.*;

    public class Simulation {

        //private String[] details;
        ArrayList<Car> carList;
        ArrayList<Ride> rideList;
        public int rows;
        public int columns;
        int cars;
        int ride;
        int bonus;
        int steps;

        public Simulation() throws FileNotFoundException {


        }


        /**
         * The run method is a method that accepts two parameters that sets up the entire simulation as well as executes it.
         *
         * @param worldAndRidesFileName - the .in file that has the world restrictions, as well as all of the rides.
         * @param allocationFileName - the .out file contains all of the ride allocations, in terms of what car is doing what.
         *
         * **/

        public void Run(String worldAndRidesFileName, String allocationFileName) {

            try {

                carList = new ArrayList<>(); //creates a new arrayList where Car Objects are then stored.
                rideList = new ArrayList<>(); //creates a new arrayList where Ride Objects are then stored, these are not yet allocated to cars.

                BufferedReader br = new BufferedReader(new FileReader(new File(worldAndRidesFileName))); //uses buffered reader to read the worldAndRides file.

                String[] details = br.readLine().split(" "); //the array details is initialised here as a string, since the text file will be stored as a string. the readLine feature reads the line left to right and the split means that when it comes across a space " " e.g. 200 50, it will store it into the individual values into the array, instead of index 0 being the whole of the first line.
                int[] parsed;//then a new array parsed is then initialised to the size of details. This is so that when the conversion takes place on the next line, it doesn't run out of space or is too big, taking up memory when it doesn't need to.
                parsed = new int[details.length];

                for (int i = 0; i < details.length; i++) //then uses the length of details again to be the constraint.
                {
                    parsed[i] = Integer.parseInt(details[i]); //then for each index of the array details, it is converted from a string to an int and stored in the array parsed. cite Integer api here
                }

                if(parsed.length != 6) {

                    System.out.println("The World Parameters must include 6 values");
                    System.exit(1);

                }


                rows = parsed[0]; //index 0 of parsed is where the the number of city rows are stored.
                columns = parsed[1]; //index1 of parsed is where the number of city columns are stored.
                cars = parsed[2]; //index2 of parsed is where the number of cars are stored.
                ride = parsed[3]; //index3 of parsed is where the number of rides are stored.
                bonus = parsed[4]; //index4 of parsed is where the bonus amount is stored.
                steps = parsed[5]; //index5 of parsed is where the number of steps in the simulation are stored.

                if(rows > 10000 || columns > 10000 || cars > 1000 || ride > 10000 || bonus > 10000 || steps > 1000000000) {

                    System.out.println("The input file contains a value greater than the specified value in the Hashcode Specification.");
                    System.exit(1);

                }

                rideToList(br); //calls the rideToList method, which is designed to create Ride Objects and store them in an arrayList, the bufferedReader is passed through to keep track of current progress.

                createCars(cars); //calls the createCars method is called and has the variable cars passed to it - this is so it knows the number of car objects it needs to create.

                assignRides(allocationFileName); //calls the assignRides method and passes through the allocation file, so that cars can be assigned the correct ride.

                duplicateRides(); //Checks for duplicated rides.

                Simulate(); //after all of the previous steps have been taken, the simulation is then ran.


            } catch (IOException e) {
                e.printStackTrace(); //if there is an error, then an error message is printed out.
            }
        }


        /**
         * A method which is designed to find duplicate rides, and stop the program should any duplicate rides have been found.
         */

        public void duplicateRides() {

            ArrayList<Integer> dupeCheck = new ArrayList<Integer>(); //creates a new arrayList called dupeCheck.

            for(Car car : carList) { //for each car in the carlist.

                for(Ride ride : car.assignedRides) { //for each ride in the current car's assigned rides.

                    dupeCheck.add(ride.getId()); //add the ride id to the duplication check arraylist..

                }
            }

            //System.out.println(dupeCheck);

            for(int i = 0; i < dupeCheck.size(); i++) { //for each value in dupeCheck

                int check = dupeCheck.get(i); //set the check value to whatever is at index i.
                //System.out.println("Currently checking for duplicates of ride " + check);

                for(int z = i+1; z < dupeCheck.size(); z++) { //for each value in dupeCheck, uses i+1 to make sure it never compares itself to itself.

                    int compareTo = dupeCheck.get(z); //value we'll be comparing to.
                    //System.out.println("Currently comparing " + check + " with " + compareTo);

                    if(check == compareTo) { //if the check and compareto are the same

                        System.out.println("There is a duplicate ride being assigned to a vehicle!"); //print an error message.
                        System.exit(1); //exit the program.

                    }
                }
            }
        }


        /**
         * The createCars method, is designed to take the parameter x, which designates how many cars are going to be made in accord to the worldAndRides.
         *
         * @param x - the number of cars that will be created.
         *
         */

        public void createCars(int x) {

            for (int i = 0; i < x; i++) { //essentially, for the amount of cars there are:

                int id = i; //assigns each car an identification number to make sure the correct route information is being assigned to it.
                Car c = new Car(i); //creates a new object of car, where i is the assigned id number.
                carList.add(c); //the instance of car is then added to an arrayList.

            }
        }


        /**
         * The rideToList method, is designed to take a parameter reader, which passes on the bufferedReader used in Run() in order to then begin getting the ride parameters and
         * putting them into the rideList.
         *
         * @param reader - the bufferedReader used in the Run method, allows for the bufferedReader to analyse the rest worldAndRides fields.
         *
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

                    if(parsed1.length != 6) {

                        System.out.println("There must be 6 values in a ride object, the pickup x and y, the drop off x and y, the earliest start and earliest finish.");
                        System.exit(1);

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

            if(rideList.size() < ride) {

                System.out.println("The amount of rides is less than the defined number that there will be.");
                System.exit(1);

            }
            else if (rideList.size() > ride) {

                System.out.print("The amount of rides is more than the defined number that there will be.");
                System.exit(1);

            }
        }


        /**
         * The assignRides method, takes a parameter which determines which car is going to have what routes and then assigns each car those routes according
         * to the allocation file.
         *
         * @param allocationFileName - the .out file which contains each of the ride allocations.
         *
         */

        public void assignRides(String allocationFileName) {

            for (Car car : carList) { //foreach object of car in the carList.

                int checkvalue = 0;

                try {



                    BufferedReader br = new BufferedReader(new FileReader(new File(allocationFileName))); //creates a bufferedReader which looks for whatever file is currently being allocated in the allocationFileName variable.


                    for (int i = 0; i < car.getId(); i++) { //essentially, for when i is less than the id, read the lines until it's the same as the id, meaning we pass all the other information that's not needed. e.g. Car ID 2 would need to go through line 0 and 1 to get to it's allocation line.
                        String skip = br.readLine(); //stores it in the String skip, this is where it's temporarily held whilst we iterate over the lines we don't need.
                    }

                    String[] routesString = br.readLine().split(" "); //when it's skipped all the other allocations, it then goes through and reads the line and stores it in the array, splitting it by space.
                    int[] parsed = new int[routesString.length]; //then creates another array which is the same length as routesString, just as an int.

                    for (int i = 0; i < routesString.length; i++) { //then uses the length of routesString to be the constraint.
                        parsed[i] = Integer.parseInt(routesString[i]);  //then for each index of the array details, it is converted from a string to an int and stored in the array parsed.
                    }

                    for (int i = 0; i < parsed.length; i++) { //then, for the length of parsed...

                        if (i != 0) { //if the index isn't 0... (we don't want index zero since, for example the allocationFile is 2 0 1, 2 is how many rides have been allocated and we don't want this, otherwise we could be allocating a ride that already exists or cause an outOfBoundsException.
                            car.setAssignedRides(rideList.get(parsed[i])); //set the rides to what's at parsed i.
                        }
                        else{

                            checkvalue = parsed[i];

                        }
                    }


                } catch (IOException e) {
                    e.getStackTrace(); //if there is an error, then an error message is printed out.
                }

                if(car.assignedRides.size() != checkvalue) {

                    System.out.println("The car's list is not the same size as the specified amount in the Allocation File.");
                    System.exit(1);
                }
            }
        }


        /**
         * The Simulate method, uses all of the previously created cars with allocated rides to run a simulation and generate a score based on the
         * performance of the cars.
         *
         */

        public void Simulate() {

            int totalScore = 0; //declares the totalScore variable.

            for (int i = 0; i < carList.size(); i++) { //essentially, for each object in the carList

                int stepsTaken = 0; //set the steps taken
                Car currentCar = carList.get(i); //set the currentCar that we're working with to whatever the value of i is at the moment.

                for (int r = 0; r < currentCar.assignedRides().size(); r++) { //essentially, for each ride that a car has

                    Ride currentRide = currentCar.assignedRides().get(r); //set the currentRide to whatever is at index r in the current car's assigned rides arrayList.
                    currentCar.updateRide(currentRide); //updates the car's global variables with the ride values.

                    //System.out.println("The car's current Row is " + currentCar.getcurrentRow());
                    //System.out.println("The car's current Col is " + currentCar.getcurrentColumn());

                    int fromCurrentToStart = Math.abs(currentCar.getcurrentRow() - currentRide.getRideFromRow()) + Math.abs(currentCar.getcurrentColumn() - currentRide.getRideFromColumn()); //works out the distance between where the car is now and where it needs to be. We use Math.abs here so that if the value is negative, it removes that and makes it a positive value. So for example, the car is at 0,0 and needs to get to 1,3 (0-1)=1 and (0-3)=3, meaning that it will take 4 steps to get from 0,0 to 1,3.
                    //System.out.println("It will take " + fromCurrentToStart + " to get from current location to start.");
                    stepsTaken+=fromCurrentToStart; //adds the how long it'll take to get from it's current row and column to the start row and column. Using our example from earlier, it stepsTaken is currently 0, but it will take 4 steps to reach the start, so the stepsTaken is now set to 4.

                    if(stepsTaken > steps) {
                        continue;
                    }

                    if(stepsTaken <= currentRide.getEarliestStart()) { //if the stepsTaken are less than or equal to the earliest start of the current ride, then we'll have to do something so that the car doesn't start the route before it's supposed to.

                        int waitTime = Math.abs(currentRide.getEarliestStart() - stepsTaken); //waitTime works out how many steps we'll have to be waiting. It gets the current rides earliest start and takes away the number of steps we've done so far. This will then show how long we have to wait. Let's say from our previous example, our earliest start is step 5. We've arrived on step 4. 5-4 = 1, so we'll need to wait a step before we can start.
                        //System.out.println("The car will have to wait " + waitTime + " steps before departing.");
                        stepsTaken+=waitTime; //we then add the waitTime to our steps taken, we now have done a total of 5 steps.
                        //System.out.println("It has been " + stepsTaken + " steps.");

                        if(stepsTaken <= currentRide.getEarliestStart()) { //if the stepsTaken are less than or equal to the earliestStart then.
                            totalScore += bonus; //Add the bonus to the totalScore. If our bonus is 2, now our total score is 2.
                            //System.out.println(totalScore + " is the current score.");
                        }

                    }
                    else{

                        //System.out.println("Car is not eligable for bonus on this route.");

                    }

                    currentCar.setOccupation(); //sets the car's occupation to true.

                    currentCar.setcurrentRow(currentRide.getRideFromRow()); //sets the cars current row to the start row.
                    currentCar.setcurrentColumn(currentRide.getRideFromColumn()); //sets the cars current column to the start column.

                    int journeyDistance = Math.abs(currentCar.getcurrentRow() - currentRide.getRideToRow()) + Math.abs(currentCar.getStartColumn() - currentRide.getRideToColum()); //we now need to calculate the distance between the start and the end point of the route. For example, we are at 1,3 and our drop off is 2,4 - meaning it will take 3 steps to get there.
                    //System.out.println(journeyDistance + " is how long it will take to get from starting position to the end.");
                    stepsTaken+=journeyDistance; //this will add the journeyDistance to the stepsTaken - this will mean it has now taken us 8 steps to get to where we are at the moment, using our current example.
                    //System.out.println(stepsTaken + " steps have been taken");

                    if(stepsTaken > steps) {
                        continue;
                    }

                    if(stepsTaken <= currentRide.getLatestFin()) { //if the steps taken are less than or equal to the currentRides latest finish, then
                        int distanceScore = currentCar.distanceScore(); //get the car to calculate the distance on this ride. In the case of our example it's 3.
                        //System.out.println("The score this will generate for the distance is " + distanceScore);
                        totalScore+= distanceScore; //the distance score calculated will then be added to our totalScore. If we're assuming the car finished before whatever the rides latest finish was, then we can assume this will be 3 + 2 (2 being the bonus allocated from earlier).
                    }
                    else{
                        //System.out.println("Car is not eligable for distance score on this route.");
                    }

                    currentCar.setcurrentRow(currentRide.getRideToRow()); //we then set the car's current row to the current rides finishing row. This will be for the next ride, if there is one.
                    currentCar.setcurrentColumn(currentRide.getRideToColum()); //we then set the car's current column to the current rides finishing row. This will be for the next ride, if there is one.

                    currentCar.setOccupation(); //sets the occupation to false.

                    //System.out.println(" ");
                }
            }
            //System.out.println("Score: " + totalScore);
            System.out.println(totalScore);
        }
    }