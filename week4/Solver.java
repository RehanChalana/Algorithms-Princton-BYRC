import java.util.Comparator;
import java.util.Scanner;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {
    private final MinPQ<Node> minPQ; 
    private final Node finalNode;
    private static final Comparator<Node> BY_PRIORITY = new priority(); 
    // private int moves;
    
    private class Node{
        Board board;
        int moves;
        Node previous;
    }

    private static class priority implements Comparator<Node>{
            public int compare(Node x , Node y){
                return (x.board.manhattan()+x.moves)-(y.board.manhattan()+y.moves);
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
        this.minPQ.insert(iniNode);
        // this.moves = 0;
        while(true){
            Node rmNode = this.minPQ.delMin();
            System.out.println(rmNode.board);
            // System.out.println(rmNode.board.hamming()+rmNode.moves);
            if(rmNode.board.isGoal()){
                this.finalNode = rmNode;
                break;
            }
            // this.moves++;
            for(Board i :rmNode.board.neighbors()){
                Node newNode = new Node();
                if(!i.equals(rmNode.board)){
                    newNode.board=i;
                    newNode.moves=rmNode.moves+1;
                    newNode.previous=rmNode;
                    this.minPQ.insert(newNode);
                }   
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
        return this.finalNode.board.neighbors();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        In in = new In(scan.nextLine());
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                tiles[i][j] = in.readInt();
            }
        }        
        Board initial = new Board(tiles);
        Solver solve = new Solver(initial);
        System.out.println(solve.moves());
    }






}
