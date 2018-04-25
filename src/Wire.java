public class Wire
{
	private int x, y, length, dir;
	private Wire con0, con1;
	
	public Wire(int x, int y, int length, int dir) {
		this.x = x;
		this.y = y;
		this.length = length;
		this.dir = dir;
	}
	
	public void connectWire0(Wire wire) {
		con0 = wire;
	}
	
	public void connectWire1(Wire wire) {
		con1 = wire;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getLength() {
		return length;
	}
	
	public int getDirection() {
		return dir;
	}
}
