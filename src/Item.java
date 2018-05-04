import java.awt.Graphics;
import java.util.ArrayList;
public class Item
{
	protected int x, y, dir;
	protected boolean ran, source;
	
	public Item(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		ran = false;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getDirection() {
		return dir;
	}
	
	public boolean equals(Object o2) {
		Item i2 = (Item) o2;
		if(!this.getClass().equals(i2.getClass())) return false;
		return this.getX() == i2.getX() && this.getY() == i2.getY() && this.getDirection() == i2.getDirection();
	}
	
	public void draw(Graphics g) {
		//Do Nothing
	}
	
	public boolean canConnect(Item i) {
		return x == i.getX() && y == i.getY();
	}
	
	public void connect(NonLogic i) {
		//Do Nothing
	}
	
	public void run() {
		//Do Nothing
	}
	
	protected boolean hasRan() {
		return ran;
	}
	
	protected boolean isSource() {
		return source;
	}
}
