import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
public class PointSet {
    private TreeSet<Point2D> BST;

    public PointSet(){
        this.BST = new TreeSet<>();
    }

    public Boolean isEmpty(){
        return this.BST.isEmpty();
    }

    public int size(){
        return this.BST.size();
    }

    public void insert(Point2D p){
        this.BST.add(p);
    }

    public boolean contains(Point2D p){
        return this.BST.contains(p);
    }

    
}
