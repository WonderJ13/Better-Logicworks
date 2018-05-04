import java.awt.Graphics;
public class NotLogic extends Logic
{
	public NotLogic(int x, int y, int dir) {
		super(x, y, dir, 1, 1);
		inputs[0] = new Wire(x, y, dir, 20);
		outputs[0] = new Wire(x + 40, y, dir, 20);
		out_switches[0] = new Switch(x + 40, y, dir);
		outputs[0].connect(out_switches[0]);
		out_switches[0].connect(outputs[0]);
	}
	
	public void draw(Graphics g) {
		for(int i = 0; i < inputs.length; i++) {
			inputs[i].draw(g);
		}
		for(int i = 0; i < outputs.length; i++) {
			outputs[i].draw(g);
		}
		//Todo, draw D
		//g.drawLine(x + 15, y - 20, x + 15, y + 20);
		g.drawLine(x + 20, y + 10, x + 20, y - 10);
		g.drawLine(x + 20, y + 10, x + 36, y);
		g.drawLine(x + 36, y, x + 20, y - 10);
		g.drawOval(x + 36, y - 2, 4, 4);
	}
	
	public void run() {
		System.out.println("\nnotrun " + delaySignals.peek());
		System.out.println(Math.random());
		out_switches[0].setState(delaySignals.poll());
		System.out.println("\nNOTRUN " + out_switches[0].currentState());
		delaySignals.add(!inputs[0].currentState());
		out_switches[0].run();
	}
}
