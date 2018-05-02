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
	ObjectState lineState, switchState, readerState;
	
	public GUI(String name)
	{
		super(name);
		lineState = ObjectState.NOT_CLICKED;
		switchState = ObjectState.NOT_CLICKED;
		readerState = ObjectState.NOT_CLICKED;
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
	
	public void mousePressed(MouseEvent e)
	{
		int xPos = e.getX() - 8;
		int yPos = e.getY() - 30;
		System.out.println(xPos + " " + yPos);
		System.out.println(switchState);
		boolean wireEquation = xPos >= 5 && xPos <= 40 && yPos >= 5 && yPos <= 25;
		boolean switchEquation = xPos >= 45 && xPos <= 90 && yPos >= 5 && yPos <= 25;
		boolean readerEquation = xPos >= 95 && xPos <= 145 && yPos >= 5 && yPos <= 25;
		if(wireEquation && lineState == ObjectState.NOT_CLICKED) {
			if(switchState == ObjectState.NOT_CLICKED && readerState == ObjectState.NOT_CLICKED) {
				lineState = ObjectState.HELD;
			}
		}
		else if(lineState == ObjectState.CLICKED && snap(mouseY) > 30) {
			lineState = ObjectState.PLACING_LINE;
		}
		if(!wireEquation && lineState == ObjectState.NOT_CLICKED) {
			System.out.println(board.lineCount());
		}
		if(switchEquation && switchState == ObjectState.NOT_CLICKED) {
			if(lineState == ObjectState.NOT_CLICKED && readerState == ObjectState.NOT_CLICKED) {
				switchState = ObjectState.HELD;
			}
		}
		else if(switchState == ObjectState.CLICKED && snap(mouseY) > 30) {
			switchState = ObjectState.PLACING_LINE;
		}
		if(!switchEquation && switchState == ObjectState.NOT_CLICKED) {
			System.out.println(board.switchCount());
		}
		if(readerEquation && readerState == ObjectState.NOT_CLICKED) {
			if(lineState == ObjectState.NOT_CLICKED && switchState == ObjectState.NOT_CLICKED) {
				readerState = ObjectState.HELD;
			}
		}
		else if(readerState == ObjectState.CLICKED && snap(mouseY) > 30) {
			readerState = ObjectState.PLACING_LINE;
		}
		if(!readerEquation && readerState == ObjectState.NOT_CLICKED) {
			System.out.println(board.readerCount());
		}
		if(!wireEquation && !switchEquation && !readerEquation) {
			for(int i = 0; i < board.switchCount(); i++) {
				Switch s = board.getSwitch(i);
				if(new Switch(snap(mouseX), snap(mouseY), 0).equals(s)) {
					s.toggle();
					s.run();
					repaint();
					break;
				}
			}
		}
	}
	
	public void mouseReleased(MouseEvent e)
	{
		int xPos = e.getX() - 8;
		int yPos = e.getY() - 30;
		//System.out.println(xPos + " " + yPos);
		System.out.println(switchState);
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
			for(int i = 0; i < board.lineCount(); i++) { //Draw each wire
				board.getWire(i).draw(g);
			}
			for(int i = 0; i < board.switchCount(); i++) {
				board.getSwitch(i).draw(g);
			}
			for(int i = 0; i < board.readerCount(); i++) {
				board.getReader(i).draw(g);
			}
			if(lineState == ObjectState.CLICKED || lineState == ObjectState.PLACING_LINE) { //Draw line to show user where a wire would be placed
				new Wire(snap(mouseX), snap(mouseY), direction, 20).draw(g);
			}
			if(switchState == ObjectState.CLICKED || switchState == ObjectState.PLACING_LINE) {
				new Switch(snap(mouseX), snap(mouseY), direction).draw(g);
			}
			if(readerState == ObjectState.CLICKED || readerState == ObjectState.PLACING_LINE) {
				new Reader(snap(mouseX), snap(mouseY), direction).draw(g);
			}
		}
	}
	
	private int snap(int num) {
		return num - (num % 20);
	}
}
