
import java.util.Arrays;
import java.util.Scanner;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdRandom;;

public class Board {
    private final int[][] board_array;
    private final int[][] answer_array;
    private final int[]   empty_position_array;
    private final int N;

    public Board(int[][] tiles){
        this.N = tiles.length;
        this.board_array = new int[this.N][this.N];
        this.answer_array = new int[this.N][this.N];
        this.empty_position_array = new int[2];
        for(int i=0;i<N;i++){
            for(int j=0; j<N;j++){
                this.board_array[i][j]=tiles[i][j];
                this.answer_array[i][j]=getTile(i, j);
                if(tiles[i][j]==0){
                    this.empty_position_array[0]=i;
                    this.empty_position_array[1]=j;
                }
            }
        }
        this.answer_array[this.N-1][this.N-1]=0;
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
        return Arrays.deepEquals(this.board_array, this.answer_array);
    }

    private int getTile(int row,int col){
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
        if(this.empty_position_array[1]<this.N-1){
            int[][] right_exchange = copyArray();
            int temp = right_exchange[this.empty_position_array[0]][this.empty_position_array[1]+1];
            right_exchange[this.empty_position_array[0]][this.empty_position_array[1]+1]=0;
            right_exchange[this.empty_position_array[0]][this.empty_position_array[1]]=temp;
            Board rightBoard = new Board(right_exchange);
            neighbors.push(rightBoard);
        }
        if(this.empty_position_array[1]>0){
            int[][] left_exchange = copyArray();
            int temp = left_exchange[this.empty_position_array[0]][this.empty_position_array[1]-1];
            left_exchange[this.empty_position_array[0]][this.empty_position_array[1]-1]=0;
            left_exchange[this.empty_position_array[0]][this.empty_position_array[1]]=temp;
            Board leftBoard = new Board(left_exchange);
            neighbors.push(leftBoard);
        }
        if(this.empty_position_array[0]>0){
            int[][] top_exchange = copyArray();
            int temp = top_exchange[this.empty_position_array[0]-1][this.empty_position_array[1]];
            top_exchange[this.empty_position_array[0]-1][this.empty_position_array[1]]=0;
            top_exchange[this.empty_position_array[0]][this.empty_position_array[1]]=temp;
            Board topBoard = new Board(top_exchange);
            neighbors.push(topBoard);
        }
        if(this.empty_position_array[0]<this.N-1){
            int[][] bottom_exchange = copyArray();
            int temp = bottom_exchange[this.empty_position_array[0]+1][this.empty_position_array[1]];
            bottom_exchange[this.empty_position_array[0]+1][this.empty_position_array[1]]=0;
            bottom_exchange[this.empty_position_array[0]][this.empty_position_array[1]]=temp;
            Board bottomBoard = new Board(bottom_exchange);
            neighbors.push(bottomBoard);
        }
        return neighbors;
    }

    public Board twin(){
        int[][] twinArray = copyArray();
        int i = StdRandom.uniformInt(this.N);
        int j = StdRandom.uniformInt(this.N);
        int q = StdRandom.uniformInt(this.N);
        int s = StdRandom.uniformInt(this.N);
        while(twinArray[i][j]==0 || twinArray[q][s]==0 || twinArray[i][j]==twinArray[q][s]){
            i=StdRandom.uniformInt(this.N);
            j=StdRandom.uniformInt(this.N);
            q=StdRandom.uniformInt(this.N);
            s=StdRandom.uniformInt(this.N);
        }
        int temp = twinArray[i][j];
        twinArray[i][j]=twinArray[q][s];
        twinArray[q][s]=temp;
        Board twin = new Board(twinArray);
        return twin;
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
                    if(this.answer_array[q][s]==this.board_array[row][col]){
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
        System.out.println(initial);
        System.out.println(initial.twin());
        // System.out.println(initial.hamming());
        // System.out.println(initial.manhattan());
        
    }
}
