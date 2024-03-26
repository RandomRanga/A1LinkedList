/**
* Represents a node in a linked list.ddssa
*/
public class Node {
   // The value stored in this node
   String value;

   // Reference the next node in the linked list
   Node next;

   /**
	* Constructs a node with a given value.
	* @param s The value getting stored in the node
	*/
   protected Node(String s) {
	   // Assign the given value to this node
	   value = s;
	   // Initialize the next node as null
	   next = null;
   }
}