import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

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
        // false for y and true for x

        public Node(Point2D p){
            this.p = p;
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
            return new Node(p);
        }
        double cmp;
        if(orien){
             cmp = p.x()-cur.p.x();
        } else{
             cmp = p.y()-cur.p.y();
        }
        if(cmp<0){
            cur.lb = insert(cur.lb, p, !orien);
        }
        else if(cmp>=0){
            cur.rt = insert(cur.rt, p, !orien);
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

    public static void main(String[] args) {
        KdTree tree = new KdTree();
        tree.insert(new Point2D(0.1, 0.2));
        tree.insert(new Point2D(0.4, 0.6));
        tree.insert(new Point2D(0.5, 0.8));
        tree.insert(new Point2D(0.25, 0.67));
        tree.insert(new Point2D(0.10, 0));
        tree.insert(new Point2D(0.6, 0.69));
        System.out.println(tree.contains(new Point2D(0.1, 0.2)));
        System.out.println(tree.contains(new Point2D(0.4, 0.6)));
        System.out.println(tree.contains(new Point2D(0.5, 0.8)));
        System.out.println(tree.contains(new Point2D(0.25, 0.67)));
        System.out.println(tree.contains(new Point2D(0.10, 0)));
        System.out.println(tree.contains(new Point2D(0.6, 0.69)));
        System.out.println(tree.contains(new Point2D(1, 0.5)));

    }

}