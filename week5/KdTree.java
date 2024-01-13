import java.util.Stack;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree{
    private Node root;
    private int size;

    public KdTree(){
        this.size = 0;
    }

    private static class Node{
        private Point2D p;
        private RectHV rect;
        private Node lb;
        private Node rt;
        // false for x and true for y

        public Node(Point2D p,RectHV rect){
            this.p = p;
            this.rect = rect;
        }
    }

    public boolean isEmpty(){
        return this.root == null;
    }

    public int size(){
        return this.size;
    }

    public void insert(Point2D p){
        root = insert(root, p, true,new RectHV(0,0,1,1));
    }

    private Node insert(Node cur,Point2D p,boolean orien,RectHV rect){
        if(cur==null){
            this.size++;
            return new Node(p,rect);
        }
        if(cur.p.equals(p)){
            return cur;
        }
        double cmp;
        if(orien){
             cmp = p.x()-cur.p.x();
        } else{
             cmp = p.y()-cur.p.y();
        }
        if(cmp<0){
            RectHV childRec;
            if(orien){
                childRec = new RectHV(cur.rect.xmin(), cur.rect.ymin(), cur.p.x(), cur.rect.ymax());
            } else{
                childRec = new RectHV(cur.rect.xmin(), cur.rect.ymin(), cur.rect.xmax(), cur.p.y());
            }
            cur.lb = insert(cur.lb, p, !orien,childRec);
        }
        else if(cmp>=0){
            RectHV childRec;
            if(orien){
                childRec = new RectHV(cur.p.x(), cur.rect.ymin(), cur.rect.xmax(), cur.rect.ymax());
            } else{
                childRec = new RectHV(cur.rect.xmin(), cur.p.y(), cur.rect.xmax(), cur.rect.ymax());
            }
            cur.rt = insert(cur.rt, p, !orien,childRec);
        }
        return cur;
    }

    public boolean contains(Point2D p){
        return contains(this.root, p,true);
    }

    private boolean contains(Node cur,Point2D p,boolean orien){
        if(cur==null){
            return false;
        }
        if(cur.p.equals(p)){
            return true;
        }
        double cmp;
        if(orien){
             cmp = p.x()-cur.p.x();
        } else{
             cmp = p.y()-cur.p.y();
        }
        if(cmp<0){
            return contains(cur.lb,p,!orien);
        } else if(cmp>=0){
            return contains(cur.rt,p,!orien);
        }
        return false;
    }

    public void draw(){
        // StdDraw.setPenRadius(0.002);
        draw(this.root,true);
    }

    private void draw(Node cur,boolean orien){
        if(cur == null){
            return;
        }
        draw(cur.lb,!orien);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.02);
        cur.p.draw();
        StdDraw.setPenRadius(0.005);
        if(orien){
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(cur.p.x(), cur.rect.ymin(), cur.p.x(), cur.rect.ymax());
        } else{
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(cur.rect.xmin(), cur.p.y(), cur.rect.xmax(), cur.p.y());   
        }
        draw(cur.rt,!orien);
    }

    public Point2D nearest(Point2D p){
        
        return new Point2D(0, 0);
    }



    public Iterable<Point2D> range(RectHV rect){
        Stack<Point2D> stack = new Stack<>();
        range(this.root,rect,stack);
        return stack;
    }

    private void range(Node cur,RectHV rect,Stack<Point2D> stack){
        if(cur==null || !rect.intersects(cur.rect)){
            return;
        }
        range(cur.lb,rect,stack);
        if(rect.contains(cur.p)){
            stack.push(cur.p);
        }
        range(cur.rt,rect,stack);
    }

    public static void main(String[] args) {
        KdTree tree = new KdTree();
        // tree.insert(new Point2D(0.5, 0.5));
        tree.insert(new Point2D(0.6, 0.6));
        tree.insert(new Point2D(0.7, 0.8));
        tree.insert(new Point2D(0.8, 0.9));
        tree.insert(new Point2D(0.7, 0.6));
        tree.insert(new Point2D(0.1, 0.2));
        tree.insert(new Point2D(0.1, 0.5));
        tree.insert(new Point2D(0.3, 0.4));
        for(Point2D i:tree.range(new RectHV(0.5, 0.6, 1, 1))){
            System.out.println(i);
        }
        System.out.println(tree.size());
        tree.draw();
        System.out.println(tree.contains(new Point2D(1, 0.5)));

    }

}