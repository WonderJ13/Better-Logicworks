import java.awt.Graphics;
public class OrLogic extends Logic
{
	public OrLogic(int x, int y, int dir) {
		super(x, y, dir, 2, 1);
		inputs[0] = new Wire(x, y - 20, dir, 20);
		inputs[1] = new Wire(x, y + 20, dir, 20);
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
		g.drawArc(x + 5, y - 25, 20, 50, 90, -180);
		g.drawArc(x - 6, y - 25, 45, 60, 90, -82);
		g.drawArc(x - 6, y - 35, 45, 60, -8, -82);
	}
	
	public void run() {
		System.out.println("\norrun " + delaySignals.peek());
		System.out.println(Math.random());
		out_switches[0].setState(delaySignals.poll());
		System.out.println("\nORRUN " + out_switches[0].currentState());
		delaySignals.add(inputs[0].currentState() || inputs[1].currentState());
		out_switches[0].run();
	}
}
