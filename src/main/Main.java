package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;

public class Main extends JFrame {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Main m = new Main();
				m.setVisible(true);
				m.addKeyListener(Keyboard.instance());
				Loop.play();
				Loop.pause();
				
			}
		});
	}
	
	public static Canvas canvas;
	private JPanel cp;
	
	public Main() {
		setResizable(false);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		cp = new JPanel();
		setContentPane(cp);
		cp.setLayout(null);
		
		JButton Play = new JButton("  Play");
		Play.setHorizontalAlignment(SwingConstants.LEFT);
		Play.setBorder(new MatteBorder(2, 2, 2, 16, Color.GREEN));
		Play.setBounds(695, 11, 89, 23);
		Play.setFocusable(false);
		cp.add(Play);
		
		JButton Pause = new JButton("  Pause");
		Pause.setHorizontalAlignment(SwingConstants.LEFT);
		Pause.setBorder(new MatteBorder(2, 2, 2, 16, Color.BLACK));
		Pause.setBounds(695, 45, 89, 23);
		Pause.setFocusable(false);
		cp.add(Pause);
		
		JButton Stop = new JButton("  Stop");
		Stop.setHorizontalAlignment(SwingConstants.LEFT);
		Stop.setBorder(new MatteBorder(2, 2, 2, 16, Color.BLACK));
		Stop.setBounds(695, 79, 89, 23);
		Stop.setFocusable(false);
		cp.add(Stop);
		
		
		canvas = new Canvas();
		canvas.setBounds(10, 10, 676, 551);
		canvas.setFocusable(false);
		cp.add(canvas);
		
		
		Play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Loop.isPaused()) {Loop.pause(); return;}
				Loop.play();
			}
		});
		Pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Loop.pause();
			}
		});
		Stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Loop.stop(true);
			}
		});
		cp.setFocusable(false);
	}
}
