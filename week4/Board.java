package week4;

public class Board {
    private int[][] board_array;
    private int N;

    public Board(int[][] tiles){
        for(int i=0;i<N;i++){
            for(int j=0; j<N;i++){
                this.board_array[i][j]=tiles[i][j];
            }
        }
    }

    public static void main(String[] args) {
        
    }
}

