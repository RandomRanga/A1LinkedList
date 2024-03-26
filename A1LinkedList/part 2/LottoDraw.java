public class LottoDraw {

        /**
         * The main method that orchestrates the lotto draw simulation.
         * @param args The command-line arguments (unused)
         */
        public static void main(String[] args) {
            // Constants for the lotto draw
            int MAX = 40;                           // Maximum number in the lotto
            int TICKET_LENGTH = 6;                  // Length of each lotto ticket
            int NUM_TICKETS_SOLD = 100;              // Number of tickets sold for the draw
            double TICKET_COST = 10.00;             // Cost of each lotto ticket
    
            // Prize pool for different number of matches
            StrLinkedList prizePool = new StrLinkedList();
            // Defining prizes for different matches
            //add more or less to ensure there is always one more that the TICEKT_LENGTH. 
            prizePool.add("100000");                // Prize for 6 matches
            prizePool.add("10000");                 // Prize for 5 matches
            prizePool.add("1000");                  // Prize for 4 matches
            prizePool.add("100");                   // Prize for 3 matches
            prizePool.add("10");                    // Prize for 2 matches
            prizePool.add("0");                     // Prize for 1 match
            prizePool.add("0");                     // Prize for 0 matches
    
            // Objects for managing lotto tickets and winning ticket
            StrLinkedList strLinkedList = new StrLinkedList();
            StrLinkedList winningTicket = new StrLinkedList();
    
            // Display full number list
            System.out.println("Full number list:");
            // Generate and print all possible numbers
            strLinkedList.possibleNumbers(MAX);
            strLinkedList.print();
            System.out.println();
    
            // Generating and displaying the winning ticket
            System.out.println("Winning numbers:");
            // Creates  the winning ticket
            winningTicket = winningTicket.winningTicketMaker(MAX, TICKET_LENGTH);
            winningTicket.print();
            System.out.println();
    
            // Display the prize pool for different matches
            System.out.println("Prize money:");
            // Check if prize pool length matches ticket length
            if (prizePool.getLength() == TICKET_LENGTH + 1) {
                prizePool.print();
            } else {
                System.out.println("Please ensure there are the same amount of possible prizes in the prize pool as the length of the ticket.");
            }
            System.out.println();
    
            // display for all the tickets bought, prize won, matching numbers, and the actaul ticket. 
            System.out.println("Tickets bought:");
            //stores the returned value, total prize, in total prize. 
            int totalPrize = strLinkedList.lottoDraw(MAX, TICKET_LENGTH, NUM_TICKETS_SOLD, winningTicket, prizePool);
            System.out.println();
    
            // Display the number of tickets sold
            System.out.println("Total number of tickets sold: " + NUM_TICKETS_SOLD);
            
            // Calculate and display the total earnings
            double earnings = NUM_TICKETS_SOLD * TICKET_COST;
            System.out.println("Total earning: $" + earnings);
            
            // Display the total prize money given away
            System.out.print("Total prizes won:");
            System.out.println("$" + totalPrize);
            
            // Calculate and display the net profit
            double totalProfit = earnings - totalPrize;
            System.out.println("Total profit: $" + totalProfit);
        }
    }
    
