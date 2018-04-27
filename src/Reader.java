import java.awt.Graphics;
public class Reader extends Item
{
	public Reader(int x, int y) {
		super(x, y);
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
		input = i;
	}
	
	public void run() {
		ran = true;
		state = input.currentState();
		System.out.println("Reader: " + state);
		ran = false;
	}
}
