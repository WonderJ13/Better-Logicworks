import java.awt.Graphics;
public class Reader extends Item
{
	public Reader(int x, int y, int dir) {
		super(x, y, dir);
		source = false;
	}
	
	public void draw(Graphics g) {
		g.drawLine(x, y, x, y - 10);
		if(state == false) {
			g.drawRect(x - 5, y - 20, 10, 10);
		} else {
			g.fillRect(x - 5, y - 20, 10, 10);
		}
	}
	
	public void connect(Item i) {
		connections.add(i);
	}
	
	public void run() {
		ran = true;
		state = false;
		for(int i = 0; i < connections.size(); i++) {
			state |= connections.get(i).currentState();
		}
		System.out.println("Reader: " + state);
		ran = false;
	}
}
