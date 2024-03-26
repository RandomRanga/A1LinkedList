import java.util.Random;

/**
* Represents a linked list of strings and usefully methods
*/
public class StrLinkedList {
    // The head of the linked list
    Node head;

    /**
     * Constructs an empty linked list.
     */
    public StrLinkedList() {
        head = null;
    }

    /**
     * Checks if the linked list is empty.
     * @return true if the linked list is empty, false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

	/**
     * Counts the number of items in the linked list.
     * @return The number of items in the linked list
     */
    public int getLength() {
        Node curr = head;
        int length = 0;
		//loops through the list and counts number of nodes
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        return length;
    }

	/**
     * Checks if the given value exists in the linked list
     * @param s The value to search for
     * @return true if the value exists, false otherwise
     */
	public boolean hasValue(String s){
		Node curr = head;
		//loops through the list untill finds wanted value
		while (curr != null){
			if (curr.value.equals(s)){
				return true;
			}
			curr = curr.next;
		}
		return false;
	}

	/**
     * gets the value at the specified index in the linked list.
     * @param i The index of the value to get
     * @return The value at the specified index, or an error message if the index is invalid
     */
	public String getValueAt(int i){
		Node curr = head;
		//loops through list to find node at specified index
		for(int j = 0; j < i; j++){
			//returns an error message if index is invalid. 
			if(curr == null || curr.next == null){
				return "please enter a valid input."; 
			}
			curr = curr.next;
		}		
		return curr.value; 
	}

	/**
     * Adds a new node with the given value to the start of the linked list.
     * @param s The value to add
     */
	public void add(String s){
		Node newNode = new Node(s);

		// if List was empty, new node becomes head 
		if(head == null) {
			head = newNode;			
		}
		//else link the new node to the head and update the head
		else{
			newNode.next = head; 
			head = newNode;
		}
	}

	/**
     * Removes the node containing the specified value from the linked list.
     * @param s The value to remove
     */
    public void remove(String s) {
        Node curr = head;
        Node prev = null;
        // loop through list to find the node with the specified value
        while (curr != null && !(curr.value.equals(s))) {
            prev = curr;
            curr = curr.next;
        }
        // If the node is found, remove it from the list
        if (curr != null) {
            if (prev == null) {
                head = curr.next;
            } else {
                prev.next = curr.next;
            }
        }
    }

	/**
     * Prints the values of the linked list in order.
     */
	public void print(){
		Node curr = head;
		//loops through the list and prints out each value with -> added to it. 
		while (curr != null){
			System.out.print(curr.value + "->");
			curr = curr.next;
		}
		System.out.println();
	}


	//MEHTODS FOR THE LOTTO, PART 2.
	////
	////
	////
	////
	

	/**
     * Adds all possible numbers up to a maximum value to the linked list.
     * @param MAX The maximum value of the numbers
     */ 
	public void possibleNumbers(int MAX){
		//adds each possible value into the linkedlist
		for (int i = 1; i <= MAX; i++){
			add(String.valueOf(i));
		}
	}


	/**
     * Generates a ticket with random numbers within a specified range.
     * @param MAX The maximum value of the numbers
     * @param TICKET_LENGTH The length of the ticket
     */
	public void ticket(int MAX, int TICKET_LENGTH){
		//creates instance of random
		Random rand = new Random();	
		//loops through deleting random numbers untill there is the wanted length
		while(getLength() > TICKET_LENGTH){
			int randomNumber = rand.nextInt(MAX)+1;
			remove(String.valueOf(randomNumber));
		}			
	}


	
	 /**
     * Generates a winning ticket.
     * @param MAX The maximum value of the numbers
     * @param TICKET_LENGTH The length of the ticket
     * @return The winning ticket
     */
	public StrLinkedList winningTicketMaker(int MAX, int TICKET_LENGTH){
		StrLinkedList winningTicket = new StrLinkedList();
		// Generate a winning ticket by adding all possible numbers and then generating a ticket
		winningTicket.possibleNumbers(MAX);
		winningTicket.ticket(MAX, TICKET_LENGTH);
		return winningTicket;
	}

	
	/**
     * Simulates the lotto draw.
     * @param MAX The maximum value of the numbers
     * @param TICKET_LENGTH The length of the ticket
     * @param NUM_TICKETS_SOLD The number of tickets sold
     * @param winningTicket The winning ticket
     * @param prizePool The prize pool
     * @return The total prize amount
     */
	public int lottoDraw(int MAX, int TICKET_LENGTH, int NUM_TICKETS_SOLD, StrLinkedList winningTicket, StrLinkedList prizePool){
		int totalPrize = 0;
		//runs it for however many tickets we want. 
		for (int i = 0; i < NUM_TICKETS_SOLD; i++) {
			//creates a ticket 
			StrLinkedList ticket = new StrLinkedList();
			ticket.possibleNumbers(MAX);
			ticket.ticket(MAX, TICKET_LENGTH);

			//count number of matching numbers 
			int numMatchingNumbers = countMatchingNumbers(ticket, winningTicket);
			

			//match the ticket to how much they won based off the amount of matching numbers
			int prize = calcPrize(numMatchingNumbers, prizePool);
			totalPrize += prize;
			

			//prints out the ticket with the prize and macthing numbers in a nice format. 
			System.out.print ("Prize won: $" + prize +  " Matching Numbers: " + numMatchingNumbers + " Ticket: ");
			ticket.print();		
		}
		return totalPrize;
	}


