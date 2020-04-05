//18001084 Yap Kian Shui COE
//18001106 Yap Kian San COE
//OOP project tower defense game 2nd draft
import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
	public static String title = "Tower defense";
	public static Dimension size = new Dimension(700,550);
	//public static String musicPath = "/core/underdog.mp3";
	public Frame() {
		setTitle(title);
		setSize(size);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		init();
	}
	
	public void init() {
		setLayout(new GridLayout(1, 1, 0, 0));
		
		Screen screen = new Screen(this);
		add(screen);
		
		setVisible(true);
		
	}
	

	public static void main(String args[]) {
		Frame frame = new Frame();
	}
	
}
