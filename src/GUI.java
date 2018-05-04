import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
public class GUI extends JFrame implements MouseMotionListener, MouseListener, KeyListener
{
	private Board board = new Board();
	private JPanel contentPane;
	private int mouseX, mouseY;
	private int direction;
	private enum ObjectState
	{
		NOT_CLICKED,
		HELD,
		CLICKED,
		PLACING_LINE
	}
	ObjectState lineState, switchState, readerState, ANDState, ORState, NOTState;
	
	public GUI(String name)
	{
		super(name);
		lineState = ObjectState.NOT_CLICKED;
		switchState = ObjectState.NOT_CLICKED;
		readerState = ObjectState.NOT_CLICKED;
		ANDState = ObjectState.NOT_CLICKED;
		ORState = ObjectState.NOT_CLICKED;
		NOTState = ObjectState.NOT_CLICKED;
		setSize(500, 500);
		addMouseMotionListener(this);
		addMouseListener(this);
		addKeyListener(this);
		contentPane = new DrawPane();
		setContentPane(contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		direction = 0;
	}
	
	public void mouseDragged(MouseEvent e)
	{
		//System.out.println(e);
		//System.out.println(switchState);
	}
	
	public void mouseMoved(MouseEvent e)
	{
		mouseX = e.getX() - 8;
		mouseY = e.getY() - 30;
		//System.out.println(e);
		//System.out.println(mouseX + " " + mouseY);
		//System.out.println(switchState);
		repaint();
	}
	
	public void mouseClicked(MouseEvent e)
	{
		
	}
	
	public void mouseEntered(MouseEvent e)
	{
		
	}
	
	public void mouseExited(MouseEvent e)
	{
		
	}
	
	public void keyPressed(KeyEvent e) { //VK_KP_[DIR]
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			direction = 0;
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP) {
			direction = 1;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			direction = 2;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			direction = 3;
		}
		System.out.println(direction);
	}
	
