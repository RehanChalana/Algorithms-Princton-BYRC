import java.util.Comparator;
import java.util.Scanner;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {
    private MinPQ<Node> minPQ; 
    private Node iniNode;
    private static final Comparator<Node> BY_BOARD = new byBoard(); 
    
    private class Node{
        Board board;
        int moves;
        Node previous;
    }

    private static class byBoard implements Comparator<Node>{
            public int compare(Node x , Node y){
                return (x.board.manhattan()+x.moves)-(y.board.manhattan()+y.moves);
            }
    }

    public Solver(Board initial){
        if(initial == null){
            throw new java.lang.IllegalArgumentException();
        }

        this.minPQ = new MinPQ<>(BY_BOARD);
        iniNode = new Node();
        iniNode.board=initial;
        iniNode.moves=0;
        iniNode.previous=null;
        this.minPQ.insert(iniNode);
        int count = 0;
        while(true){
            Node rmNode = this.minPQ.delMin();
            System.out.println(rmNode.board);
            if(rmNode.board.isGoal()){
                break;
            }
            count++;
            for(Board i :rmNode.board.neighbors()){
                Node newNode = new Node();
                newNode.board=i;
                newNode.moves=count;
                newNode.previous=rmNode;
                this.minPQ.insert(newNode);
            }
        }
    }

    public boolean isSolvable(){
        return false;
    }

    public int moves(){
        return -1;
    }

    public Iterable<Board> solution(){
        return iniNode.board.neighbors();
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

    }






}
