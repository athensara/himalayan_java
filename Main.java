import java.util.Scanner;

/**
 * This class provides the user interface for the user to interact with the seat
 * reserver.
 */

public class Main {
    /**
     * The main method. It prompts the user for information and carries out the
     * operations requested.
     * 
     * @param args unused
     */
    public static void main(String[] args) {
        SeatReserver buyer = new SeatReserver(20);

        // Run your test code. Remember to comment out this line before submitting.
        buyer.testCode();

        Scanner scanner = new Scanner(System.in);

        // Ask if they'd like to check a seat's availability, reserve a group, or quit
        System.out.println(
                "Please select an option: (c)heck availability of and reserve a single seat, (r)eserve a group of seats, or (q)uit");
        String option = scanner.nextLine();

        while (!option.equals("q")) {

            // if c for check status
            if (option.equals("c")) {
                checkAvailability(buyer, scanner);

            }

            // If r for reserve
            else if (option.equals("r")) {
                // Prompt for how many
                System.out.println("Enter a number of seats to reserve");
                if (scanner.hasNextInt()) {
                    reserveSeats(buyer, scanner);
                } else {
                    System.out.println("Please enter a number for number of seats to reserve.\n");
                    scanner.nextLine(); // Read the rest of the line.
                }
            }

            System.out.println(
                    "Please select an option: (c)heck availability of and reserve a single seat, (r)eserve a group of seats, or (q)uit");
            option = scanner.nextLine();
        }
        scanner.close();
    }

    /**
     * Provide the user interface to reserve seats
     * 
     * @param buyer   the seat reserver object
     * @param scanner the scanner to get the user's input
     */
    private static void reserveSeats(SeatReserver buyer, Scanner scanner) {
        int numSeats = scanner.nextInt();
        scanner.nextLine();

        boolean isPossible;
        // Make a plan with the seats wherever they're available
        isPossible = buyer.planReservation(numSeats);

        // If it isn't possible to make a plan, let the user know
        if (!isPossible) {
            System.out.printf("A sequence of " + numSeats + " seats meting those criteria is not available.\n");
        }

        // Otherwise, show the user the proposed seats and ask them to confirm
        else {
            // Show the current available seats (before this transaction)
            System.out.println("Before, the available seats were:");
            buyer.printSeatsReserved();

            // Show the seats after this transaction
            System.out.println("Including your seats, the available seats are:");
            buyer.printSeatsProposed();

            System.out.println("Would you like to confirm? Enter y or n.");
            String confirm = scanner.nextLine();
            // If they want to confirm, make the change to the array of reserved seats.
            if (confirm.equals("y")) {
                buyer.confirmReservation();
                System.out.println("Reservation successful.");
            }
        }
    }

    /**
     * Provide the user interface to check seat availability
     * 
     * @param buyer   the seat reserver object
     * @param scanner the scanner to get the user's input
     */
    private static void checkAvailability(SeatReserver buyer, Scanner scanner) {
        // Prompt for the seat number
        System.out.println("Enter a seat number to check");
        if (scanner.hasNextInt()) {
            int seat = scanner.nextInt();
            scanner.nextLine();

            // Print whether it's available or not
            boolean seatReserved = buyer.isSeatReserved(seat);

            // If the seat is available (not reserved)
            if (!seatReserved) {
                System.out.printf("Seat " + seat + " is available\nDo you wish to reserve it? Enter y or n.\n");

                String confirm = scanner.nextLine();

                if (confirm.equals("y")) {
                    buyer.reserveIndivSeat(seat);
                    System.out.println("Reservation successful.");
                }
            }

            else {
                System.out.printf("Seat " + seat + " is NOT available\n");
            }
        } else {
            System.out.println("Please enter a number for the seat number.\n");
            scanner.nextLine(); // Read the rest of the line.
        }
    }

}
