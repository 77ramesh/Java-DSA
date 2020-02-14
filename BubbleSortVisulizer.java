package sorting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class BubbleSortVisulizer implements ActionListener{

	static int[] arr = {
			2,3,4,1,8,3,9,23,45,17,
			34, 56, 22, 10, 11
	};
	
	static JMenu runSortOP;
	static JMenuBar menuBar;
	static JMenuItem go;
	
	static Sleep sleep = new Sleep(arr);
	static Thread t = new Thread(sleep);
	
	public static void main(String[] args) {
		
		//init widgets
		init();
		
		JFrame frame = new JFrame("Visualize sort");
		frame.setSize(500, 500);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLocation(0, 50);
		frame.getContentPane().setSize(500, 450);
		frame.getContentPane().add(sleep);
		
		frame.setJMenuBar(menuBar);
	}
	
	private static void init() {
		runSortOP = new JMenu("Run Sort");
		menuBar = new JMenuBar();
		go = new JMenuItem("GO");
		
		menuBar.add(runSortOP);
		runSortOP.add(go);

		go.addActionListener(new BubbleSortVisulizer());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == go) {
			t.start();
		}
		
	}

}

class Sleep extends JComponent implements Runnable {
	
	int[] arr;
	Color defColor = new Color(0, 255, 0);
	
	int pos = 0;
	
	public Sleep() {}
	
	public Sleep(int[] arr) {
		this.arr = arr;
	}
	
	public void run() {
		try {
			runSort();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void runSort() throws InterruptedException {
		
		for(int i=0;i<arr.length;i++) {
			for(int j=i;j<arr.length;j++) {
				
				//to track current index position
				pos = i;
				
				if(arr[i] > arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
					Thread.sleep(500);
					repaint();
				}		
			}
		}
		
		for(int i: arr)
			System.out.println(i);
	}
	
	@Override
	public void paint(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setPaint(Color.BLACK);
		
		int dist = 25;
		
		for(int i=0;i<arr.length;i++) {
			
			if(i == pos)
				g2d.setPaint(defColor);
			
			g2d.fill(new Rectangle2D.Double(dist, 0, 20, arr[i]*4));
			dist+=25;
			
			g2d.setPaint(Color.BLACK);
		}
	}
	
	public Color getColor() {
		return defColor;
	}
	
	public int[] getSortedArr() {
		return arr;
	}
}
