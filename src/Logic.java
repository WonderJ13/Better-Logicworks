import java.util.concurrent.ArrayBlockingQueue;
public class Logic extends Item
{
	protected int delay;
	protected Wire[]inputs, outputs;
	protected Switch[] out_switches;
	protected ArrayBlockingQueue<Boolean> delaySignals;
	
	public Logic(int x, int y, int dir, int num_of_inputs, int num_of_outputs) {
		super(x, y, dir);
		this.inputs = new Wire[num_of_inputs];
		this.outputs = new Wire[num_of_outputs];
		this.out_switches = new Switch[num_of_outputs];
		this.delaySignals = new ArrayBlockingQueue<Boolean>(10);
		for(int i = 0; i < 10; i++) {
			this.delaySignals.add(false);
		}
	}
	
	public int numOfInputs() {
		return inputs.length;
	}
	
	public int numOfOutputs() {
		return outputs.length;
	}
	
	public Wire getInput(int index) {
		return inputs[index];
	}
	
	public Wire getOutput(int index) {
		return outputs[index];
	}
}
