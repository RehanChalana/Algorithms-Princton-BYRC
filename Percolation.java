import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF UF;
    private boolean[] openOrNot;
    private int N;
    private int numberOfOpenSites;

    public Percolation(int n){
        this.N=n;
        this.UF = new WeightedQuickUnionUF((n*n)+2);
        this.openOrNot = new boolean[n*n];
        numberOfOpenSites = 0;
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
            openOrNot[convertIndex(row, col)-1]=true;
            numberOfOpenSites++;
        }     
    }

    public boolean isOpen(int row , int col){
        return openOrNot[convertIndex(row, col)-1];
    }

    public boolean isFull(int row , int col){
        return UF.find(convertIndex(row, col))==UF.find(0);
    }

    public int numberOfOpenSites(){
        return numberOfOpenSites;
    }

    public boolean percolates(){
        return UF.find(N*N+2)==UF.find(0);
    }

    private void unionNBRS(int row , int col){
        if(checkRight(row,col)){
            UF.union(convertIndex(row, col),convertIndex(row, col)+1);
        }
        if(checkLeft(row,col)){
            UF.union(convertIndex(row, col),convertIndex(row, col)-1);
        }
        if(checkUp(row,col)){
           UF.union(convertIndex(row, col),convertIndex(row, col)-5); 
        }
        if(checkDown(row,col)){
            UF.union(convertIndex(row, col),convertIndex(row, col)+5); 
        }
    }

    private int convertIndex(int row , int col){
        return ((row-1)*this.N)+(col);
    }

    private boolean checkRight(int row , int col){
        if(convertIndex(row, col)+1<=row*N){
            if(isOpen(row, col+1)){
                return true;
            }   
        }
        return false;
    }

    private boolean checkLeft(int row, int col){
        if(convertIndex(row, col)-1>=(5*(row-1))+1){
            if(isOpen(row, col-1)){
                return true;
            }    
        }
        return false;
    }

    private boolean checkUp(int row,int col){
        if(convertIndex(row, col)-5>=1){
            if(isOpen(row-1, col)){
                return true;
            }    
        }
        return false;
    }

    private boolean checkDown(int row , int col){
        if(convertIndex(row, col)+5>=N*N){
            if(isOpen(row+1, col))
            return true;
        }
        return false;
    }


}
