
import java.util.Arrays;
import java.util.Scanner;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;


public final class Board {
    private final int[][] board_array;
    // private final int[] empty_position_array;
    private final int N;
    private int empty_position_row;
    private int empty_position_column;

    public Board(int[][] tiles){
        this.N = tiles.length;
        this.board_array = new int[this.N][this.N];
        // this.empty_position_array = new int[2];
        for(int i=0;i<N;i++){
            for(int j=0; j<N;j++){
                this.board_array[i][j]=tiles[i][j];
                if(tiles[i][j]==0){
                    this.empty_position_row=i;
                    this.empty_position_column=j;
                }
            }
        }
    }

    public String toString(){
        StringBuilder answer = new StringBuilder(this.N+"\n");
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                answer.append(this.board_array[i][j]+" ");
            }
            answer.append("\n"); 
        }
        return answer.toString();
    }

    public int dimension(){
        return this.N;
    }

    public int hamming(){
        int count=0;
        for(int i=0;i<this.N;i++){
            for(int j=0;j<this.N;j++){
                if(i==this.N-1 && j==this.N-1){
                    break;
                }
                if(this.board_array[i][j]!=getTile(i, j)){
                    count++;
                }
            }
        }
        return count;
    }

    public boolean isGoal(){ 
        int[][] answerArray = new int[this.N][this.N];
        for(int i=0;i<this.N;i++){
            for(int j=0;j<this.N;j++){
                answerArray[i][j]=getTile(i, j);
            }
        }
        return Arrays.deepEquals(this.board_array, answerArray);
    }

    private int getTile(int row,int col){
        if(row==this.N-1 && col == this.N-1){
            return 0;
        }
        return ((this.N-1)*row)+col+row+1;
    }

    public int manhattan(){
        int sum = 0;
        for(int i=0;i<this.N;i++){
            for(int j=0;j<this.N;j++){
             if(this.board_array[i][j]!=0 && this.board_array[i][j]!=getTile(i, j)){
                        sum+=findMDistance(i, j);
                    }
                }
            }
        return sum;
    }

    public boolean equals(Object x){
        if(this==x){
            return true;
        }
        if(x==null){
            return false;
        }
        if(this.getClass()!=x.getClass()){
            return false;
        }
        Board that = (Board)x;
        return this.N==that.N && Arrays.deepEquals(this.board_array, that.board_array);
    }

    public Iterable<Board> neighbors(){
        Stack<Board> neighbors = new Stack<>();
        if(this.empty_position_column<this.N-1){
            int[][] right_exchange = copyArray();
            int temp = right_exchange[this.empty_position_row][this.empty_position_column+1];
            right_exchange[this.empty_position_row][this.empty_position_column+1]=0;
            right_exchange[this.empty_position_row][this.empty_position_column]=temp;
            Board rightBoard = new Board(right_exchange);
            neighbors.push(rightBoard);
        }
        if(this.empty_position_column>0){
            int[][] left_exchange = copyArray();
            int temp = left_exchange[this.empty_position_row][this.empty_position_column-1];
            left_exchange[this.empty_position_row][this.empty_position_column-1]=0;
            left_exchange[this.empty_position_row][this.empty_position_column]=temp;
            Board leftBoard = new Board(left_exchange);
            neighbors.push(leftBoard);
        }
        if(this.empty_position_row>0){
            int[][] top_exchange = copyArray();
            int temp = top_exchange[this.empty_position_row-1][this.empty_position_column];
            top_exchange[this.empty_position_row-1][this.empty_position_column]=0;
            top_exchange[this.empty_position_row][this.empty_position_column]=temp;
            Board topBoard = new Board(top_exchange);
            neighbors.push(topBoard);
        }
        if(this.empty_position_row<this.N-1){
            int[][] bottom_exchange = copyArray();
            int temp = bottom_exchange[this.empty_position_row+1][this.empty_position_column];
            bottom_exchange[this.empty_position_row+1][this.empty_position_column]=0;
            bottom_exchange[this.empty_position_row][this.empty_position_column]=temp;
            Board bottomBoard = new Board(bottom_exchange);
            neighbors.push(bottomBoard);
        }
        return neighbors;
    }

    public Board twin(){
        int[][] twinArray = copyArray();
        int i = 0;
        int j = 0;
        if((this.empty_position_row==i && this.empty_position_column==j) || (this.empty_position_row==i && this.empty_position_column==j+1)){
           i++;
        } 
        int temp = twinArray[i][j];
        twinArray[i][j]=twinArray[i][j+1];
        twinArray[i][j+1]=temp;
        return new Board(twinArray);
    }

    private int[][] copyArray(){
        int[][] copy = new int[this.N][this.N];
        for(int i=0;i<this.N;i++){
            for(int j=0;j<this.N;j++){
                copy[i][j]=this.board_array[i][j];
            }
        }
        return copy;
    }
    

    private int findMDistance(int row , int col){
         for(int q=0;q<this.N;q++){
                for(int s=0;s<this.N;s++){
                    if(getTile(q, s)==this.board_array[row][col]){
                        int x = 0;
                        if(row>=q){
                            x=row-q;
                        } else{
                            x=q-row;
                        }
                        int y = 0;
                        if(col>=s){
                            y=col-s;
                        } else{
                            y=s-col;
                        }
                        return y+x;
                    }
                }
          }
          return 0;
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
        // System.err.println(initial);
        // System.out.println(initial.isGoal());
        // System.out.println(initial.twin());
        
    }
}
