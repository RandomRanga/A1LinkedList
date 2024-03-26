public class Node { 
	String value; 
	Node next; 

	//says should make this private however then errors occur
	protected Node(String s){
		value = s;
		next = null;
	}
}