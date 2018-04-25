public class Wire
{
	private int x, y, length, dir; //Instance Variables
	private Wire con0, con1; //More Instance Variables
	
	public Wire(int x, int y, int length, int dir) { //Constructor
		this.x = x;
		this.y = y;
		this.length = length;
		this.dir = dir;
	}
	
	public void connectWire0(Wire wire) { //public method: available to use by other classes
		con0 = wire;
	}
	
	public void connectWire1(Wire wire) { //public method: available to use by other classes
		con1 = wire;
	}
	
	public int getX() { //public method: returns x pos
		return x;
	}
	
	public int getY() { //public method: returns y pos
		return y;
	}
	
	public int getLength() { //public method: returns length of wire
		return length;
	}
	
	public int getDirection() { //public method: returns direction
		return dir;
	}
}
