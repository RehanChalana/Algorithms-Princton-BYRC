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
}
