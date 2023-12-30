import java.util.Comparator;

import edu.princeton.cs.algs4.MinPQ;

public class Solver {
    private MinPQ minPQ; 
    
    private class Node{
        Board board;
        int moves;
        Node previous;
        
        public int compareTo(Node that){
            return (this.board.manhattan()+this.moves)-(that.board.manhattan()+that.moves);
        }
        
    }

    


}
