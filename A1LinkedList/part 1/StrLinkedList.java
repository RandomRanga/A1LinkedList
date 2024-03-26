public class StrLinkedList {
	//creates new node
	Node head; 

	public StrLinkedList(){
		head = null;
	}

	//checks if the Linked List is empty returns true, else returns false.
	public boolean isEmpty(){
		if(head == null){
			return true;
		}
		return false;
	}

	//counts how many items are in the Linked List and then returns it. 
	public int getLength(){
		Node curr = head;
		int length = 0;
		//loops through and counts untill there are no more nodes in the list.
		while(curr != null){

			length ++;
			curr = curr.next;
		}
		return length; 
	}

	//checks to see if the inputed string exsits in the linked list
	public boolean hasValue(String s){
		Node curr = head;
		//loops through untill finds wanted value
		while (curr != null){
			if (curr.value == s){
				return true;
			}
			curr = curr.next;
		}
		return false;
	}

	//finds the value the set location and returns it or an error message.
	public String getValueAt(int i){
		Node curr = head;
		for(int j = 0; j < i; j++){
			if(curr == null || curr.next == null){
				return "please enter a valid input."; 
			}
			curr = curr.next;
		}		
		return curr.value; 
	}

	//adds a new Node at the start of the Linked List. 
	public void add(String s){
		Node newNode = new Node(s);

		// if List was empty 
		if(head == null) {
			head = newNode;			
		}
		//making the new node point to list
		else{
			newNode.next = head; 
			head = newNode;
		}
	}

	//removes a value from the Linked List if it exists
	// NOTHING HAPPENEDS IF VALUE DOESN'T EXIST SHOULD ADD SOMETHING TO SAY THAT IT DOESN'T
	// USE THE HAS VALUE METHOD.
	public void remove(String s){
		Node curr = head;
		Node prev = null; 

		while (curr != null && !(curr.value.equals(s))){
			prev = curr;
			curr = curr.next; 
		}

		if (curr != null) {      	
        	if (prev == null) {
            	head = curr.next;
        	} 
        	else {          	
            	prev.next = curr.next;
        	}
    	}		
	}

	// Prints out the Linked List in a nice order. 
	public void print(){
		Node curr = head;
		//loops through the list and prints out each value with -> added to it. 
		while (curr != null){
			System.out.print(curr.value + "->");
			curr = curr.next;
		}
		System.out.println();
	}

}