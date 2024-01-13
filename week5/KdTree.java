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
    }

}