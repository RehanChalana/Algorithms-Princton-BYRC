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
        if(n<1){
            throw new IllegalArgumentException();
        }
        this.N=n;
        this.UF = new WeightedQuickUnionUF((n*n)+2);
        this.openOrNot = new boolean[n*n];
        numberOfOpenSites = 0;
        for(int i=0;i<openOrNot.length;i++){
            openOrNot[i]=false;
        }
    } 

    public void open(int row, int col){
        if(row<1 || col<1 || row>N || col>N){
            throw new IllegalArgumentException();
        }
        if(convertIndex(row, col)<1 || convertIndex(row, col)>N*N+1){
            throw new IllegalArgumentException();
        }
        if(!isOpen(row, col)){
            openOrNot[convertIndex(row, col)-1]=true;
            // connecting to top virtual site if top row element is open
            if(convertIndex(row, col)<=N){
                UF.union(0,convertIndex(row, col));
            }
            // connecting to down virtual site if last row element is open
            if(convertIndex(row, col)>N*(N-1)){
                UF.union((N*N)+1,convertIndex(row, col));
            }
            unionNBRS(row,col);
            numberOfOpenSites++;
        }     
    }

    public boolean isOpen(int row , int col){
        if(row<1 || col<1 || row>N || col>N){
            throw new IllegalArgumentException();
        }
           if(convertIndex(row, col)<1 || convertIndex(row, col)>N*N+1){
            throw new IllegalArgumentException();
        }
        return openOrNot[convertIndex(row, col)-1];
    }

    public boolean isFull(int row , int col){
        if(row<1 || col<1 || row>N || col>N){
            throw new IllegalArgumentException();
        }
           if(convertIndex(row, col)<1 || convertIndex(row, col)>N*N+1){
            throw new IllegalArgumentException();
        }
        return UF.find(convertIndex(row, col))==UF.find(0);
    }

    public int numberOfOpenSites(){
        return numberOfOpenSites;
    }

    public boolean percolates(){
        return UF.find(N*N+1)==UF.find(0);
    }

    private void unionNBRS(int row , int col){
        if(checkRight(row,col)){
            UF.union(convertIndex(row, col),convertIndex(row, col)+1);
        }
        if(checkLeft(row,col)){
            UF.union(convertIndex(row, col),convertIndex(row, col)-1);
        }
        if(checkUp(row,col)){
           UF.union(convertIndex(row, col),convertIndex(row, col)-N); 
        }
        if(checkDown(row,col)){
            UF.union(convertIndex(row, col),convertIndex(row, col)+N); 
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
        if(convertIndex(row, col)-1>=(N*(row-1))+1){
            if(isOpen(row, col-1)){
                return true;
            }    
        }
        return false;
    }

    private boolean checkUp(int row,int col){
        if(convertIndex(row, col)-N >= 1){
            if(isOpen(row-1, col)){
                return true;
            }    
        }
        return false;
    }

    private boolean checkDown(int row , int col){
        if(convertIndex(row, col) + N <= N*N){
            if(isOpen(row+1, col))
            return true;
        }
        return false;
    }

    private void printUF(){
        System.out.println(UF.find(0));
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                System.out.print(UF.find(convertIndex(i, j))+" ");
            }
            System.out.println();
        }
        System.out.println(UF.find(N*N+1));
    }

    public static void main(String[] args) {
        Percolation per = new Percolation(4);
        per.open(1, 1);
        per.open(2, 1);
        per.open(2,2);
        per.open(2,3);
        // per.open(3, 1);
        // per.open(3,2);
        per.open(3, 3);
        per.open(4, 1);
        per.open(4,3);
        // per.open(4,4);
        // per.open(2, 2);
        per.printUF();
        System.out.println(per.isFull(3, 1));
    }
}
