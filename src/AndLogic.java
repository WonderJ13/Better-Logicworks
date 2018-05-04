import java.util.Queue;
import java.awt.Graphics;
public class AndLogic extends Logic
{
	public AndLogic(int x, int y, int dir) {
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
		g.drawLine(x + 15, y - 20, x + 15, y + 20);
		g.drawArc(x, y - 20, 40, 40, 90, -180);
	}
	
	public void run() {
		System.out.println("\nandrun " + delaySignals.peek());
		out_switches[0].setState(delaySignals.poll());
		System.out.println("\nANDRUN " + out_switches[0].currentState());
		delaySignals.add(inputs[0].currentState() && inputs[1].currentState());
		out_switches[0].run();
	}
}
