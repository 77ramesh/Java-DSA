import java.awt.Point;

public class ShortPointPath{
	
	public static void main(String[] args){
		
		Tree tree = new Tree();
		
		tree.addPoint(new Point(34, 145));
		tree.addPoint(new Point(100, 347));
		tree.addPoint(new Point(56, 78));
		tree.addPoint(new Point(98, 455));
		tree.addPoint(new Point(90, 300));
		
		tree.traverse();
		
		tree.search(new Point(98, 455));
	}
}

class Tree{
	
	Node root;
	
	public void addPoint(Point p){
		Node newNode = new Node(p);
		if(root == null){
			root = newNode;
		} else
			this.root.addNode(newNode);
			
	}
	
	public void traverse(){
		this.root.visit();
	}
	public int search(Point p){
		return this.root.searchPoint(p);
	}
	
}

class Node{
	
	Point point;
	Node left;
	Node right;
	
	public Node(Point point){
		this.point = point;
		left = null;
		right = null;
	}
	
	public void addNode(Node n){
		
		int val1 = point.x + point.y;
		int val2 = n.point.x + n.point.y;
		
		if(val2 < val1){
			if(this.left == null)
				this.left = n;
			else
				this.left.addNode(n);
		}
		if(val2 > val1){
			if(this.right == null)
				this.right = n;
			else
				this.right.addNode(n);
		}
	}
	
	public void visit(){
		
		if(this.left != null)
			this.left.visit();
		System.out.println(this.point.x + " " + this.point.y);
		if(this.right != null)
			this.right.visit();
	}
	
	public int searchPoint(Point p){
		
		Point rootP = this.point;
		
		int val = p.x + p.y;
		int current = rootP.x + rootP.y;
		
		
		if((p.x == rootP.x) && (p.y == rootP.y)){
			System.out.println("Finished");
			return 1;
		}
		else if(val < current && this.left != null) {
			this.left.searchPoint(p);
		}
		else if(val > current && this.right != null){
			this.right.searchPoint(p);
		}
		
		return -1;
	}

}




