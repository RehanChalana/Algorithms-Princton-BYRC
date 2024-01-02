import java.util.Comparator;
import java.util.Scanner;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    private final MinPQ<Node> minPQ; 
    private final Node finalNode;
    private static final Comparator<Node> BY_PRIORITY = new priority(); 
    // private int moves;
    
    private class Node{
        Board board;
        int moves;
        Node previous;
        int manhattan;
    }

    private static class priority implements Comparator<Node>{
            public int compare(Node x , Node y){
                return (x.manhattan+x.moves)-(y.manhattan+y.moves);
            }
    }

    public Solver(Board initial){
        if(initial == null){
            throw new java.lang.IllegalArgumentException();
        }
        this.minPQ = new MinPQ<>(BY_PRIORITY);
        Node iniNode = new Node();
        iniNode.board=initial;
        iniNode.moves=0;
        iniNode.previous=null;
        iniNode.manhattan=initial.manhattan();
        this.minPQ.insert(iniNode);
        // this.moves = 0;
        while(true){
            Node rmNode = this.minPQ.delMin();
            // System.out.println(rmNode.board+"del node");
            // System.out.println(rmNode.board.hamming()+rmNode.moves);
            if(rmNode.board.isGoal()){
                this.finalNode = rmNode;
                break;
            }
            // this.moves++;
            for(Board i :rmNode.board.neighbors()){
                Node newNode = new Node();
                if(rmNode.moves>0 && i.equals(rmNode.previous.board)){
                    continue;
                }
                    newNode.board=i;
                    newNode.moves=rmNode.moves+1;
                    newNode.previous=rmNode;
                    newNode.manhattan=i.manhattan();
                    // System.out.println(i+"added node");
                    this.minPQ.insert(newNode);
            }
        }
    }

    public boolean isSolvable(){
        return false;
    }

    public int moves(){
        return this.finalNode.moves;
    }

    public Iterable<Board> solution(){
        Stack<Board> answStack = new Stack<>();
        Node curNode = this.finalNode;
        while(true){
            answStack.push(curNode.board);
            if(curNode.moves==0){
                break;
            }
            curNode=curNode.previous;
        }
        return answStack;
    }

    public static void main(String[] args) {
        // Scanner scan = new Scanner(System.in);
        // In in = new In(scan.nextLine());
        // int n = in.readInt();
        // int[][] tiles = new int[n][n];
        // for (int i = 0; i < n; i++){
        //     for (int j = 0; j < n; j++){
        //         tiles[i][j] = in.readInt();
        //     }
        // }        
        // Board initial = new Board(tiles);
        // Solver solve = new Solver(initial);
        // for(Board i:solve.solution()){
        //     System.out.println(i);
        // }
    }
}
