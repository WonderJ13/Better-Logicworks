import java.util.ArrayList;
public class Board
{
	private ArrayList<Wire> wires;
	
	public Board()
	{
		wires = new ArrayList<Wire>();
	}
	
	public void addWire(int x, int y) {
		wires.add(new Wire(x, y, 5, 0));
	}
	
	public int amountOfLines() {
		return wires.size();
	}
	
	public Wire getWire(int index) {
		return wires.get(index);
	}
}
