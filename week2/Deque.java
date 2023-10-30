package week2;
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
            last = first;
            N=1;
        }else {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            oldFirst.back = first;
            N++;     
            if(N==2){
                last = oldFirst;
            }  
        } 
        
    }

    // add the item to the back
    public void addLast(Item item){
        if(item==null){
            throw new IllegalArgumentException();
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
        Node remLast = last;
        last.back.next = null;
        last = remLast.back;
        return remLast.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){
            return current == null;
        }

        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove(){

        }
    }

    public void printTest(){
        System.out.println(N+" =N");
        System.out.println(first.item+" firstItem");
        System.out.println(last.item+" lastItem");
    }
     // unit testing (required)
    public static void main(String[] args){
        Deque<Integer> intDeque = new Deque<Integer>();
        intDeque.addFirst(10);
        intDeque.addLast(20);
        intDeque.addLast(5);
        intDeque.addLast(100);
        // intDeque.removeFirst();
        // intDeque.removeFirst();
        // intDeque.removeLast();
        // intDeque.removeLast();
        // System.out.println(intDeque.last.item);
        // intDeque.printTest();
        // for(int i : intDeque){
        //     System.out.println();
        // }





    }

}
