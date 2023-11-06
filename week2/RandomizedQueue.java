import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] rdqueue ; 
    private int N = 0;
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
        int nonnull = 0;
        for(int i=0;i<N;i++){
            if(rdqueue[i]!=null){
                temp[nonnull]=rdqueue[i];
                nonnull++;
            }
        } 
        rdqueue = temp;
    }

    // return the number of items on the randomized queue
    public int size(){
        return N;
    }

    // add the item
    public void enqueue(Item item){
        if(item == null){
            throw new IllegalArgumentException();
        }
        rdqueue[N++] = item;
        if(N==rdqueue.length){
            resize(N*2);
        } 
    }

    // remove and return a random item
    public Item dequeue(){
        if(N==0){
            throw new java.util.NoSuchElementException();
        }
        int rmindex = StdRandom.uniformInt(N);
        Item ritem = rdqueue[rmindex];
        rdqueue[rmindex] = rdqueue[N-1];
        rdqueue[N--]=null;
        if(N==rdqueue.length/4 && N>0){
            resize(N*2);
        }
        return ritem;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if(N==0){
            throw new java.util.NoSuchElementException();
        }
        return rdqueue[StdRandom.uniformInt(N)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new RandomArrayIterator();
    }

    private class RandomArrayIterator implements Iterator<Item> {
        Item[] temp = (Item[]) new Object[N];
        int count = 0;
        public RandomArrayIterator(){
            for(int i=0;i<N;i++){
            temp[i]=rdqueue[i];
        }
        StdRandom.shuffle(temp);
        }
        
        public boolean hasNext(){
            return count!=temp.length;
        }

        public Item next(){
            if(count==temp.length){
                throw new java.util.NoSuchElementException();
            }
            return temp[count++];
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }

    }

    // unit testing (required)
    public static void main(String[] args){

    }
    }

