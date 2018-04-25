import java.util.ArrayList; //Just an array that'll increase in size automatically
public class Board
{
	private ArrayList<Wire> wires; //Instance Variable: list of wires
	/* <Wire> means the arraylist will accept objects of type Wire 
	 * This can easily be changed to <Integer> or <String> to fit
	 * any object you want */
	
	public Board() //Constructor
	{
		wires = new ArrayList<Wire>();
	}
	
	public void addWire(int x, int y) { //Public method: creates a new wire and puts it in the list
		wires.add(new Wire(x, y, 5, 0));
	}
	
	public int amountOfLines() { //Public method: returns the size of the arraylist (how many wires are there?)
		return wires.size();
	}
	
	public Wire getWire(int index) { //Returns a specific wire specified by the index
		return wires.get(index);
	}
}
