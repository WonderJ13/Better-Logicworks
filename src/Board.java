import java.util.ArrayList; //Just an array that'll increase in size automatically
public class Board
{
	private ArrayList<NonLogic> nonLogicItems;
	private ArrayList<Logic> logicItems;
	
	public Board() //Constructor
	{
		nonLogicItems = new ArrayList<NonLogic>();
		logicItems = new ArrayList<Logic>();
	}
	
	private void addNoLogic(NonLogic nl) {
		for(int i = 0; i < nonLogicItems.size(); i++) {
			if(nl.canConnect(nonLogicItems.get(i)) || nonLogicItems.get(i).canConnect(nl)) {
				System.out.println("Connected " + nonLogicItems.get(i).getClass().toString());
				nl.connect(nonLogicItems.get(i));
				nonLogicItems.get(i).connect(nl);
			}
		}
		for(int i = 0; i < logicItems.size(); i++) {
			System.out.println("hi");
			Logic l = logicItems.get(i);
			for(int j = 0; j < l.numOfInputs(); j++) {
				if(l.getInput(j).canConnect(nl) || nl.canConnect(l.getInput(j))) {
					System.out.println("connecting input wire");
					l.getInput(j).connect(nl);
					nl.connect(l.getInput(j));
				}
			}
			for(int j = 0; j < l.numOfOutputs(); j++) {
				if(l.getOutput(j).canConnect(nl) || nl.canConnect(l.getOutput(j))) {
					System.out.println("connecting output wire");
					l.getOutput(j).connect(nl);
					nl.connect(l.getOutput(j));
				}
			}
		}
		System.out.println("end of addnologic");
		nonLogicItems.add(nl);
	}
	
	private void addLogic(Logic l) {
		for(int i = 0; i < nonLogicItems.size(); i++) {
			for(int j = 0; j < l.numOfInputs(); j++) {
				if(l.getInput(j).canConnect(nonLogicItems.get(i)) || nonLogicItems.get(i).canConnect(l.getInput(j))) {
					System.out.println("Connected input " + nonLogicItems.get(i).getClass().toString());
					l.getInput(j).connect(nonLogicItems.get(i));
					nonLogicItems.get(i).connect(l.getInput(j));
				}
			}
			for(int j = 0; j < l.numOfOutputs(); j++) {
				if(l.getOutput(j).canConnect(nonLogicItems.get(i)) || nonLogicItems.get(i).canConnect(l.getOutput(j))) {
					System.out.println("Connected output " + nonLogicItems.get(i).getClass().toString());
					l.getOutput(j).connect(nonLogicItems.get(i));
					nonLogicItems.get(i).connect(l.getOutput(j));
				}
			}
		}
		logicItems.add(l);
	}
	
	public void addWire(int x, int y, int dir) { //Public method: creates a new wire and puts it in the list
		if(nonLogicItems.indexOf(new Wire(x, y, dir, 20)) != -1) return;
		Wire newWire = new Wire(x, y, dir, 20);
		addNoLogic(newWire);
	}
	
	public void addSwitch(int x, int y) {
		System.out.println("indexof: " + nonLogicItems.indexOf(new Switch(x, y, 0)));
		if(nonLogicItems.indexOf(new Switch(x, y, 0)) != -1) return;
		System.out.println("switch going into addnologic");
		Switch newSwitch = new Switch(x, y, 0);
		addNoLogic(newSwitch);
	}
	
	public void addReader(int x, int y) {
		if(nonLogicItems.indexOf(new Reader(x, y, 0)) != -1) return;
		Reader newReader = new Reader(x, y, 0);
		addNoLogic(newReader);
	}
	
	public void addAnd(int x, int y) {
		if(logicItems.indexOf(new AndLogic(x, y, 0)) != -1) return;
		AndLogic and = new AndLogic(x, y, 0);
		addLogic(and);
	}
	
	public void addOr(int x, int y) {
		if(logicItems.indexOf(new OrLogic(x, y, 0)) != -1) return;
		OrLogic or = new OrLogic(x, y, 0);
		addLogic(or);
	}
	
	public void addNot(int x, int y) {
		if(logicItems.indexOf(new NotLogic(x, y, 0)) != -1) return;
		NotLogic not = new NotLogic(x, y, 0);
		addLogic(not);
	}
	
	public int noLogicCount() {
		return nonLogicItems.size();
	}
	
	public NonLogic getNoLogic(int index) {
		return nonLogicItems.get(index);
	}
	
	public int logicCount() {
		return logicItems.size();
	}
	
	public Logic getLogic(int index) {
		return logicItems.get(index);
	}
}