	/**
     * Counts the number of matching numbers between a ticket and a winning ticket.
     * @param ticket The ticket to compare
     * @param winningTicket The winning ticket
     * @return The number of matching numbers
     */
	public int countMatchingNumbers (StrLinkedList ticket, StrLinkedList winningTicket){
		int matching = 0;
		Node currNum = ticket.head;
		//goes through the ticket untill empty to check if any numbers match any in the winning ticket. 
		while(currNum != null){
			if (winningTicket.hasValue(currNum.value)){
				matching++;
			}
			currNum = currNum.next;
		}	
		return matching; 
	}

 	/**
     * Calculates the prize amount based on the number of matching numbers.
     * @param numMatchingNumbers The number of matching numbers
     * @param prizePool The prize pool
     * @return The prize amount
     */
	public int calcPrize(int numMatchingNumbers, StrLinkedList prizePool){
		int prize = 0;
		// Iterate through the prize pool to determine the prize based on the number of matching numbers
		for (int i =0; i < numMatchingNumbers + 1; i++){
			if(i < prizePool.getLength()){
				prize = Integer.parseInt(prizePool.getValueAt(i));
			} else{
				break; 
			}

		}
		return prize; 
	}
}






	/* 
	public int calcPrize (int numMatchingNumbers, int[] prizePool, int TICKET_LENGTH){
		
		if (prizePool.length > TICKET_LENGTH) {
            System.out.println("Error: The length of the prize pool array must be less than or equal to the ticket length.");
            return 0;
        }
		if(numMatchingNumbers <= prizePool.length){
			return prizePool[numMatchingNumbers];
		}else{
			return 0;
		}
			
	}
	*/








	// THE FOLLOWING ONLY USES StrLinkedList, HOWEVER BEST WITH TICKET LENGTH 6
	/* 

	public int lottoDraw(int MAX, int TICKET_LENGTH, int NUM_TICKETS, StrLinkedList winningTicket, int MACTCHES0, int MACTCHES1, int MACTCHES2, int MACTCHES3, int MACTCHES4, int MACTCHES5, int MACTCHES6){
		int totalPrize = 0;
		//runs it for however many tickets we want. 
		for (int i = 0; i < NUM_TICKETS; i++) {
			//creates a ticket 
			StrLinkedList ticket = new StrLinkedList();
			ticket.possibleNumbers(MAX);
			ticket.ticket(MAX, TICKET_LENGTH);

			//count number of matching numbers 
			int numMatchingNumbers = countMatchingNumbers(ticket, winningTicket);
			

			//match the ticket to how much they won based off the amount of matching numbers
			int prize = calcPrize(numMatchingNumbers, MACTCHES0, MACTCHES1, MACTCHES2, MACTCHES3, MACTCHES4, MACTCHES5, MACTCHES6);
			totalPrize += prize;

			//returns out the ticket with the prize and macthing numbers in a nice format. 
			System.out.print ("Prize won: $" + prize +  " Matching Numbers: " + numMatchingNumbers + " Ticket: ");
			ticket.print();
			
			
		}
		return totalPrize;
		

	}


	//calculates the prize depending on how many matching numbers there are
	//ONLY REALLY WORKS FOR WHEN TICKETLENGTH = 6
	public int calcPrize(int numMatchingNumbers, int MACTCHES0, int MACTCHES1, int MACTCHES2, int MACTCHES3, int MACTCHES4, int MACTCHES5, int MACTCHES6){
		switch (numMatchingNumbers) {
			//could pass in all the value for the prizes however doesn't seem would make anything easier. 
			//depending on how many numbers match then will return the correct winnings. 
			case 0:
				return MACTCHES0;
			case 1:
				return MACTCHES1;
			case 2:
				return MACTCHES2;
			case 3:
				return MACTCHES3;
			case 4:
				return MACTCHES4;
			case 5:
				return MACTCHES5;
			case 6:
				return MACTCHES6;
				//defult case shouldn't happen however just incase somehow it does, then the code won't break. 
			default: 
				return 0;
		}
	}
	*/

	
		

