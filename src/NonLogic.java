import java.awt.Graphics;
import java.util.ArrayList;
public class NonLogic extends Item
{
	protected boolean state;
	protected ArrayList<NonLogic> connections;
	
	public NonLogic(int x, int y, int dir) {
		super(x, y, dir);
		state = false;
		connections = new ArrayList<NonLogic>();
	}
	
	public void connect(NonLogic i) {
		connections.add(i);
	}
	
	public void connect(Logic l) {
		System.out.println("connecting logic?");
		for(int i = 0; i < l.numOfInputs(); i++) {
			if(l.getInput(i).canConnect(this) || canConnect(l.getInput(i))) {
				System.out.println("Connected Logic Wire");
				connect(l.getInput(i));
				l.getInput(i).connect(this);
			}
		}
		for(int i = 0; i < l.numOfOutputs(); i++) {
			if(l.getOutput(i).canConnect(this) || canConnect(l.getOutput(i))) {
				System.out.println("Connected Logic Wire");
				connect(l.getOutput(i));
				l.getOutput(i).connect(this);
			}
		}
	}
	
	public void setState(boolean newState) {
		state = newState;
	}
	
	protected boolean currentState() {
		return state;
	}
}

