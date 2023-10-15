import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF UF;
    private boolean[] openOrNot;
    private int N;

    public Percolation(int n){
        this.N=n;
        this.UF = new WeightedQuickUnionUF((n*n)+2);
        this.openOrNot = new boolean[n*n];
        for(int i=0;i<openOrNot.length;i++){
            openOrNot[i]=false;
            if(i<n){
                UF.union(0,i+1);
            }
            if(i>n*(n-1)){
                UF.union((n*n)+1, i);
            }
        }
    } 

    public void open(int row, int col){
        if(!openOrNot[convertIndex(row, col)-1]){
            unionNBRS(row,col);
            openOrNot[convertIndex(row, col)-1]=true;;
        }     
    }

    private void unionNBRS(int row , int col){
        if(checkRight(row,col)){
            UF.union(convertIndex(row, col),convertIndex(row, col)+1);
        }
        if(checkLeft()){
            UF.union(convertIndex(row, col),convertIndex(row, col)-1);
        }
        if(checkUp()){
           UF.union(convertIndex(row, col),convertIndex(row, col)-5); 
        }
        if(checkDown()){
            UF.union(convertIndex(row, col),convertIndex(row, col)+5); 
        }
    }

    private int convertIndex(int row , int col){
        return ((row-1)*this.N)+(col);
    }

    private boolean checkRight(int row , int col){
        
    }


}
