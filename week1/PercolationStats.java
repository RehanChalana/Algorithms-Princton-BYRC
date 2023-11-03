import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.Math;


public class PercolationStats {
    private double[] fractions;
    private int trials;

    public PercolationStats(int n, int trials){
        this.trials=trials;
        fractions = new double[trials];
        if(n<1){
            throw new IllegalArgumentException();
        }
        for(int i=0;i<trials;i++){
            Percolation per = new Percolation(n);
            int opensites=0;
            while(!per.percolates()){
                per.open(StdRandom.uniformInt(1,n+1), StdRandom.uniformInt(1,n+1));
                opensites++;
            }
            double fraction = (double) opensites / (n*n);
            fractions[i]=fraction;   
        }

    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean()-((1.96*stddev())/Math.sqrt(trials));
    }
    

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean()+((1.96*stddev())/Math.sqrt(trials));
    }

    public static void main(String[] args){
        PercolationStats perstats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        String confidence = "["+perstats.confidenceLo() + ", " + perstats.confidenceHi()+"]";
        StdOut.println("mean                    = " + perstats.mean());
        StdOut.println("stddev                  = " + perstats.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }
        
    }

