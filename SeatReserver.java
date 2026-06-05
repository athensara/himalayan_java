/**
 * A class to keep track of which seats have been reserved in an arena of seats.
 * 
 * NOTE: A user may request and reserve seat 0!
 */
public class SeatReserver {

    // An array with an entry for each seat. A true value means the seat is
    // reserved.
    private boolean[] seatsReserved;

    // An array with an entry for each seat. A true value means the seat has been
    // presented to the user for consideration, but the seat is not yet reserved.
    private boolean[] seatsProposed;

    /**
     * CONSTRUCTOR
     * 
     * @param totalSeats the total number of seats in the arena
     */
    public SeatReserver(int totalSeats) {
        //initializing the instance variables
        seatsReserved = new boolean[totalSeats];
        seatsProposed = new boolean[totalSeats];
    }

    /**
     * Checks if the seat at index seatNum is reserved.
     * 
     * @param seatNum the index of the seat whose status is being queried
     * @return true if the seat is reserved
     */
    public boolean isSeatReserved(int seatNum) {
        if (seatNum >= 0 && seatNum < seatsReserved.length){
            // checking if the seat number entered is valid
            if (seatsReserved[seatNum]== true){
            return true; // returns true when seat is reserved
            } else {
                return false;
            }
        }
        return true; // returns true when seat number entered is not valid
    }

    /**
     * Reserves a single seat located at seatNum. You can assume that the user is
     * allowed to reserve the seat requested -- you do not first need to check if
     * the seat is available.
     * 
     * @param seatNum the index of the seat being reserved in the seatsReserved
     *                array
     */
    public void reserveIndivSeat(int seatNum) {
        seatsReserved[seatNum] = true ;
    }

    /**
     * Copies the values from copyFrom into copyInto (such that copyInto will be a
     * copy of copyFrom). You can assume that copyFrom and copyInto have the same
     * length.
     * 
     * @param copyFrom the array to be copied
     * @param copyInto the array which will be filled with the copy
     */
    private void copyArray(boolean[] copyFrom, boolean[] copyInto) {
        int i;
        // looping through each element in copyFrom to assign to copyInto
        for (i = 0; i < copyFrom.length; i++){
            copyInto[i] = copyFrom[i];
        }
    }

    /**
     * Checks if the total number of seats available (not reserved) >= the number of
     * seats that the user wants to reserve
     * 
     * @param numSeatsWanted the total number of seats the user wants to reserve
     * @return true if enough seats are available. The seats might not be next to
     *         each other.
     */
    public boolean isAvailable(int numSeatsWanted) {
        int i ;
        int totalAvailableSeats = 0 ;

        //finding the total number of available seats
        for (i = 0; i < seatsProposed.length; i++ ){
            if (seatsReserved[i] == false){
                totalAvailableSeats ++ ;
            }
        }
        //checking if a valid numSeatsWanted can be accommodated
        if (totalAvailableSeats >= numSeatsWanted && numSeatsWanted > 0){
            return true;
        }
        return false;
    }

    /**
     * Checks if it would be possible to reserve numSeatsWanted seats. If it is, it
     * modifies the seatsProposed array to reflect what seatsReserved would look
     * like if the requested seats were reserved too.
     * 
     * @param numSeatsWanted the total number of seats that the user wants to
     *                       reserve
     * @return false if it is not possible to reserve the seats. Return true if
     *         seatsProposed was successfully updated to show what seatsReserved
     *         would look like with the new seats reserved.
     */
    public boolean planReservation(int numSeatsWanted) {
        int i = 0;
        // checking if number of seats are available
        if (isAvailable(numSeatsWanted)){

            //modifying seatsProposed to what seatsReserved would look if seats were reserved
            copyArray(seatsReserved, seatsProposed);

            //looping until all seats wanted are accomodated legally
            while (numSeatsWanted > 0 && i < seatsProposed.length){
                if (isSeatReserved(i) == false){
                    seatsProposed[i] = true;
                    numSeatsWanted --;
                }
                i ++ ;
            }
            //returning true when possible to reserve seats
            return true;
        }
        //returning false when not possible to reserve seats
        return false;

    }

    /**
     * Updates seatsReserved to match seatsProposed
     */
    public void confirmReservation() {
        // HINT: This method should contain a single function call to a function you've
        // already written!
        copyArray(seatsProposed, seatsReserved);
    }

    /**
     * Prints the seats currently reserved
     */
    public void printSeatsReserved() {
        System.out.println(java.util.Arrays.toString(this.seatsReserved));
    }

    /**
     * Prints the seats which would be reserved if the proposed layout was accepted
     */
    public void printSeatsProposed() {
        System.out.println(java.util.Arrays.toString(this.seatsProposed));
    }

    /**
     * Runs test code.
     */
    public void testCode() {

        //System.out.println("Test code begins ----------");
        // reserveIndivSeat(0);
        // System.out.println(isSeatReserved(0));
        // copyArray(seatsReserved, seatsProposed);
        // printSeatsReserved();
        // printSeatsProposed();
        // System.out.println(isAvailable(100));
        // System.out.println("Test code ends ----------");

    }

    /**
     * Interested in trying the challenge? Here's the starter code for it! You can
     * replace isAvailable with findContiguous, and planReservation with
     * planContiguous
     * to test it! Please let Sage know in the readme if you've done this.
     */

    /**
     * Finds the start of a set of available, contiguous seats (if it exists)
     * 
     * @param numSeatsWanted the total number of seats requested
     * @return the index of the start of the contiguous sequence of seats. Returns
     *         -1 if a contiguous sequence of seats of that size does not exist
     */
    // public int findContiguous(int numSeatsWanted){

    // }

    /**
     * Checks if it would be possible to reserve numSeatsWanted seats in a
     * contiguous sequence. If it is, it modifies the seatsProposed array to reflect
     * what seatsReserved would look like if the requested seats were reserved too.
     * 
     * @param numSeatsWanted the total number of seats that the user wants to
     *                       reserve
     * @return true if it is possible to reserve the desired number of seats
     *         contiguously. Otherwise returns false.
     */
    // public boolean planContiguous(int numSeatsWanted){

    // }

}
