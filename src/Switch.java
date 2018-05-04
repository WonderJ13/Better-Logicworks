import java.awt.Graphics;
public class Switch extends NonLogic
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
	
	public void toggle() {
		state = !state;
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
