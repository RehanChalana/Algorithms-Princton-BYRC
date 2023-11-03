import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champ = "";
        int i =1;
        while(!StdIn.isEmpty()){
            String in = StdIn.readString();
            if(StdRandom.bernoulli(1.0/i)){
                champ = in ;
            }
            i++;
        }
        StdOut.println(champ);
    }
}
