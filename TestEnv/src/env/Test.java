package env;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import app.BallisticChart;


public class Test extends JFrame {

	private static final long serialVersionUID = 3301132081005308954L;

	JTextField xV, yV, gravV, veloV, angV, wV, hV;
	
	public Test() {
		final int WIDTH = 400, HEIGHT = 400;	
		Dimension frameSize = new Dimension(WIDTH,HEIGHT);
		setSize(frameSize);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
			
		});
		
		
		GridLayout experimentLayout = new GridLayout(0,2);
		setLayout(experimentLayout);
		
		
		JLabel xL = new JLabel("x");
	    add(xL);
	    xV = new JTextField("0");
	    add(xV);
	    
	    JLabel yL = new JLabel("y");
	    add(yL);
	    yV = new JTextField("0");
	    add(yV);
	    
	    JLabel gravL = new JLabel("Gravity");
	    add(gravL);
	    gravV = new JTextField("9.5");
	    add(gravV);
	    
	    JLabel veloL = new JLabel("Velocity");
	    add(veloL);
	    veloV = new JTextField("100");
	    add(veloV);
	    
	    JLabel angL = new JLabel("Angle");
	    add(angL);
	    angV = new JTextField("60");
	    add(angV);
	    
	    JLabel wL = new JLabel("Width");
	    add(wL);
	    wV = new JTextField("600");
	    add(wV);
	    
	    JLabel hL = new JLabel("Height");
	    add(hL);
	    hV = new JTextField("400");
	    add(hV);
	    
	    JButton drawBtn = new JButton("Rysuj wykres");
	    JButton test1Btn = new JButton("Test 1 - Kolor");
	    JButton test2Btn = new JButton("Test 2 - Rysowanie");
	    JButton test3Btn = new JButton("Test 3 - Precyzja");
	    
	    
		//---RYSUJ_WYKRES
		drawBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Curve");
				frame.setResizable(false);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				
				BallisticChart chart = new BallisticChart(Integer.parseInt(xV.getText()),
						Integer.parseInt(yV.getText()),
						Double.parseDouble(gravV.getText()), 
						Double.parseDouble(veloV.getText()),
						Double.parseDouble(angV.getText()),
						Integer.parseInt(wV.getText()), 
						Integer.parseInt(hV.getText()));
				
				//Chart_config
				chart.setPointSize(4);
				chart.setFrameOffset(50);
				chart.setXYZoom(12);
				chart.setXTag("Dystans");
				chart.setYTag("Wysokość");
				chart.setColor(160, 100, 100);
				chart.setVisible(true);
				
				frame.add(chart);
				frame.pack();
				frame.setVisible(true);
				
				add(frame);
			}
		} );
		
		
		//---RANDOM_WYKRES
		test2Btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Curve");
				frame.setResizable(false);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				
				Random rand = new Random();
				
				BallisticChart chart = new BallisticChart(rand.nextInt(900),// X
						rand.nextInt(900),									// Y
						(rand.nextFloat()*4)+8,						 		// Gravity (8-12)
						(rand.nextFloat()*800)+30,							// Velocity (30-830)
						(rand.nextFloat()*60)+30,							// Angle	(30-90)
						rand.nextInt(100)+400, 								// WIDTH
						rand.nextInt(100)+350);								// HEIGHT
				
				//Chart_config
				chart.setPointSize(4);
				chart.setFrameOffset(50);
				chart.setXYZoom(12);
				chart.setXTag("Dystans");
				chart.setYTag("Wysokość");
				chart.setColor(160, 100, 100);
				chart.setVisible(true);
				
				frame.add(chart);
				frame.pack();
				frame.setVisible(true);
				
				add(frame);
			}
		} );
		
		
		//---ZMIANA_KOLORU
		test1Btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Curve");
				frame.setResizable(false);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				
				Random rand = new Random();
				int int_random_H = rand.nextInt(360); 
				int int_random_S = rand.nextInt(100); 
				int int_random_B = rand.nextInt(100); 
				
				BallisticChart chart = new BallisticChart(Integer.parseInt(xV.getText()),
						Integer.parseInt(yV.getText()),
						Double.parseDouble(gravV.getText()), 
						Double.parseDouble(veloV.getText()),
						Double.parseDouble(angV.getText()),
						Integer.parseInt(wV.getText()), 
						Integer.parseInt(hV.getText()));
				
				//Chart_config
				chart.setPointSize(4);
				chart.setFrameOffset(50);
				chart.setXYZoom(12);
				chart.setXTag("Dystans");
				chart.setYTag("Wysokość");
				chart.setColor(int_random_H, int_random_S, int_random_B);
				chart.setVisible(true);
				
				frame.add(chart);
				frame.pack();
				frame.setVisible(true);
				
				add(frame);
			}
		} );
		
		
		//---ZMIANA_PRECYZJI
		test3Btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Curve");
				frame.setResizable(false);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				
				Random rand = new Random();
				int u = 48;
				int int_random_zoom = rand.nextInt(u) + 2; 
				
				BallisticChart chart = new BallisticChart(Integer.parseInt(xV.getText()),
						Integer.parseInt(yV.getText()),
						Double.parseDouble(gravV.getText()), 
						Double.parseDouble(veloV.getText()),
						Double.parseDouble(angV.getText()),
						Integer.parseInt(wV.getText()), 
						Integer.parseInt(hV.getText()));
				
				//Chart_config
				chart.setPointSize(4);
				chart.setFrameOffset(50);
				chart.setXYZoom(int_random_zoom);
				chart.setXTag("Dystans");
				chart.setYTag("Wysokość");
				chart.setColor(160, 100, 100);
				chart.setVisible(true);
				
				frame.add(chart);
				frame.pack();
				frame.setVisible(true);
				
				add(frame);
			}
		} );
		
		add(drawBtn);
		add(test1Btn);
		add(test2Btn);
		add(test3Btn);
		
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		Test window = new Test();
		window.setVisible(true);
	}

}
