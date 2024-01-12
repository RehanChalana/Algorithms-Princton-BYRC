import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
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

    public void draw(){
        StdDraw.setPenRadius(0.01);
        for(Point2D i:this.BST){
            i.draw();
        }
    }

    public Point2D nearest(Point2D p){
        Point2D nearest=this.BST.first();
        for(Point2D i:this.BST){
            if(p.distanceTo(i)<p.distanceTo(nearest)){
                nearest = i;
            }
        }
        return nearest;
    }

    public static void main(String[] args) {
        PointSet set = new PointSet();
        set.insert(new Point2D(0.4, 0.9));
        set.insert(new Point2D(0.5, 0.5));
        set.insert(new Point2D(0.2, 0.2));
        set.insert(new Point2D(0.1, 0));
        System.out.println(set.nearest(new Point2D(0, 0)));
        set.draw();
    }
}
