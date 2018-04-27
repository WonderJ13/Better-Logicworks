import java.awt.Graphics;
public class Item
{
	protected int x, y;
	protected boolean state, ran, source;
	protected Item input, output;
	
	public Item(int x, int y) {
		this.x = x;
		this.y = y;
		state = false;
		ran = false;
		input = null;
		output = null;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void toggle() {
		state = !state;
	}
	
	public boolean equals(Object o2) {
		Item i2 = (Item) o2;
		return this.getX() == i2.getX() && this.getY() == i2.getY();
	}
	
	public void draw(Graphics g) {
		//Do Nothing
	}
	
	public boolean canConnect(Item i) {
		return x == i.getX() && y == i.getY();
	}
	
	public void connect(Item i) {
		//Do Nothing
	}
	
	public void run() {
		//Do Nothing
	}
	
	protected boolean hasRan() {
		return ran;
	}
	
	protected boolean currentState() {
		return state;
	}
	
	protected boolean isSource() {
		return source;
	}
}
