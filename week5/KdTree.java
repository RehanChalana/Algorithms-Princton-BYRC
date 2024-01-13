import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree{
    private Node root;
    private int size;

    private static class Node{
        private Point2D p;
        private RectHV rect;
        private Node lb;
        private Node rt;
        // false for y and true for x
        private boolean orien;

        public Node(Point2D p,boolean orien){
            this.p = p;
            this.orien = orien;
        }
    }

    public boolean isEmpty(){
        return this.root == null;
    }

    public int size(){
        return this.size;
    }

    public void insert(Point2D p){
        root = insert(root, p, true);
    }

    private Node insert(Node cur,Point2D p,boolean orien){
        if(cur==null){
            this.size++;
            return new Node(p, orien);
        }
        double cmp;
        if(cur.orien){
             cmp = p.x()-cur.p.x();
        } else{
             cmp = p.y()-cur.p.y();
        }
        if(cmp<0){
            cur.lb = insert(cur.lb, p, !cur.orien);
        }
        else if(cmp>=0){
            cur.rt = insert(cur.rt, p, !cur.orien);
        }
        return cur;
    }

}