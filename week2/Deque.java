
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int N;

    private class Node {
        Item item;
        Node next;
        Node back;
    }

    // construct an empty deque
    public Deque(){
        first = new Node();
        last = first;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return N==0;
    }

    // return the number of items on the deque
    public int size(){
        return N;
    }

    // add the item to the front
    public void addFirst(Item item){
        if(item==null){
            throw new IllegalArgumentException();
        }
        if(N==0){
            first.item=item;
            N=1;
        }else {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            oldFirst.back = first;
            N++;     
        } 
        
    }

    // add the item to the back
    public void addLast(Item item){
        if(item==null){
            throw new IllegalArgumentException();
        }
        if(N==0){
            first.item=item;
            N++;
            return;
        }
        Node oldLast = last;
        last = new Node();
        last.item=item;
        oldLast.next=last;
        last.back=oldLast;
        N++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if(N==0){
            throw new java.util.NoSuchElementException();
        }
        if(N==1){
            Item rItem = first.item;
            first.item = null;
            N--;
            return rItem;
        }
        Item removedFirstItem = first.item;
        first = first.next;
        N--;
        return removedFirstItem;
        // if(first!=null){
        //     first.back=null;
        // } 
        
        // if(first==null){
        //     last=null;
        // }
        
    }

    // remove and return the item from the back
    public Item removeLast(){
        if(N==0){
            throw new java.util.NoSuchElementException();
        }
        if(N==1){
            Item rItem = last.item;
            last.item = null;
            N--; 
            return rItem;
        }
        Node remLast = last;
        last.back.next = null;
        last = remLast.back;
        N--;
        return remLast.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item>{
        private Node current = first;
        private int i = 0;
        public boolean hasNext(){
                if(i==0){
                    return current.item!=null;
                }
                return current.next!=null;
        }

        public Item next(){
            if(i==0){
                if(current.item==null){
                    throw new java.util.NoSuchElementException();
                }
                i++;
                return current.item;
            }
            if(current.next==null){
                throw new java.util.NoSuchElementException();
            }
            current = current.next;
            i++;
            return current.item;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    private void printTest(){
        System.out.println(N+" =N");
        System.out.println(first.item+" firstItem");
        System.out.println(last.item+" lastItem");
    }
     // unit testing (required)
    public static void main(String[] args){
        Deque<Integer> intDeque = new Deque<Integer>();
        intDeque.addFirst(1);
        intDeque.addLast(4);
        intDeque.removeLast();
        // intDeque.addFirst(5);
        for(int i : intDeque){
            System.out.println(i);
        }
    }

}
