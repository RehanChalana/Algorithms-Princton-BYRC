package week4;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private final int[][] board_array;
    private final int[][] answer_array;
    private final int N;

    public Board(int[][] tiles){
        this.N = tiles.length;
        this.board_array = new int[this.N][this.N];
        this.answer_array = new int[this.N][this.N];
        for(int i=0;i<N;i++){
            for(int j=0; j<N;j++){
                this.board_array[i][j]=tiles[i][j];
                this.answer_array[i][j]=getTile(i, j);
            }
        }
    }

    public String toString(){
        String dim = N+"\n";
        String answer = dim;
        for(int i=0;i<N;i++){
            String line = "";
            for(int j=0;j<N;j++){
                line = line +this.board_array[i][j]+" ";
            }
            if(i==this.N-1){
                answer=answer+line;
            } else{
                answer = answer+line+"\n";
            }
            
        }
        return answer;
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

    private int getTile(int row,int col){
        return ((this.N-1)*row)+col+row+1;
    }

    // public int manhattan(){
    //     int check = 1;
    //     int sum = 0;
    //     for(int i=0;i<this.N;i++){
    //         for(int j=0;j<this.N;j++){
                
                
    //             check++;
    //         }
    //     }
    //     return sum;
    // }



    public static void main(String[] args) {
        int num=0;
        int[][] in = new int[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                in[i][j]=num;
                // System.out.print(in[i][j]+" ");
                num++;
            }
            // System.out.println();
        }
        Board brd = new Board(in);
        // System.err.println(brd);
        System.out.println(brd.hamming());
        // System.out.println(brd.manhattan());
    }
}

