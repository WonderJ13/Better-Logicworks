import java.awt.Graphics;
public class Switch extends Item
{
	public Switch(int x, int y, int dir) {
		super(x, y, dir);
		source = true;
	}
	
	public void draw(Graphics g) {
		g.drawLine(x - 10, y, x, y);
		if(state) {
			g.drawLine(x - 10, y, x - 20, y - 10);
		} else {
			g.drawLine(x - 10, y, x - 20, y + 10);
		}
	}
	
	public void connect(Item i) {
		connections.add(i);
	}
	
	public void run() {
		ran = true;
		System.out.println("In Switch run");
		for(int i = 0; i < connections.size(); i++) {
			connections.get(i).run();
		}
		System.out.println("Leaving Switch run");
		ran = false;
	}
}
