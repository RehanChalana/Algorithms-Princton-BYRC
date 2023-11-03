import edu.princeton.cs.algs4.StdRandom;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] rdqueue ; 
    private int N = 0;
    private Item[] temp;
    // construct an empty randomized queue
    public RandomizedQueue(){
        rdqueue = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return N==0;
    }

    private void resize(int max){
        Item[] temp = (Item[]) new Object[max];
        for(int i=0;i<N;i++){
            temp[i] = rdqueue[i];
        } 
        rdqueue = temp;
    }

    // return the number of items on the randomized queue
    public int size(){
        return N;
    }

    // add the item
    public void enqueue(Item item){
        if(N==rdqueue.length){
            resize(N*2);
        }
        rdqueue[N++] = item;
    }

    // remove and return a random item
    public Item dequeue(){
        int rmindex = StdRandom.uniformInt(N+1);
        Item ritem = rdqueue[rmindex];
        rdqueue[rmindex] = null;
        for(int i=rmindex+1;i<rdqueue.length;i++){
            rdqueue[i]=rdqueue[i+1];
        }
        N--;
        if(N==rdqueue.length/4){
            resize(N*2);
        }
        return ritem;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        return rdqueue[StdRandom.uniformInt(N+1)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new RandomArrayIterator();
    }

    private class RandomArrayIterator implements Iterator<Item>{
        // int i=0;
        // int b = StdRandom.u
        // this.temp = rdqueue;
        // StdRandom.shuffle(temp);
        // public boolean hasNext(){
        //     return i==temp.length;
        // }

        // public Item next(){
        //     return temp[i++];
        // }

    }

    // unit testing (required)
    public static void main(String[] args){

    }

}