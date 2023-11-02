
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
        Node removedFirst = first;
        first = first.next;
        if(first!=null){
            first.back=null;
        } 
        N--;
        if(first==null){
            last=null;
        }
        return removedFirst.item;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if(N==0){
            throw new java.util.NoSuchElementException();
        }
        if(N==1){
            last.next = null;
            N--; 
        }
        Node remLast = last;
        last.back.next = null;
        last = remLast.back;
        return remLast.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item>{
        private Node current = first;

        public boolean hasNext(){
            return current != null;
        }

        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove(){

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
        intDeque.addLast(2);
        // intDeque.addFirst(3);
        // intDeque.addFirst(5);
        intDeque.printTest();
        // intDeque.addFirst(3);
        // intDeque.addFirst(5);
        // intDeque.addLast(10);
        // intDeque.removeLast();
        // intDeque.removeFirst();
        // intDeque.removeFirst();
        // System.out.println(intDeque.removeFirst());
        // intDeque.printTest();
        // intDeque.printTest();
        // System.out.println(intDeque.first == intDeque.last);

        for(int i : intDeque){
            System.out.println(i);
        }
    }

}
