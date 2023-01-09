import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class screen extends JPanel {
	private character a = new character();
	private rectangle levelTest = new rectangle(80, 300, 300, 100);
	public screen () {
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
			//Passes the KeyEvent e to the ball instance
			a.keyReleased(e); 
			}
			@Override
			public void keyPressed(KeyEvent e) {
			//Passes the KeyEvent e to the ball instance
			a.keyPressed(e);
			}
			});
			setFocusable(true);
	}
	
	private void move() {
		a.move(levelTest);
	}
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		a.paint(g2d); 
		levelTest.paint(g2d);
	}
	
	public static void main(String [] args) throws InterruptedException{
		JFrame f = new JFrame("Walking");
		screen s  = new screen(); 
		f.add(s); 
		
		f.setSize(1020,640);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		while (true) {
			s.move(); // Updates the coordinates
			s.repaint(); // Calls the paint method
			Thread.sleep(10); // Pauses for a moment
			}
		
	}

}
