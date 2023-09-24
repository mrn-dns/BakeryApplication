package utils;

import models.Ingredient;
import models.Recipe;

import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

public class NodeList<L> implements Iterable<L> {
    public Node<L> head;
    public Node<L>  temp;

    public Node<L> getHead() {
        return head;
    }

    public Node<L> getTemp() {
        return temp;
    }


    public static void main(String[] args) {
        NodeList<Ingredient> ingredients = new NodeList<>();
        Random r = new Random();

        for(int i = 0; i<10; i++){
            ingredients.addNode(new Ingredient("egg", "Its ovalish", r.nextInt(50), "grams"));

        }
        for(Ingredient x : ingredients){
            System.out.println(x);
        }
        System.out.println();

        Comparator<Ingredient> i = (a,b) -> a.getCalPer100Unit()- b.getCalPer100Unit();

        Node<Ingredient> end = null;

        while(ingredients.temp != null){
            if(ingredients.temp.getNext() == null){
                end = ingredients.temp;
                break;
            }
            ingredients.temp = ingredients.temp.getNext();
        }
        ingredients.temp = ingredients.head;

        ingredients.sort(ingredients.head, end, i);


        for(Ingredient x : ingredients){
            System.out.println(x);
        }
    }


    public Node<L> addNode(L node){
        this.temp = this.head = new Node<>(head, node);
        return head;
    }

    public void reset(){
        this.head = this.temp = null;
    }

    public Node<L> deleteNode(Node<L> nodeToDelete){
        if(temp != null && nodeToDelete == head){
            temp = head = head.getNext();
            return nodeToDelete;

        }
        while(temp!=null) {
            if (temp.getNext() == nodeToDelete) {
                temp.setNext(temp.getNext().getNext());
                temp = head;
                return nodeToDelete;
            }
            temp = temp.getNext();
        }
        temp = head;
        return null;
    }

    public L delete(L item){
        if(temp!=null && item.equals(head.getContents())){
            temp = head = head.getNext();
            return item;
        }
        while (temp != null && temp.getNext()!=null){
            if(item.equals(temp.getNext().getContents())) {
                temp.setNext(temp.getNext().getNext());
                temp = head;
                return item;
            }
            temp = temp.getNext();
        }
        temp = head;
        return null;
    }

    public int count(){
        int i = 0;
        for(L x : this){i++;}
        return i;
    }

    public Node<L> getLastNode(){
        Node<L> end;
        while(temp != null){
            if(temp.getNext() == null){
                end = temp;
                temp = head;
                return end;
            }
            temp = temp.getNext();
        }
        return null;
    }

    @Override
    public Iterator<L> iterator() {
        return new NodeIterator<L>(head);
    }

    public int getIndex(Node<L> node){
        int count = 1;
        while(!temp.equals(node)){
            count++;
        }
        return count;
    }

    public Node<L> partitionLast(Node<L> start, Node<L> end, Comparator<L> comparator)
    {
        if (start == end || start == null || end == null)
            return start;

        Node<L> pivot_prev = start;
        Node<L> curr = start;
        Node<L> pivot = end;

        while (start != end) {
            if (comparator.compare(start.getContents(), pivot.getContents())<0) {

                // keep tracks of last modified item
                pivot_prev = curr;
                Node<L> currCopy = new Node<L>(null, curr.contents); // was int
                curr.contents = start.contents;
                start.contents = currCopy.contents;
                curr = curr.next;
            }
            start = start.next;
        }

        Node<L> currCopy = new Node<L>(null, curr.contents); //was int
        curr.contents = pivot.getContents();
        end.contents = currCopy.contents;


        while(temp != null){
            System.out.println(temp.getContents());
            temp = temp.getNext();
        }
        temp = head;
        System.out.println();
        return pivot_prev;
    }

    public void sort(Node<L> start, Node<L> end, Comparator comparator)
    {
        if (start == null || start == end
                || start == end.next)
            return;

        // Split list and partition recurse
        Node<L> pivot_prev = partitionLast(start, end, comparator);
        sort(start, pivot_prev, comparator);

        // If pivot to the start,
        if (pivot_prev != null && pivot_prev == start)
            sort(pivot_prev.next, end, comparator);

            // If pivot is in between of the list,
        else if (pivot_prev != null && pivot_prev.next != null && pivot_prev.next != end)
            sort(pivot_prev.next.next, end, comparator);
    }
}
