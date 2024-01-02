import java.util.Comparator;
import java.util.Scanner;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    // private final MinPQ<Node> minPQ; 
    private final Node finalNode;
    private static final Comparator<Node> BY_PRIORITY = new priority(); 
    private boolean isSolvable;
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
        MinPQ<Node> minPQ = new MinPQ<>(BY_PRIORITY);
        MinPQ<Node> twinminPQ = new MinPQ<>(BY_PRIORITY);
        Node iniNode = new Node();
        iniNode.board=initial;
        iniNode.moves=0;
        iniNode.previous=null;
        iniNode.manhattan=initial.manhattan();
        minPQ.insert(iniNode);

        Node twinIniNode = new Node();
        twinIniNode.board=initial.twin();
        twinIniNode.moves=0;
        twinIniNode.previous=null;
        twinIniNode.manhattan=initial.twin().manhattan();
        twinminPQ.insert(twinIniNode);

        boolean twinTurn = false;
        // this.moves = 0;
        while(true){
            if(!twinTurn){
                Node rmNode = minPQ.delMin();
                // System.out.println(rmNode.board+"actual");
                if(rmNode.board.isGoal()){
                    this.finalNode = rmNode;
                    this.isSolvable=true;
                    break;
                }
                for(Board i :rmNode.board.neighbors()){
                    Node newNode = new Node();
                    if(rmNode.moves>0 && i.equals(rmNode.previous.board)){
                        continue;
                    }
                        newNode.board=i;
                        newNode.moves=rmNode.moves+1;
                        newNode.previous=rmNode;
                        newNode.manhattan=i.manhattan();
                        minPQ.insert(newNode);
                }
                twinTurn=true;
            }

            if(twinTurn){
                Node rmNode = twinminPQ.delMin();
                // System.out.println(rmNode.board+"twin");
                if(rmNode.board.isGoal()){
                    this.isSolvable=false;
                    this.finalNode=null;
                    break;
                }
                for(Board i :rmNode.board.neighbors()){
                    Node newNode = new Node();
                    if(rmNode.moves>0 && i.equals(rmNode.previous.board)){
                        continue;
                    }
                        newNode.board=i;
                        newNode.moves=rmNode.moves+1;
                        newNode.previous=rmNode;
                        newNode.manhattan=i.manhattan();
                        twinminPQ.insert(newNode);
                }
                twinTurn=false;
            }
            
        }
    }

    public boolean isSolvable(){
        return this.isSolvable;
    }

    public int moves(){
        if(this.isSolvable()==false){
            return -1;
        }
        return this.finalNode.moves;
    }

    public Iterable<Board> solution(){
        if(this.isSolvable==false){
            return null;
        }
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
        // // for(Board i:solve.solution()){
        // //     System.out.println(i);
        // // }
        // System.out.println(solve.isSolvable());
    }
}
