import java.util.ArrayList; //Just an array that'll increase in size automatically
public class Board
{
	private ArrayList<Wire> wires; //Instance Variable: list of wires
	/* <Wire> means the arraylist will accept objects of type Wire 
	 * This can easily be changed to <Integer> or <String> to fit
	 * any object you want */
	 private ArrayList<Switch> switches;
	 private ArrayList<Reader> readers;
	
	public Board() //Constructor
	{
		wires = new ArrayList<Wire>();
		switches = new ArrayList<Switch>();
		readers = new ArrayList<Reader>();
	}
	
	public void addWire(int x, int y) { //Public method: creates a new wire and puts it in the list
		if(wires.indexOf(new Wire(x, y, 0, 0)) != -1) return;
		Wire newWire = new Wire(x, y, 20, 0);
		for(int i = 0; i < lineCount(); i++) {
			System.out.println(newWire.canConnect(wires.get(i)));
			System.out.println(wires.get(i).canConnect(newWire));
			if(newWire.canConnect(wires.get(i)) || wires.get(i).canConnect(newWire)) {
				System.out.println("connected wire");
				newWire.connect(wires.get(i));
				wires.get(i).connect(newWire);
			}
		}
		for(int i = 0; i < switchCount(); i++) {
			if(newWire.canConnect(switches.get(i)) || switches.get(i).canConnect(newWire)) {
				System.out.println("connected switch");
				newWire.connect(switches.get(i));
				switches.get(i).connect(newWire);
			}
		}
		for(int i = 0; i < readerCount(); i++) {
			if(newWire.canConnect(readers.get(i)) || readers.get(i).canConnect(newWire)) {
				System.out.println("connected reader");
				newWire.connect(readers.get(i));
				readers.get(i).connect(newWire);
			}
		}
		wires.add(newWire);
	}
	
	public void addSwitch(int x, int y) {
		if(switches.indexOf(new Switch(x, y)) != -1) return;
		Switch newSwitch = new Switch(x, y);
		for(int i = 0; i < lineCount(); i++) {
			if(newSwitch.canConnect(wires.get(i)) || wires.get(i).canConnect(newSwitch)) {
				System.out.println("connected wire");
				newSwitch.connect(wires.get(i));
				wires.get(i).connect(newSwitch);
			}
		}
		for(int i = 0; i < switchCount(); i++) {
			if(newSwitch.canConnect(switches.get(i)) || switches.get(i).canConnect(newSwitch)) {
				System.out.println("connected switch");
				newSwitch.connect(switches.get(i));
				switches.get(i).connect(newSwitch);
			}
		}
		for(int i = 0; i < readerCount(); i++) {
			if(newSwitch.canConnect(readers.get(i)) || readers.get(i).canConnect(newSwitch)) {
				System.out.println("connected reader");
				newSwitch.connect(readers.get(i));
				readers.get(i).connect(newSwitch);
			}
		}
		switches.add(newSwitch);
	}
	
	public void addReader(int x, int y) {
		if(readers.indexOf(new Reader(x, y)) != -1) return;
		Reader newReader = new Reader(x, y);
		for(int i = 0; i < lineCount(); i++) {
			if(newReader.canConnect(wires.get(i)) || wires.get(i).canConnect(newReader)) {
				System.out.println("connected wire");
				newReader.connect(wires.get(i));
				wires.get(i).connect(newReader);
			}
		}
		for(int i = 0; i < switchCount(); i++) {
			if(newReader.canConnect(switches.get(i)) || switches.get(i).canConnect(newReader)) {
				System.out.println("connected switch");
				newReader.connect(switches.get(i));
				switches.get(i).connect(newReader);
			}
		}
		for(int i = 0; i < readerCount(); i++) {
			if(newReader.canConnect(readers.get(i)) || readers.get(i).canConnect(newReader)) {
				System.out.println("connected reader");
				newReader.connect(readers.get(i));
				readers.get(i).connect(newReader);
			}
		}
		readers.add(newReader);
	}
	
	public int lineCount() { //Public method: returns the size of the arraylist (how many wires are there?)
		return wires.size();
	}
	
	public int switchCount() {
		return switches.size();
	}
	
	public int readerCount() {
		return readers.size();
	}
	
	public Wire getWire(int index) { //Returns a specific wire specified by the index
		return wires.get(index);
	}
	
	public Switch getSwitch(int index) { //Returns a specific switch specified by the index
		return switches.get(index);
	}
	
	public Reader getReader(int index) {
		return readers.get(index);
	}
}
