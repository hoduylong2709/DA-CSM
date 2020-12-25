package Client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JPanel;

public class SendEvent implements KeyListener, MouseMotionListener, MouseListener{
	private Socket cSocket = null;
	private JPanel cPanel = null;
	private PrintWriter writer = null;
	String width = "", height = "";
	double w;
	double h;

	SendEvent(Socket s, JPanel p, String width, String height){
		cSocket = s;
		cPanel = p;
		this.width = width;
		this.height = height;
		w = Double.valueOf(width.trim()).doubleValue();
		h = Double.valueOf(width.trim()).doubleValue();

		cPanel.addKeyListener(this);
		cPanel.addMouseListener(this);
		cPanel.addMouseMotionListener(this);

		try{
			writer = new PrintWriter(cSocket.getOutputStream());
			} catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e){
	}

	@Override
	public void mouseMoved(MouseEvent e){
		double xScale = w/cPanel.getWidth();
		double yScale = h/cPanel.getHeight();
		writer.println(-5);
		writer.println((int)(e.getX()*xScale));
		writer.println((int)(e.getY()*yScale*0.7114*0.8));
		writer.flush();
	}

	@Override
	public void mouseClicked(MouseEvent e){
	}

	@Override
	public void mousePressed(MouseEvent e){
		writer.println(-1);
		int button = e.getButton();
		int xButton = 16;
		if(button==3){
			xButton = 4;
		}
		writer.println(xButton);
		writer.flush();
	}

	@Override
	public void mouseReleased(MouseEvent e){
		writer.println(-2);
		int button = e.getButton();
		int xButton = 16;
		if(button==3){
			xButton = 4;
		}
		writer.println(xButton);
		writer.flush();
	}

	@Override
	public void mouseEntered(MouseEvent e){
	}

	@Override
	public void mouseExited(MouseEvent e){
	}

	@Override
	public void keyTyped(KeyEvent e){
	}

	@Override
	public void keyPressed(KeyEvent e){
		writer.println(-3);
		writer.println(e.getKeyCode());
		writer.flush();
	}

	@Override
	public void keyReleased(KeyEvent e){
		writer.println(-4);
		writer.println(e.getKeyCode());
		writer.flush();
	}
}
