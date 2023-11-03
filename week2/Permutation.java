import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> rqStrings = new RandomizedQueue<>();
        while(StdIn.hasNextLine()){
            rqStrings.enqueue(StdIn.readLine());
        }
        for(int i=0;i<Integer.parseInt(args[0]);i++){
            StdOut.print(rqStrings.sample());
        }
    }
    
}
