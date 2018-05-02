import java.awt.Graphics;
public class Wire extends Item
{
	private int length; //Instance Variables
	
	public Wire(int x, int y, int dir, int length) { //Constructor
		super(x, y, dir);
		source = true;
		this.length = length;
	}
	
	public int getLength() { //public method: returns length of wire
		return length;
	}
	
	public void draw(Graphics g) {
		int x1 = x, y1 = y;
		if(dir == 0) {
			x1 += length;
		} else if(dir == 1) {
			y1 -= length;
		} else if(dir == 2) {
			x1 -= length;
		} else {
			y1 += length;
		}
		g.drawLine(x, y, x1, y1);
	}
	
	public boolean canConnect(Item i) {
		int x1 = x, y1 = y;
		if(dir == 0) {
			x1 += length;
		} else if(dir == 1) {
			y1 -= length;
		} else if(dir == 2) {
			x1 -= length;
		} else {
			y1 += length;
		}
		int ix1 = i.getX(), iy1 = i.getY(), idir = i.getDirection();
		if(idir == 0) {
			ix1 += length;
		} else if(idir == 1) {
			iy1 -= length;
		} else if(idir == 2) {
			ix1 -= length;
		} else {
			iy1 += length;
		}
		//System.out.println("I: (" + i.getX() + ", " + i.getY() + ")");
		//System.out.println("T: (" + x + ", " + y + ")");
		//System.out.println("A: (" + x1 + ", " + y1 + ")");
		return (i.getX() == x1 && i.getY() == y1) || (i.getX() == x && i.getY() == y) || (ix1 == x1 && iy1 == y1) || (ix1 == x && iy1 == y);
	}
	
	public void connect(Item i) {
		connections.add(i);
	}
	
	public void run() {
		ran = true;
		state = false;
		boolean found = false;
		for(int i = 0; i < connections.size(); i++) {
			if(connections.get(i).hasRan()) {
				state = connections.get(i).currentState();
				found = true;
				break;
			}
		}
		if(!found) {
			for(int i = 0; i < connections.size(); i++) {
				state |= (connections.get(i).isSource() ? connections.get(i).currentState() : false);
			}
		}
		System.out.println("Wire: " + state);
		for(int i = 0; i < connections.size(); i++) {
			if(!connections.get(i).hasRan() && !(connections.get(i) instanceof Switch)) {
				System.out.println("Calling input");
				connections.get(i).run();
				System.out.println("Called input");
			}
		}
		ran = false;
	}
}
