import java.awt.Graphics;
public class Wire extends Item
{
	private int length, dir; //Instance Variables
	private Item con0, con1; //More Instance Variables
	
	public Wire(int x, int y, int length, int dir) { //Constructor
		super(x, y);
		source = false;
		this.length = length;
		this.dir = dir;
	}
	
	public void connectWire0(Item wire) { //public method: available to use by other classes
		con0 = wire;
	}
	
	public void connectWire1(Item wire) { //public method: available to use by other classes
		con1 = wire;
	}
	
	public int getLength() { //public method: returns length of wire
		return length;
	}
	
	public int getDirection() { //public method: returns direction
		return dir;
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
		return (i.getX() == x1 && i.getY() == y1) || (i.getX() == x || i.getY() == y);
	}
	
	public void connect(Item i) {
		if(input == null) input = i;
		else if(output == null) output = i;
	}
	
	public void run() {
		ran = true;
		state = input.isSource() ? input.currentState() : false || output.isSource() ? output.currentState() : false;
		if(!input.hasRan()) {
			input.run();
		}
		if(!output.hasRan()) {
			output.run();
		}
		ran = false;
	}
}
