import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
public class GUI extends JFrame implements MouseMotionListener, MouseListener
{
	private Board board = new Board();
	private JPanel contentPane;
	private int mouseX, mouseY;
	private enum LineState
	{
		NOT_CLICKED,
		HELD,
		CLICKED,
		PLACING_LINE
	}
	LineState lineState;
	
	public GUI(String name)
	{
		super(name);
		lineState = LineState.NOT_CLICKED;
		setSize(500, 500);
		addMouseMotionListener(this);
		addMouseListener(this);
		contentPane = new DrawPane();
		setContentPane(contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void mouseDragged(MouseEvent e)
	{
		//System.out.println(e);
		System.out.println(lineState);
	}
	
	public void mouseMoved(MouseEvent e)
	{
		mouseX = e.getX() - 8;
		mouseY = e.getY() - 30;
		//System.out.println(e);
		//System.out.println(mouseX + " " + mouseY);
		System.out.println(lineState);
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
	
	public void mousePressed(MouseEvent e)
	{
		int xPos = e.getX() - 8;
		int yPos = e.getY() - 30;
		System.out.println(xPos + " " + yPos);
		System.out.println(lineState);
		boolean equation = xPos >= 5 && xPos <= 40 && yPos >= 5 && yPos <= 25;
		if(equation && lineState == LineState.NOT_CLICKED) {
			lineState = LineState.HELD;
		}
		else if(lineState == LineState.CLICKED) {
			lineState = LineState.PLACING_LINE;
		}
		if(!equation && lineState == LineState.NOT_CLICKED) {
			System.out.println(board.amountOfLines());
		}
	}
	
	public void mouseReleased(MouseEvent e)
	{
		int xPos = e.getX() - 8;
		int yPos = e.getY() - 30;
		//System.out.println(xPos + " " + yPos);
		System.out.println(lineState);
		if(lineState == LineState.HELD) lineState = LineState.CLICKED;
		else if(lineState == LineState.PLACING_LINE) {
			board.addWire(mouseX, mouseY);
			lineState = LineState.NOT_CLICKED;
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
			char lines[] = {'L', 'i', 'n', 'e'};
			g.drawChars(lines, 0, 4, 10, 20);
			g.drawRect(5, 5, 35, 20);
			for(int i = 0; i < board.amountOfLines(); i++) {
				Wire wire = board.getWire(i);
				g.drawRect(wire.getX(), wire.getY(), wire.getLength(), 0);
			}
		}
	}
}
