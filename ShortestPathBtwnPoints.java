package methods;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.JComponent;
import javax.swing.JFrame;

@SuppressWarnings("unused")
public class QueueJava {

	//list of all the places
	static List<Place> places = new ArrayList<>();
	
	public static void main(String[] args) {
		
		places.add(new Place(new Point(300, 34), "Sankhamul"));
		places.add(new Place(new Point(45, 200), "Patan"));
		places.add(new Place(new Point(234, 100), "Bhirkutimandap"));
		places.add(new Place(new Point(450, 400), "Baneshwor"));
		places.add(new Place(new Point(100, 345), "Tinkune"));
		places.add(new Place(new Point(300, 300), "Britamod"));
		
		//FIFO: queue works on the concept of first-in first-out
		
		Point startingPoint = places.get(1).getPoint();
		Point endPoint = places.get(places.size()-1).getPoint();
		
		new Frame(places, startingPoint, endPoint);

	}
	
}

class Place {
	
	Point point;
	String name;
	
	public Place(Point point, String name) {
		this.point = point;
		this.name = name;
	}

	public Point getPoint() {
		return point;
	}

	public String getName() {
		return name;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}


class NearestPoint {
	
	public NearestPoint() {
		
	}
	
	Point start;
	List<Place> places;
	Point closestPoint;
	
	public NearestPoint(List<Place> places, Point point) {
		this.start = point;
		this.places = places;
		
		closestPoint = places.get(0).getPoint();
		double closestDist = dist(start.x, closestPoint.x, start.y, closestPoint.y);
		
		
		for(int i=0;i<places.size();i++) {
			
			Point p = places.get(i).getPoint();
			double dist = dist(start.x, p.x, start.y, p.y);
			
			if(dist < closestDist && dist != 0.0) {
				closestDist = dist;
				closestPoint = places.get(i).getPoint();
			}
			if(i == places.size() - 1) {
				System.out.println("Closest dist: " + closestDist);
				System.out.println("Fee: " + ((int)closestDist/10));
			}
		}
	}
	
	public Point getNearestPoint() {
		return closestPoint;
	}
	
	static double dist(double x1, double x2, double y1, double y2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
}


//for GUI
class Frame extends JFrame{
	
	public Frame(){
		
	}
	
	public Frame(List<Place> places, Point start, Point nearest) {
		setTitle("Find Shortest Path");
		setSize(600, 600);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
		getContentPane().add(new GraphView(places, start, nearest));
	}
}

class GraphView extends JComponent{
	
	List<Place> place;
	Point nearest, start;
	
	public GraphView(List<Place> place, Point start, Point nearest) {
		this.place = place;
		this.nearest = nearest;
		this.start = start;
	}
	
	@Override
	public void paint(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		int h = 50, w = 50;
		
		NearestPoint np;
		
		for(int i=0;i<place.size();i++) {
			
			np = new NearestPoint(place, place.get(i).getPoint());
			
			Point p = place.get(i).getPoint();
			g2d.drawString(place.get(i).getName(), p.x+25, p.y-5);
			g2d.draw(new Rectangle2D.Double(p.x, p.y, w, h));
			
			if(i != place.size()-1)
				g2d.draw(new Line2D.Double(p, np.getNearestPoint()));
		}
		
		g2d.setPaint(Color.RED);
		g2d.draw(new Line2D.Double(new Point(start.x + 25, start.y + 25), new Point(nearest.x + 25, nearest.y + 25)));
		g2d.drawString("Start", start.x, start.y);
		g2d.drawString("End", nearest.x, nearest.y);
	}
}