	public void keyReleased(KeyEvent e) {
		
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
	
	private boolean nothingClicked() {
		return lineState == ObjectState.NOT_CLICKED && switchState == ObjectState.NOT_CLICKED && readerState == ObjectState.NOT_CLICKED && ANDState == ObjectState.NOT_CLICKED && ORState == ObjectState.NOT_CLICKED;
	}
	
	public void mousePressed(MouseEvent e)
	{
		int xPos = e.getX() - 8;
		int yPos = e.getY() - 30;
		//System.out.println(xPos + " " + yPos);
		//System.out.println(ANDState);
		boolean wireEquation = xPos >= 5 && xPos <= 40 && yPos >= 5 && yPos <= 25;
		boolean switchEquation = xPos >= 45 && xPos <= 90 && yPos >= 5 && yPos <= 25;
		boolean readerEquation = xPos >= 95 && xPos <= 145 && yPos >= 5 && yPos <= 25;
		boolean ANDEquation = xPos >= 150 && xPos <= 183 && yPos >= 5 && yPos <= 25;
		boolean OREquation = xPos >= 188 && xPos <= 213 && yPos >= 5 && yPos <= 25;
		boolean NOTEquation = xPos >= 218 && xPos <= 241 && yPos >= 5 && yPos <= 25;
		boolean runEquation = xPos >= (getWidth() - 56) && xPos <= (getWidth() - 23) && yPos >= 5 && yPos <= 25;
		boolean nothing_clicked = nothingClicked();
		if(wireEquation && nothing_clicked) {
			lineState = ObjectState.HELD;
		}
		else if(lineState == ObjectState.CLICKED && snap(mouseY) > 30) {
			lineState = ObjectState.PLACING_LINE;
		}
		
		if(switchEquation && nothing_clicked) {
			switchState = ObjectState.HELD;
		}
		else if(switchState == ObjectState.CLICKED && snap(mouseY) > 30) {
			switchState = ObjectState.PLACING_LINE;
		}
		
		if(readerEquation && nothing_clicked) {
			readerState = ObjectState.HELD;
		}
		else if(readerState == ObjectState.CLICKED && snap(mouseY) > 30) {
			readerState = ObjectState.PLACING_LINE;
		}
		
		if(ANDEquation && nothing_clicked) {
			ANDState = ObjectState.HELD;
		}
		else if(ANDState == ObjectState.CLICKED && snap(mouseY) > 30) {
			ANDState = ObjectState.PLACING_LINE;
		}
		
		if(OREquation && nothing_clicked) {
			ORState = ObjectState.HELD;
		}
		else if(ORState == ObjectState.CLICKED && snap(mouseY) > 30) {
			ORState = ObjectState.PLACING_LINE;
		}
		
		if(NOTEquation && nothing_clicked) {
			NOTState = ObjectState.HELD;
		}
		else if(NOTState == ObjectState.CLICKED && snap(mouseY) > 30) {
			NOTState = ObjectState.PLACING_LINE;
		}
		
		if(!wireEquation && !switchEquation && !readerEquation && !ANDEquation && !OREquation) {
			System.out.println("No Logic Count: " + board.noLogicCount());
			System.out.println("Logic Count: " + board.logicCount());
			for(int i = 0; i < board.noLogicCount(); i++) {
				if(!(board.getNoLogic(i) instanceof Switch)) continue;
				Switch s = (Switch) board.getNoLogic(i);
				if(new Switch(snap(mouseX), snap(mouseY), 0).equals(s)) {
					s.toggle();
					s.run();
					for(int j = 0; j < board.logicCount(); j++) {
						board.getLogic(j).run();
					}
					repaint();
					break;
				}
			}
		}
		
		if(runEquation) {
			System.out.println("running");
			for(int i = 0; i < board.logicCount(); i++) {
				board.getLogic(i).run();
			}
			repaint();
		}
	}
	
	public void mouseReleased(MouseEvent e)
	{
		int xPos = e.getX() - 8;
		int yPos = e.getY() - 30;
		//System.out.println(xPos + " " + yPos);
		//System.out.println(ANDState);
		if(lineState == ObjectState.HELD) lineState = ObjectState.CLICKED;
		else if(lineState == ObjectState.PLACING_LINE) {
			board.addWire(snap(mouseX), snap(mouseY), direction);
			lineState = ObjectState.NOT_CLICKED;
			repaint();
		}
		if(switchState == ObjectState.HELD) switchState = ObjectState.CLICKED;
		else if(switchState == ObjectState.PLACING_LINE) {
			board.addSwitch(snap(mouseX), snap(mouseY));
			switchState = ObjectState.NOT_CLICKED;
			repaint();
		}
		if(readerState == ObjectState.HELD) readerState = ObjectState.CLICKED;
		else if(readerState == ObjectState.PLACING_LINE) {
			board.addReader(snap(mouseX), snap(mouseY));
			readerState = ObjectState.NOT_CLICKED;
			repaint();
		}
		if(ANDState == ObjectState.HELD) ANDState = ObjectState.CLICKED;
		else if(ANDState == ObjectState.PLACING_LINE) {
			board.addAnd(snap(mouseX), snap(mouseY));
			ANDState = ObjectState.NOT_CLICKED;
			repaint();
		}
		if(ORState == ObjectState.HELD) ORState = ObjectState.CLICKED;
		else if(ORState == ObjectState.PLACING_LINE) {
			board.addOr(snap(mouseX), snap(mouseY));
			ORState = ObjectState.NOT_CLICKED;
			repaint();
		}
		if(NOTState == ObjectState.HELD) NOTState = ObjectState.CLICKED;
		else if(NOTState == ObjectState.PLACING_LINE) {
			board.addNot(snap(mouseX), snap(mouseY));
			NOTState = ObjectState.NOT_CLICKED;
			repaint();
		}
	}
	
	public static void main(String[]args)
	{
		GUI gui = new GUI("GUI");
	}
	
	private class DrawPane extends JPanel
	{
		public void paint(Graphics g)
		{
			g.drawRect(0, 30, getWidth(), 0); //Separate controls from board
			g.drawChars("Wire".toCharArray(), 0, 4, 10, 20); //Draw button for
			g.drawRect(5, 5, 35, 20); //Creation of wire
			g.drawChars("Switch".toCharArray(), 0, 6, 50, 20); //Draw button for
			g.drawRect(45, 5, 45, 20); //Creation of switch
			g.drawChars("Reader".toCharArray(), 0, 6, 100, 20); //Draw button for
			g.drawRect(95, 5, 50, 20); //Creation of reader
			g.drawChars("AND".toCharArray(), 0, 3, 155, 20); //Draw button for
			g.drawRect(150, 5, 33, 20); //Creation of AND gate
			g.drawChars("OR".toCharArray(), 0, 2, 192, 20);
			g.drawRect(188, 5, 25, 20);
			g.drawChars("NOT".toCharArray(), 0, 3, 223, 20);
			g.drawRect(218, 5, 33, 20);
			g.drawChars("Run".toCharArray(), 0, 3, getWidth() - 35, 20);
			g.drawRect(getWidth() - 40, 5, 33, 20);
			
			for(int i = 0; i < board.noLogicCount(); i++) {
				board.getNoLogic(i).draw(g);
			}
			for(int i = 0; i < board.logicCount(); i++) {
				board.getLogic(i).draw(g);
			}
			/*for(int i = 0; i < board.lineCount(); i++) { //Draw each wire
				board.getWire(i).draw(g);
			}
			for(int i = 0; i < board.switchCount(); i++) {
				board.getSwitch(i).draw(g);
			}
			for(int i = 0; i < board.readerCount(); i++) {
				board.getReader(i).draw(g);
			}*/
			if(lineState == ObjectState.CLICKED || lineState == ObjectState.PLACING_LINE) { //Draw line to show user where a wire would be placed
				new Wire(snap(mouseX), snap(mouseY), direction, 20).draw(g);
			}
			if(switchState == ObjectState.CLICKED || switchState == ObjectState.PLACING_LINE) {
				new Switch(snap(mouseX), snap(mouseY), direction).draw(g);
			}
			if(readerState == ObjectState.CLICKED || readerState == ObjectState.PLACING_LINE) {
				new Reader(snap(mouseX), snap(mouseY), direction).draw(g);
			}
			if(ANDState == ObjectState.CLICKED || ANDState == ObjectState.PLACING_LINE) {
				new AndLogic(snap(mouseX), snap(mouseY), direction).draw(g);
			}
			if(ORState == ObjectState.CLICKED || ORState == ObjectState.PLACING_LINE) {
				new OrLogic(snap(mouseX), snap(mouseY), direction).draw(g);
			}
			if(NOTState == ObjectState.CLICKED || NOTState == ObjectState.PLACING_LINE) {
				new NotLogic(snap(mouseX), snap(mouseY), direction).draw(g);
			}
		}
	}
	
	private int snap(int num) {
		return num - (num % 20);
	}
}
