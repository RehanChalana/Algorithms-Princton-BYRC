import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;
public class PointSET {
    private TreeSet<Point2D> BST;

    public PointSET(){
        this.BST = new TreeSet<>();
    }

    public boolean isEmpty(){
        return this.BST.isEmpty();
    }

    public int size(){
        return this.BST.size();
    }

    public void insert(Point2D p){
        if(p==null){
            throw new java.lang.IllegalArgumentException();
        }
        this.BST.add(p);
    }

    public boolean contains(Point2D p){
        if(p==null){
            throw new java.lang.IllegalArgumentException();
        }
        return this.BST.contains(p);
    }

    public void draw(){
        StdDraw.setPenRadius(0.01);
        for(Point2D i:this.BST){
            i.draw();
        }
    }

    public Point2D nearest(Point2D p){
        if(p==null){
            throw new java.lang.IllegalArgumentException();
        }
        if(this.BST.isEmpty()){
            return null;
        }
        Point2D nearest=this.BST.first();
        for(Point2D i:this.BST){
            if(p.distanceTo(i)<p.distanceTo(nearest)){
                nearest = i;
            }
        }
        return nearest;
    }

    public Iterable<Point2D> range(RectHV rect){
        if(rect==null){
            throw new java.lang.IllegalArgumentException();
        }
        Stack<Point2D> stack = new Stack<>();
        if(this.BST.isEmpty()){
            return stack;
        }
        for(Point2D i:this.BST){
            if(i.x()>=rect.xmin() && i.x()<=rect.xmax() && i.y()>=rect.ymin() && i.y()<=rect.ymax()){
                stack.push(i);
            }
        }
        return stack;
    }

    public static void main(String[] args) {
        PointSET set = new PointSET();
        set.insert(new Point2D(0.4, 0.4));
        set.insert(new Point2D(0.2, 0.4));
        set.insert(new Point2D(0.7, 0.8));
        set.insert(new Point2D(0.9, 0.9));
        for(Point2D i:set.range(new RectHV(0.1, 0.1, 0.6, 0.6))){
            System.out.println(i);
        }
        // System.out.println(set.nearest(new Point2D(0, 0)));
        // set.insert(new Point2D(0.0, 0.0));
        set.draw();
    }
}
