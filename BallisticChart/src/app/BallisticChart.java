package app;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JFrame;

public class BallisticChart extends Canvas {

	private static final long serialVersionUID = 6075444260598962146L;
	private double VAL_X = 0, VAL_Y = 0, VAL_GRAVITY = 0, VAL_VELOCITY = 0,
			VAL_A = 0;
	private int WIDTH_CANVAS = 400, HEIGHT_CANVAS = 400;
	private int FRAME_OFFSET = 50;
	private int ZOOM = 12;
	private int POINT_SIZE = 4;
	private Color COLOR = Color.getHSBColor(50, 100, 100);
	private String XTAG = "X";
	private String YTAG = "Y";
	
	
	public void setValues(double x, double y, double gravi, double velo, double a, 
			int w, int h) {
        this.VAL_X = x; 
        this.VAL_Y = y;
        this.VAL_GRAVITY = gravi;
        this.VAL_VELOCITY = velo;
        this.VAL_A = a;
        this.WIDTH_CANVAS = w;
        this.HEIGHT_CANVAS = h;
        
        repaint();
    }
	
	public BallisticChart() {		
		setSize(500, 400);
		setValues(0, 0, 10.5f, 50f, 60, 500, 400);
	}
	
	public BallisticChart(int x, int y, double gravity, double velocity, double angle,
			int w, int h) {
		final int WIDTH = w, HEIGHT = h;
		setSize(WIDTH, HEIGHT);
		setValues((double)x, (double)y, gravity, velocity, angle, WIDTH, HEIGHT);
	}
	
	public static void main(String[] args)  {
		JFrame frame = new JFrame("My Drawing");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
		frame.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				//dispose();
				System.exit(0);
			}
					
		});
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		BallisticChart window = new BallisticChart();
		window.setVisible(true);
		
		frame.add(window);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void setFrameOffset (int offset) {
		this.FRAME_OFFSET = offset;
	}
	
	public void setXYZoom (int zoom) {
		this.ZOOM = zoom;
	}
	
	public void setPointSize (int size) {
		this.POINT_SIZE = size;
	}
	
	public void setColor (int h, int s, int b) {
		this.COLOR = Color.getHSBColor((float)h/360, (float)s/100, (float)b/100);
	}
	
	public void setXTag (String xtag) {
		this.XTAG = xtag;
	}
	
	public void setYTag (String ytag) {
		this.YTAG = ytag;
	}
	
	@Override
	public void paint(Graphics g) {
		//******Make chart
		double x = VAL_X;
		double gravi = VAL_GRAVITY;
		double velocity = VAL_VELOCITY;
		double y = VAL_Y;
		double a = Math.toRadians(VAL_A);
		double yHigh = 0;
		
		ArrayList<Double> chart = new ArrayList<Double>();
		
		do {
			y = (x * Math.tan(a)) - (Math.pow(x, 2) * gravi) / (2 * Math.pow(velocity, 2) * Math.cos(a) * Math.cos(a) );
			chart.add(y);
			if(yHigh < y)
				yHigh = y;
			x++;
		} while(y>=0 || x == 1000);
		//******Make chart
		
		
		//******Draw scatter
		
			//Data input
		int scatterW = chart.size(), scatterH = (int) yHigh;
		
			//Layout config input
		int frameOffset = FRAME_OFFSET; // left-top
		int zoom = ZOOM;
		int pointSize = POINT_SIZE;
		int valStringAppend = 25;
				
		int winX = WIDTH_CANVAS - (frameOffset * 2);
		int winY = HEIGHT_CANVAS - (frameOffset * 2);
		
		
			//Scatter lines
		int aX = frameOffset;
		int aY = frameOffset;
		
		int bX = frameOffset;
		int bY = frameOffset + winY;//(int) scatterH;
		
		int cX = frameOffset + winX;//(int) scatterW;
		int cY = bY;
		
		g.drawLine(bX, bY, aX, aY); //Y top - bottom		
		g.drawLine(bX, bY, cX, cY); //X left - right
		
		
		g.drawString(XTAG, ((winX)/2) + frameOffset, bY + (valStringAppend+20));
		
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform defaultAt = g2d.getTransform();
		AffineTransform at = new AffineTransform();
		at.rotate(-Math.PI / 2);
		g2d.setTransform(at);
		
		g2d.drawString(YTAG, -(((winY)/2) + (frameOffset*2)),  aX - (valStringAppend+12));
		
		g2d.setTransform(defaultAt);
		
		
			//Values
			// Y
		for(int i = 0; i <= zoom; i++) {
			g.drawString(Integer.toString((int)scatterH - (int) (i*(scatterH/zoom))), aX-valStringAppend, aY+(i*(winY/zoom)));
		}
		
			// X
		for(int i = 0; i <= zoom; i++) {
			g.drawString(Integer.toString((int)scatterW - (int) (i*(scatterW/zoom))), cX-(i*(winX/zoom)), cY+valStringAppend);
		}
		//******Draw scatter
		
		
		//******Draw chart
		int temp = 0;
		int temp2 = 0;
		double zoomXRatio = winX / (float) scatterW;
		
		for (int xChart = 0; xChart < chart.size(); xChart++) {
			g.setColor(COLOR);
			temp = (int) Math.round(winY * (chart.get(xChart)/scatterH ));
			temp2 = (int) Math.round(xChart * zoomXRatio);
			g.fillOval(temp2 + frameOffset, winY + frameOffset - (temp), pointSize, pointSize);//x,y
		}
		//******Draw chart
		
	}
}
