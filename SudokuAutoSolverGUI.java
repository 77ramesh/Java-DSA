package sudoku;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SudokuAutoSolverGUI implements KeyListener{

	private Graph graph;
	private Thread play;
	
	public SudokuAutoSolverGUI(int[][] board) {
		
		graph = new Graph(board);
		play = new Thread(graph);
		
		JFrame frame = new JFrame("Sudoku Auto Solver");
		frame.setSize(600, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().add(graph);
		frame.addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
			play.start();
			
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}

class Graph extends JPanel implements Runnable{

	private static final long serialVersionUID = 1L;
	
	private int[][] board;
	
	public Graph(int[][] board){
		this.board = board;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawString("Hit Space to start!", 280, 20);
		
		for(int i=120;i<480;i+=40) {
			for(int j=120;j<480;j+=40) {
				
				if(i == 120 && j == 120)
					g2d.setPaint(Color.GREEN);
				else
					g2d.setPaint(Color.BLACK);
				
				g2d.draw(new Rectangle2D.Double(i, j, 40, 40));
			}
		}
		
		g2d.setPaint(Color.RED);
		
		paintNumbers(g2d);
		
		g2d.dispose();
	}
	
	private void paintNumbers(Graphics2D g2d) {
		
		g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
		
		int x = 0;
		int y = 0;
		boolean done = false;
		
		for(int i=140;i<500;i+=40) {
			
			if(done)
				break;
			
			for(int j=140;j<500;j+=40) {
				
				if(j == 9) {
					done = true;
					break;
				}
				
				g2d.drawString(String.valueOf(board[y][x]), i, j);
				y++;
			}
			
			y = 0;
			x++;
			
		}
	}
	
	void solve() throws InterruptedException {
		
		for(int x=0;x<board.length;x++) {
			for(int y=0;y<board.length;y++) {
				
				if(board[y][x] == 0) { //(0, 0)+
					for(int z=1;z<=9;z++) {
						if(possible(y, x, z)) {
							board[y][x] = z;
							Thread.sleep(300);
							repaint();
						}
					}
				}
			}
		}
	}
	
	 boolean possible(int x, int y, int n) {
		
		for(int i=0;i<9;i++) {
			if(board[x][i] == n)
				return false;
		}
		
		for(int i=0;i<9;i++) {
			if(board[i][y] == n)
				return false;
		}
		
		int x1 = (x/3)*3, y1 = (y/3)*3;
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(board[x1+j][y1+i] == n)
					return false;
			}
		}
		return true;
	}

	@Override
	public void run() {
		
		try {
			solve();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}






